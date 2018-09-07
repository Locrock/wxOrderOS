package com.locrock.sell.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Author : 周怡斌 data : 2018/9/7
 */
public class Date2LongSerializer extends JsonSerializer<Date>
{
    @Override
    public void serialize (Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException
    {
        jsonGenerator.writeNumber (date.getTime ()/1000);
    }
}
