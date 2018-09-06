package com.locrock.sell.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author : 周怡斌 data : 2018/9/6
 * http请求返回的最外层对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T>
{
//    消息码,正确或错误
    private Integer code;
//    返回的信息
    private String msg;
//    数据
    private T data;
}
