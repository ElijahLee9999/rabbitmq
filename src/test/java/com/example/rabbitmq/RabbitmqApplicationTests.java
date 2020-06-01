package com.example.rabbitmq;

import com.example.rabbitmq.config.DefaultProperty;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RabbitmqApplicationTests {

    private RabbitTemplate rabbitTemplate;

    public RabbitmqApplicationTests(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void sendMsgToMq() {
        rabbitTemplate.convertAndSend(DefaultProperty.topicExchangeName,
                DefaultProperty.routingKey, "这是一条test消息");
    }
}
