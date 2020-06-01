package com.example.rabbitmq.utils;

import com.example.rabbitmq.config.DefaultProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author Elijah
 * @create 2020-06-01 17:49
 */
@Slf4j
@Component
@RabbitListener(queues = "TopicQueue1000")
public class RabbitmqHandler {

    @RabbitHandler
    public void processTopicMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Topic message : " + msg);
    }
}
