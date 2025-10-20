package com.itheima.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void publishMessage(){
        //1.队列名
        String queueName = "simple.queue";

        //2.消息
        String message = "hello, spring amqp!";

        //3.发送消息
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void publishWorkQueue(){
        //1.队列名
        String queueName = "work.queue";

        //3.发送消息
        for (int i = 0; i < 50; i++) {
            //2.消息
            String message = "hello, work.queue!"+i;
            //3.发送消息
            rabbitTemplate.convertAndSend(queueName,message);
        }
    }

    @Test
    public void TestFanoutExchange(){
        //1.交换机名
        String exchange = "fz.fanout";

        //2.消息
        String message = "hello, everyone!";

        //3.发送消息
        rabbitTemplate.convertAndSend(exchange,null,message);
    }
    @Test
    public void TestDirectExchange(){
        //1.交换机名
        String exchange = "fz.direct";

        //2.消息
        String message = "hello, everyone!";

        //3.发送消息
        rabbitTemplate.convertAndSend(exchange,"blue",message);
    }

    @Test
    public void TestTopicExchange(){
        //1.交换机名
        String exchange = "fz.topic";

        //2.消息
        String message = "hello, everyone!";

        //3.发送消息
        rabbitTemplate.convertAndSend(exchange,"chain.people",message);
    }


    @Test
    public void TestObjectQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","fz");
        map.put("age",18);
        rabbitTemplate.convertAndSend("object.queue",map);
    }


}
