package com.locrock.sell.controller;

import com.locrock.sell.converter.OrderForm2OrderDTOConverter;
import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.enums.ResultEnum;
import com.locrock.sell.exception.SellException;
import com.locrock.sell.form.OrderForm;
import com.locrock.sell.service.OrderService;
import com.locrock.sell.service.ProductService;
import com.locrock.sell.util.ResultVOUtil;
import com.locrock.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    /**
     * 创建订单
     */
    @PostMapping("create")
    public ResultVO create (@Valid OrderForm orderForm, BindingResult bindingResult)
    {
        try {
        if (bindingResult.hasErrors ()) {
            log.error ("[创建订单] 参数不正确 , orderForm = {}", orderForm);
            throw new SellException (ResultEnum.PARAM_ERROR.getCode (), bindingResult.getFieldError ().getDefaultMessage ());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter (orderForm);

        if (CollectionUtils.isEmpty (orderDTO.getOrderDetailList ())){
            log.error ("[创建订单] 购物车为空 , orderDTO = {}", orderDTO);

            throw new SellException (ResultEnum.CART_EMPTY_ERROR);
        }

        OrderDTO resultDTO = orderService.create (orderDTO);

        Map <String,String> map=new HashMap<> ();

        map.put ("orderId",resultDTO.getOrderId ());

        return ResultVOUtil.success (map);
        }catch (Exception e){
            return ResultVOUtil.fail (ResultEnum.CREATE_ORDER_ERROR.getCode (),"创建购物车失败");
        }

    }

    /**
     * 订单列表
     */

    @GetMapping("list")
    public ResultVO list(@RequestParam(name = "openid") String openid,
                         @RequestParam(name = "page",defaultValue = "0")   Integer page,
                         @RequestParam(name = "size",defaultValue = "10")   Integer size){
        try {

            if (StringUtils.isEmpty (openid)) {
                log.error ("[查询订单列表] openid为空");

                throw new SellException (ResultEnum.OPENID_EMPTY);
            }

            PageRequest pageRequest = new PageRequest (page, size);

            Page<OrderDTO> dtoPage = orderService.findList (openid, pageRequest);

            return ResultVOUtil.success (dtoPage.getContent ());
        }catch (Exception e){
            return ResultVOUtil.fail (1,"查询列表失败");
        }
    }
    /**
     * 订单详情
     */
    @RequestMapping("detail")
    public ResultVO detail(String openid,String orderId){

        if (StringUtils.isEmpty (openid)) {
            log.error ("[获取订单详情] openid为空");
            throw new SellException (ResultEnum.OPENID_EMPTY);
        }

        if (StringUtils.isEmpty (orderId)){
            log.error ("[获取订单详情] orderId为空");
            throw new SellException (ResultEnum.ORDER_ID_EMPTY);
        }
        try {
            //todo 安全校验,根据 openid 进行校验
            //todo 不安全做法改进
            OrderDTO orderDTO = orderService.findOne (orderId);

            return ResultVOUtil.success (orderDTO);
        }catch (Exception e){
            log.error ("[获取订单详情]  获取详情失败  , openid={}",openid);
            return ResultVOUtil.fail (1,"获取详情错误");
        }
    }

    /**
     * 取消订单
     */
    @PostMapping("cancel")
    public ResultVO cancel(@RequestParam("openid")String openid,
                           @RequestParam("orderId")String orderId) {
        if (StringUtils.isEmpty (openid)) {
            log.error ("[取消订单] openid为空");
            throw new SellException (ResultEnum.OPENID_EMPTY);
        }

        if (StringUtils.isEmpty (orderId)){
            log.error ("[取消订单] orderId为空");
            throw new SellException (ResultEnum.ORDER_ID_EMPTY);
        }
        try {
            //todo 安全校验,根据 openid 进行校验

            OrderDTO dto = orderService.findOne (orderId);

            OrderDTO orderDTO = orderService.cancel (dto);

            return ResultVOUtil.success ();
        }catch (Exception e){
            log.error ("[获取订单详情]  获取详情失败  , openid={}",openid);
            return ResultVOUtil.fail (1,"删除订单错误");
        }
    }
}
