package com.draper.common.config.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * 消费者，AutoACK, 且基于消息驱动
 *
 * @author draper_hxy
 */
@Slf4j
@Configuration
public class StringMsgNotAutoAckConsumer extends DefaultConsumer {

    private Channel channel;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public StringMsgNotAutoAckConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    public void handleString(String msg) {
        log.info("消费的消息为 = {}", msg);
//        handleDelivery();
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        log.info("-----------consume message----------");
        log.info("consumerTag = {}", consumerTag);
        log.info("envelope = {}", envelope);
        log.info("properties = {}", properties);
        log.info("body = {}", new String(body));

        //channel.basicAck(envelope.getDeliveryTag(), false); // 对消息进行手动 ack
        channel.basicNack(envelope.getDeliveryTag(),false,true); // 对信息不进行确认，让重回队列，当满足一定条件时
    }
}
