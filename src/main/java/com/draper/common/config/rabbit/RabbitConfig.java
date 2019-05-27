package com.draper.common.config.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author draper_hxy
 */
@Configuration
public class RabbitConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");

        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);

        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Channel channel() {
        Channel channel = connectionFactory().createConnection().createChannel(false);
        try {
            channel.exchangeDeclare("draper.production.test", "topic", true, false, null);
            channel.queueDeclare("productionQueue2", true, false, false, null);
            channel.queueBind("productionQueue2", "draper.production.test", "2");

            channel.basicQos(0, 3, false);
            channel.basicConsume("productionQueue2", false, new StringMsgNotAutoAckConsumer(channel));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setConfirmCallback(new RabbitConfirmCallback());
        rabbitTemplate.setReturnCallback(new RabbitReturnCallback());
        return rabbitTemplate;
    }

    // 这一部分仅包括生产配置
    /*------------------------- 开始 -------------------------*/
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("draper.production.test", true, false);
    }
    /*------------------------- 结束 -------------------------*/

    // 这一部分仅包括消费消费，且基于消息驱动
    /*------------------------- 开始 -------------------------*/
    @Bean
    public Queue productionQueue1() {
        return new Queue("productionQueue1", true, false, false);
    }

    @Bean
    public Binding bindingProductionQueue1(@Qualifier("productionQueue1") Queue productionQueue1,
                                           TopicExchange exchange) {
        return BindingBuilder.bind(productionQueue1).to(exchange).with("1");
    }

    /*------------------------- 结束 -------------------------*/

    /*------------------------- 开始 -------------------------*/
//    @Bean
//    public Queue productionQueue2() {
//        return new Queue("productionQueue2", true, false, false);
//    }
//
//    @Bean
//    public Binding bindingProductionQueue2(@Qualifier("productionQueue2") Queue productionQueue2,
//                                           TopicExchange exchange) {
//        return BindingBuilder.bind(productionQueue2).to(exchange).with("2");
//    }
    /*------------------------- 结束 -------------------------*/


}
