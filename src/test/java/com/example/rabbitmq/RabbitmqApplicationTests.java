package com.example.rabbitmq;

import com.example.rabbitmq.config.DefaultProperty;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RabbitmqApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        log.info("开始测试");
    }

    @Test
    void sendMsgToMq() {
        rabbitTemplate.convertAndSend(DefaultProperty.topicExchangeName,
                DefaultProperty.routingKey, "这是一条test消息" + DateTime.now().toString(DefaultProperty.dateTimeFormatRegx));
    }
}
