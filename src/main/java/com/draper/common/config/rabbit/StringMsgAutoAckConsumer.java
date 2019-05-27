package com.draper.common.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * 消费者，AutoACK, 且基于消息驱动
 *
 * @author draper_hxy
 */
@Slf4j
@Configuration
public class StringMsgAutoAckConsumer {

    public void handleString(String msg) {
        log.info("消费的消息为 = {}", msg);
    }

}
