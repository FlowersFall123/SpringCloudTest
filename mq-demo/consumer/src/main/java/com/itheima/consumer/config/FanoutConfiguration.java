package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfiguration {

    @Bean
    public FanoutExchange fanoutExchange(){//创建交换机
        return ExchangeBuilder.fanoutExchange("fz1.fanout").build();
    }
    @Bean
    public Queue fanoutQueue1(){//创建队列
        return new Queue("fanout.queue1");
    }
    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1,FanoutExchange fanoutExchange){//绑定关系
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
}
