package com.example.rabbitmq.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Elijah
 * @create 2020-05-29 15:13
 */
@Component
public class DefaultProperty {
    /**
     * 时间格式
     */
    public static String dateTimeFormatRegx;
    /**
     * 时区
     */
    public static String timeZoneString;
    /**
     * 分页查询最大分页
     */
    public static int paginationLimit;
    /**
     * redis缓存过期时间，单位分钟
     */
    public static int redisDuration;
    /**
     * 队列名称
     */
    public static String topicQueueName;
    /**
     * 交换机名
     */
    public static String topicExchangeName;
    /**
     * 路由key，*匹配一个，#匹配多个
     */
    public static String routingKey;
    /**
     * Id生成器机器ID
     */
    public static Long workerId;
    /**
     * Id生成器数据中心ID
     */
    public static Long dataCenterId;

    private PropertyEntity propertyEntity;

    public DefaultProperty(PropertyEntity propertyEntity) {
        this.propertyEntity = propertyEntity;
    }
    
    @PostConstruct
    public void init() {
        dateTimeFormatRegx = propertyEntity.getDateTimeFormatRegx();
        timeZoneString = propertyEntity.getTimeZoneString();
        paginationLimit = propertyEntity.getPaginationLimit();
        redisDuration = propertyEntity.getRedisDuration();
        topicQueueName = propertyEntity.getTopicQueueName();
        topicExchangeName = propertyEntity.getTopicExchangeName();
        routingKey = propertyEntity.getRoutingKey();
        workerId = propertyEntity.getWorkerId();
        dataCenterId = propertyEntity.getDataCenterId();
    }
}
