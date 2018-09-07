package com.locrock.sell.exception;

import com.locrock.sell.enums.ResultEnum;
import lombok.Data;

/**
 * Author : 周怡斌 data : 2018/9/7
 * 全局异常类
 */
@Data
public class SellException extends RuntimeException
{
    private Integer code;

    public SellException (ResultEnum resultEnum)
    {
        super(resultEnum.getMessage ());

        this.code = resultEnum.getCode ();
    }

    public SellException (Integer code, String message) {
        super(message);
        this.code=code;
    }
}
