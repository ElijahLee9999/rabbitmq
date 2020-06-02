package com.example.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Elijah
 * @create 2020-06-01 17:49
 */
@Slf4j
@Component
public class RabbitmqHandler {

    @RabbitListener(queues = "TopicQueue1000")
    @RabbitHandler
    public void processTopicMsg(Channel channel, String json, Message message, @Headers Map<String,Object> map) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("received Topic message : " + msg);

        //<P>代码为在消费者中开启消息接收确认的手动ack</p>
        //<H>配置完成</H>
        //<P>可以开启全局配置</p>
        if (map.get("error")!= null){
            log.info("错误的消息");
            try {
                //否认消息,拒接该消息重回队列
                channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,false);
                return;
            } catch (Exception e) {
                log.error("错误的消息", e);
            }
        }
        //手动ACK
        //默认情况下如果一个消息被消费者所正确接收则会被从队列中移除
        //如果一个队列没被任何消费者订阅，那么这个队列中的消息会被 Cache（缓存），
        //当有消费者订阅时则会立即发送，当消息被消费者正确接收时，就会被从队列中移除
        try {
            //手动ack应答
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了
            // 否则消息服务器以为这条消息没处理掉 后续还会在发，true确认所有消费者获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            log.info("消息消费成功：id：{}",message.getMessageProperties().getDeliveryTag());
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            try {
                //最后一个参数是：是否重回队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                //拒绝消息
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //消息被丢失
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                //消息被重新发送
                //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                //多条消息被重新发送
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            log.info("消息消费失败：id：{}", message.getMessageProperties().getDeliveryTag());
        }
    }
}
