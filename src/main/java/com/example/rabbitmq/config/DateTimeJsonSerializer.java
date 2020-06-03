package com.example.rabbitmq.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * DateTime序列化配置
 * @author Elijah
 * @create 2020-05-29 15:11
 */
public class DateTimeJsonSerializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(dateTime.toString(DefaultProperty.dateTimeFormatRegx));
    }

    @Override
    public Class<DateTime> handledType() {
        return DateTime.class;
    }
}
