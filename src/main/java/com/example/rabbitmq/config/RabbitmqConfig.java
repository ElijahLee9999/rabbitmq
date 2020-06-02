package com.example.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Elijah
 * @create 2020-06-01 15:56
 */
@Slf4j
@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue queue() {
        return new Queue(DefaultProperty.topicQueueName);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(DefaultProperty.topicExchangeName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(DefaultProperty.routingKey);
    }

    /*@Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory cachingConnectionFactory) {
        return new RabbitTemplate(cachingConnectionFactory);
    }*/

    /**
     * 全局消息确认
     * @return messageListenerContainer SimpleMessageListenerContainer
     */
    /*@Bean
    public SimpleMessageListenerContainer messageListenerContainer(CachingConnectionFactory cachingConnectionFactory){

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(cachingConnectionFactory);
        //监听的队列（是一个String类型的可变参数,将监听的队列配置上来，可减少在消费者中代码量）
        container.setQueueNames(DefaultProperty.topicQueueName);
        //手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(channelAwareMessageListener());

        //消息处理
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            log.info("====接收到消息=====");
            log.info(new String(message.getBody()));
            //它会根据方法的执行情况来决定是否确认还是拒绝（是否重新入queue）
            //1.抛出NullPointerException异常则重新入队列
            //throw new NullPointerException("消息消费失败");
            //2.当抛出的异常是AmqpRejectAndDontRequeueException异常的时候，则消息会被拒绝，且requeue=false
            //throw new AmqpRejectAndDontRequeueException("消息消费失败");
            //3.当抛出ImmediateAcknowledgeAmqpException异常，则消费者会被确认
            //throw new ImmediateAcknowledgeAmqpException("消息消费失败");
            //消息手动弄ACK确认
            if(message.getMessageProperties().getHeaders().get("error") == null){

                try {
                    //手动ack应答
                    //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了
                    // 否则消息服务器以为这条消息没处理掉 后续还会在发，true确认所有消费者获得的消息
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                    log.info("消息消费成功：id：{}",message.getMessageProperties().getDeliveryTag());
                } catch (IOException e) {
                    e.printStackTrace();
                    //丢弃这条消息
                    try {
                        //最后一个参数是：是否重回队列
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
                        //拒绝消息
                        //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                        //消息被丢失
                        //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
                        //消息被重新发送
                        //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                        //多条消息被重新发送
                        //channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    log.info("消息消费失败：id：{}",message.getMessageProperties().getDeliveryTag());
                }
            }else {
                //处理错误消息，拒觉错误消息重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
                channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
                log.info("消息拒绝");
            }
        });

        return container;
    }*/
}
