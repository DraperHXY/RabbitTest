package com.draper.common.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


/**
 * 消息发送到 Broker 后触发回调，确认消息是否送达到 Broker
 * 也就是仅能确认是否送达到 Exchange 中
 *
 * @author draper_hxy
 */
public class RabbitConfirmCallback implements RabbitTemplate.ConfirmCallback {

    private static final Logger log = LoggerFactory.getLogger(RabbitConfirmCallback.class);

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息唯一标识 = {}", correlationData);
        log.info("确认结果: {}", ack);
        log.info("结果内容: {}", cause);
    }

}
