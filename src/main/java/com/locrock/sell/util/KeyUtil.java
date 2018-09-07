package com.locrock.sell.util;

import java.util.Random;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
public class KeyUtil
{
    /**
     * 生成唯一的主键
     * 格式:  时间加随机数
     * @return
     */
    public static synchronized String genUniqueKey(){

        Random random=new Random ();
        //生成六位随机数
        Integer num=random.nextInt (900000)+100000;

        return System.currentTimeMillis ()+num+"";
    }
}
