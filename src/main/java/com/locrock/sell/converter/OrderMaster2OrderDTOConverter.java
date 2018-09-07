package com.locrock.sell.converter;

import com.locrock.sell.dataObject.OrderMaster;
import com.locrock.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
public class OrderMaster2OrderDTOConverter
{

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO ();

        BeanUtils.copyProperties (orderMaster,orderDTO);

        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasterList){
        return orderMasterList.
                stream ().
                map (OrderMaster2OrderDTOConverter::converter).
                collect(Collectors.toList());
    }

    public static OrderMaster converter(OrderDTO orderDTO){
        OrderMaster master=new OrderMaster ();

        BeanUtils.copyProperties (orderDTO,master);

        return master;
    }
}
