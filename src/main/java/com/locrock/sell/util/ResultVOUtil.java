package com.locrock.sell.util;

import com.locrock.sell.vo.ResultVO;

import javax.xml.transform.Result;

/**
 * Author : 周怡斌 data : 2018/9/6
 */
public class ResultVOUtil
{
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO ();
        resultVO.setData (object);
        resultVO.setCode (0);
        resultVO.setMsg ("成功");
        return  resultVO;
    }
    public static ResultVO success(){
        return  success (null);
    }

    public static ResultVO fail(Integer code,String msg){
        ResultVO resultVO=new ResultVO ();
        resultVO.setData (null);
        resultVO.setCode (code);
        resultVO.setMsg (msg);
        return resultVO;
    }
}
