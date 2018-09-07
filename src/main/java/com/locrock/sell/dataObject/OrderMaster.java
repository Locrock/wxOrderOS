package com.locrock.sell.dataObject;

import com.locrock.sell.enums.OrderStatusEnum;
import com.locrock.sell.enums.PayStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMaster {
    @Id
    /*订单id*/
    private String orderId;
    /*买家名字*/
    private String buyerName;
    /*买家电话*/
    private String buyerPhone;
    /*买家地址*/
    private String buyerAddress;
    /*买家的微信id*/
    private String buyerOpenid;
    /*账单金额*/
    private BigDecimal orderAmount;
    /*订单状态,默认新订单*/
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /*支付状态,默认未支付*/
    private Integer payStatus= PayStatusEnum.WAIT.getCode();
    /*创建时间*/
    private Date createTime;
    /*更新时间*/
    private Date updateTime;
}
