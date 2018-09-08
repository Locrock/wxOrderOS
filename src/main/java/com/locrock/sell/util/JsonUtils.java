package com.locrock.sell.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Author : 周怡斌 data : 2018/9/8
 */
public class JsonUtils
{
    public static String toJson(Object object){
        GsonBuilder gsonBuilder=new GsonBuilder ();

        gsonBuilder.setPrettyPrinting ();

        Gson gson=gsonBuilder.create ();

        return gson.toJson (object);
    }
}
