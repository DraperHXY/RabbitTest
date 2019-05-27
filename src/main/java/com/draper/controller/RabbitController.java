package com.draper.controller;

import com.draper.common.util.StatusResult;
import com.draper.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author draper_hxy
 */
@Slf4j
@RestController
public class RabbitController {

    @Autowired
    private RabbitService rabbitService;

    @PostMapping("/p/{routingKey}/{num}")
    public StatusResult<String> product(@PathVariable String routingKey, @PathVariable Integer num) {
        log.info("Rabbit product");
        rabbitService.product(routingKey, num);
        return null;
    }

    @PostMapping("/c/{queueName}/{num}")
    public StatusResult<String> consume(@PathVariable String queueName, @PathVariable Integer num) {
        log.info("Rabbit consume");
        rabbitService.consume(queueName, num);
        return null;
    }


}
