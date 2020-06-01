package com.example.rabbitmq.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * @author Elijah
 * @create 2020-05-29 15:16
 */
public class MyJsonDeserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(DefaultProperty.dateTimeFormatRegx);
        return DateTime.parse(jsonParser.getValueAsString(), dtf);
    }

    @Override
    public Class<DateTime> handledType() {
        return DateTime.class;
    }
}
