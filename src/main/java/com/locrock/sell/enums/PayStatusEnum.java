package com.locrock.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PayStatusEnum {
    WAIT(0,"未支付"),
    SUCCESS(1,"支付成功");



    private Integer code;

    private String message;

}
