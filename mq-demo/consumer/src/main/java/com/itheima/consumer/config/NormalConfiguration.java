package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NormalConfiguration {

    @Bean
    public DirectExchange normalExchange(){//创建普通交换机
        return ExchangeBuilder.directExchange("normal.direct").build();
    }
    @Bean
    public Queue normalQueue(){//绑定死信交换机
        return QueueBuilder.durable("normal.queue").deadLetterExchange("dlx.direct").build();
    }
    @Bean
    public Binding directBinding(Queue normalQueue,DirectExchange normalExchange){//绑定关系
        return BindingBuilder.bind(normalQueue).to(normalExchange).with("dlx");//这里需要与死信交换机的routingKey一致
    }
}
