package com.locrock.sell.service.impl;

import com.locrock.sell.converter.OrderMaster2OrderDTOConverter;
import com.locrock.sell.dataObject.OrderDetail;
import com.locrock.sell.dataObject.OrderMaster;
import com.locrock.sell.dataObject.ProductInfo;
import com.locrock.sell.dto.CartDTO;
import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.enums.OrderStatusEnum;
import com.locrock.sell.enums.PayStatusEnum;
import com.locrock.sell.enums.ResultEnum;
import com.locrock.sell.exception.SellException;
import com.locrock.sell.repository.OrderDetailRepository;
import com.locrock.sell.repository.OrderMasterRepository;
import com.locrock.sell.service.OrderService;
import com.locrock.sell.service.ProductService;
import com.locrock.sell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderMasterRepository masterRepository;
    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private ProductService productService;

    /**
     * 创建新的订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create (OrderDTO orderDTO)
    {
        //1,查询商品(数量,价格)
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList ();

        String orderId= KeyUtil.genUniqueKey ();
        //定义一个总价
        BigDecimal orderAmount=new BigDecimal (BigInteger.ZERO);

        for (OrderDetail orderDetail : orderDetailList) {

            ProductInfo productInfo = productService.findOne (orderDetail.getProductId ());
            //如果商品不存在
            if (productInfo==null) throw new SellException (ResultEnum.PRODUCT_NOT_EXIST);
            //2.计算总价
            orderAmount = productInfo
                    .getProductPrice ()
                    .multiply (new BigDecimal (orderDetail.getProductQuantity ()))
                    .add (orderAmount);

            //订单详情入库
            //属性拷贝
            BeanUtils.copyProperties (productInfo,orderDetail);

            orderDetail.setOrderId (orderId);
            orderDetail.setDetailId (KeyUtil.genUniqueKey ());
            detailRepository.save (orderDetail);
        }

        //3,写入订单数据库(orderMaster和orderDetail)
        OrderMaster orderMaster=new OrderMaster ();

        //先拷贝后赋值
        BeanUtils.copyProperties (orderDTO,orderMaster);

        orderMaster.setPayStatus (0);
        orderMaster.setOrderStatus (0);
        orderMaster.setOrderId (orderId);
        orderMaster.setOrderAmount (orderAmount);

        masterRepository.save (orderMaster);
        //4.扣库存

        List<CartDTO> cartDTOList = orderDTO.
                getOrderDetailList ().
                stream ().
                map (e -> new CartDTO (e.getProductId (), e.getProductQuantity ())).
                collect (Collectors.toList ());

        productService.decreaseStock (cartDTOList);


        //返回时orderId为空,再此赋值
        orderDTO.setOrderId (orderId);

        return orderDTO;
    }

    /**
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO findOne (String orderId)
    {
        OrderDTO orderDTO=new OrderDTO ();

        //获取订单数据
        OrderMaster orderMaster = masterRepository.findOne (orderId);
        //判断是否为空
        if (orderMaster==null)throw new SellException (ResultEnum.ORDER_NOT_EXIST);
        //获取订单详情
        List<OrderDetail> orderDetailList = detailRepository.findByOrOrderId (orderId);
        //判断是否为空
        if (CollectionUtils.isEmpty (orderDetailList)) throw new SellException (ResultEnum.ORDER_DETAIL_NOT_EXIST);
        //属性拷贝
        BeanUtils.copyProperties (orderMaster,orderDTO);
        //字段赋值
        orderDTO.setOrderDetailList (orderDetailList);

        return orderDTO;
    }

    /**
     *返回orderDTO的列表集合，分页
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDTO> findList (String buyerOpenid, Pageable pageable)
    {
        Page<OrderMaster> orderMasters = masterRepository.findByBuyerOpenid (buyerOpenid, pageable);

        //获取分页内容
        List<OrderMaster> content = orderMasters.getContent ();

        //自定义转换类
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.converter (content);
        //此处列表为空，逻辑允许，代表没有商品
        /*if(CollectionUtils.isEmpty (orderMasters.getContent ())) throw new SellException (ResultEnum.ORDER_NOT_EXIST);*/

        return new PageImpl<> (orderDTOList,pageable,orderMasters.getTotalElements ());
    }

    @Override
    @Transactional
    public OrderDTO cancel (OrderDTO orderDTO)
    {
        //将orderDTO转换为OrderMaster
        OrderMaster master = OrderMaster2OrderDTOConverter.converter (orderDTO);

        Integer orderStatus = orderDTO.getOrderStatus ();

        /*判断订单状态*/
        if(orderStatus!=0) {

            log.error ("[取消订单] 订单状态不正确  orderId={},orderStatus={}",orderDTO.getOrderId (),orderDTO.getOrderStatus ());

            throw new SellException (ResultEnum.ORDER_STATUS_ERROR);
        }

        /*修改订单状态*/
        //设置订单为取消状态
        master.setOrderStatus (OrderStatusEnum.CANCEL.getCode ());

        //储存结果
        OrderMaster orderMaster = masterRepository.save (master);

        if (orderMaster==null){
            log.error ("[取消订单] 更新失败 orderMaster={}" ,master);

            throw new SellException (ResultEnum.ORDER_UPDATE_FAIL);
        }
        if (CollectionUtils.isEmpty (orderDTO.getOrderDetailList ())){
            log.error ("[取消订单] 订单中无商品详情");

            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        /*返回库存*/

        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList ().
                stream ().
                map (e->new CartDTO (e.getProductId (),e.getProductQuantity ())).
                collect (Collectors.toList ());

        //增加库存
        productService.increaseStock (cartDTOList);

        /*如果已支付，需要退款*/
        if (orderDTO.getPayStatus ().equals (PayStatusEnum.SUCCESS.getCode ())){
            //todo
            //退款操作

        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish (OrderDTO orderDTO)
    {
        OrderMaster orderMaster = OrderMaster2OrderDTOConverter.converter (orderDTO);

        Integer orderStatus = orderMaster.getOrderStatus ();

        //1.判断订单状态
        if (!orderStatus.equals (OrderStatusEnum.NEW.getCode ())){
            log.error ("[完结订单] 订单完结失败: orderMaster = {}", orderMaster);

            throw new SellException(ResultEnum.ORDER_FINISHED_ERROR);
        }

        //2.修改订单状态
        orderMaster.setOrderStatus (OrderStatusEnum.FINISHED.getCode ());

        OrderMaster master = masterRepository.save (orderMaster);

        if (master==null){
            log.error ("[完结订单] 更新失败,orderMaster={}",orderMaster);

            throw new SellException (ResultEnum.ORDER_UPDATE_FAIL);
        }

        return OrderMaster2OrderDTOConverter.converter (master);
    }

    @Override
    public OrderDTO paid (OrderDTO orderDTO)
    {
        OrderMaster orderMaster=new OrderMaster ();

        Integer orderStatus = orderDTO.getOrderStatus ();
        Integer payStatus = orderDTO.getPayStatus ();
        if (!orderStatus.equals (OrderStatusEnum.NEW.getCode ())){
            log.error ("[订单支付] 支付失败 ,orderStatus={},orderDTO={}",orderStatus,orderDTO);

            throw new SellException (ResultEnum.ORDER_PAID_ERROR);
        }
        if (!payStatus.equals (PayStatusEnum.WAIT.getCode ())){
            log.error ("[订单支付]  支付失败, payStatus={}",payStatus);

            throw new SellException (ResultEnum.ORDER_PAID_ERROR);
        }
        //修改订单状态
        orderDTO.setPayStatus (PayStatusEnum.SUCCESS.getCode ());

        //orderDTO.setOrderStatus (OrderStatusEnum.FINISHED.getCode ());

        BeanUtils.copyProperties (orderDTO,orderMaster);

        OrderMaster master = masterRepository.save (orderMaster);

        if (master==null){
            log.error ("[完结订单] 更新失败,orderMaster={}",orderMaster);

            throw new SellException (ResultEnum.ORDER_UPDATE_FAIL);
        }

        return orderDTO;
    }
}
