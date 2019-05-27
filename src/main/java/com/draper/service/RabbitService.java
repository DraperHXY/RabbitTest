package com.draper.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author draper_hxy
 */
@Slf4j
@Service
public class RabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 生产消息
     */
    public void product(String routingKey, Integer num) {
        for (Integer i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend("draper.production.test", routingKey, "消费");
        }

    }

    /**
     * 消费消息
     *
     * @param num
     */
    public void consume(String queueName, Integer num) {
        for (Integer i = 0; i < num; i++) {
            String msg = Objects.requireNonNull(rabbitTemplate.receiveAndConvert(queueName, 1000)).toString();
            log.info("消费信息 {}", msg);
        }
    }


}
