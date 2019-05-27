package com.draper.common.config.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 启动消息失败返回，比如路由不到队列时触发回调
 *
 * @author draper_hxy
 */
public class RabbitReturnCallback implements RabbitTemplate.ReturnCallback {

    private static final Logger log = LoggerFactory.getLogger(RabbitReturnCallback.class);

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息主体 message : " + message);
        log.info("消息码 message : " + replyCode);
        log.info("描述：" + replyText);
        log.info("消息使用的交换器 exchange : " + exchange);
        log.info("消息使用的路由键 routing : " + routingKey);
    }

}
