package com.example.rabbitmq.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.joda.time.DateTime;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Elijah
 * @create 2020-05-29 16:56
 */
public class MyJackson2ObjectMapper {

    public static Jackson2ObjectMapperBuilder getObjectMapper(Jackson2ObjectMapperBuilder builder) {
        //处理枚举的序列化
        builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_INDEX);
        // 设置时间格式
        builder.simpleDateFormat(DefaultProperty.dateTimeFormatRegx);
        //设置时区
        builder.timeZone(DefaultProperty.timeZoneString);
        //处理DateTime的序列化
        builder.serializerByType(DateTime.class, new MyJsonSerializer());
        //处理DateTime的反序列化
        builder.deserializerByType(DateTime.class, new MyJsonDeserializer());
        //通过服务加载程序查找模块
        builder.findModulesViaServiceLoader(true);
        return builder;
    }

    public static Jackson2ObjectMapperBuilder getObjectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        return getObjectMapper(builder);
    }
}
