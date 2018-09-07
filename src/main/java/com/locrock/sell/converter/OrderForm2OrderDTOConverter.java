package com.locrock.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.locrock.sell.dataObject.OrderDetail;
import com.locrock.sell.dto.CartDTO;
import com.locrock.sell.dto.OrderDTO;
import com.locrock.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import javax.xml.soap.Detail;
import java.util.List;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
@Slf4j
public class OrderForm2OrderDTOConverter
{
    public static OrderDTO converter (OrderForm orderForm)
    {

        OrderDTO dto = new OrderDTO ();
        try {

            //商家信息封装
            dto.setBuyerName (orderForm.getName ());
            dto.setBuyerPhone (orderForm.getPhone ());
            dto.setBuyerAddress (orderForm.getAddress ());
            dto.setBuyerOpenid (orderForm.getOpenid ());

            String items = orderForm.getItems ();

            List<OrderDetail> orderDetails = new Gson ().fromJson (items, new TypeToken<List<OrderDetail>> () {}.getType ());

            dto.setOrderDetailList (orderDetails);

            return dto;

        } catch (Exception e) {
            e.printStackTrace ();

            log.error ("[表单转换]  类型转换异常 , string = {}",orderForm.getItems ());

            throw new RuntimeException ("转换异常");
        }
    }
}
