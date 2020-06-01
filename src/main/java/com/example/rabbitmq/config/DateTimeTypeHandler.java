package com.example.rabbitmq.config;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * @author Elijah
 * @create 2020-05-28 15:43
 */
@Slf4j
@Component
public class DateTimeTypeHandler extends AbstractJsonTypeHandler<DateTime> {

    @Override
    protected DateTime parse(String json) {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(DefaultProperty.dateTimeFormatRegx);
        return DateTime.parse(json, dtf);
    }

    @Override
    protected String toJson(DateTime obj) {
        return obj.toString(DefaultProperty.dateTimeFormatRegx);
    }
}
