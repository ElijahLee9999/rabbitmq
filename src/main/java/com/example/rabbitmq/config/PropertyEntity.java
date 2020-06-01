package com.example.rabbitmq.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Elijah
 * @create 2020-06-01 16:44
 */
@Data
@Component
public class PropertyEntity {
    @Value("${customize.date-time-format}")
    private String dateTimeFormatRegx;
    @Value("${customize.time-zone}")
    private String timeZoneString;
    @Value("${customize.pagination}")
    private int paginationLimit;
    @Value("${customize.redis-duration}")
    private int redisDuration;
    @Value("${customize.topic-queue}")
    private String topicQueueName;
    @Value("${customize.topic-exchange}")
    private String topicExchangeName;
    @Value("${customize.routing-key}")
    private String routingKey;
    @Value("${customize.worker-id}")
    private Long workerId;
    @Value("${customize.data-center-id}")
    private Long dataCenterId;
}
