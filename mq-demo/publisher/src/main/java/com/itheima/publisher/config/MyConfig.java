package com.itheima.publisher.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyConfig {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setReturnsCallback(returnedMessage  -> {
            log.error("触发return callback,");
            log.debug("exchange: {}", returnedMessage.getExchange());
            log.debug("routingKey: {}", returnedMessage.getRoutingKey());
            log.debug("message: {}", returnedMessage.getMessage());
            log.debug("replyCode: {}", returnedMessage.getReplyCode());
            log.debug("replyText: {}", returnedMessage.getReplyText());
        });
    }
}
