package com.itheima.consumer.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class SpringRabbitListener {
    @RabbitListener(queues="simple.queue")
    public void listenSimpleQueue(String message){
        System.out.println("收到simple.queue的消息: "+message);
    }

    @RabbitListener(queues="work.queue")
    public void listenWorkQueue1(String message) throws InterruptedException {
        System.out.println("消费者1，收到work.queue的消息: "+message);
        Thread.sleep(25);
    }

    @RabbitListener(queues="work.queue")
    public void listenWorkQueue2(String message) throws InterruptedException {
        System.err.println("消费者2，收到work.queue的消息: "+message);
        Thread.sleep(200);
    }

    @RabbitListener(queues="fanout.queue1")
    public void listenFanoutQueue1(String message) throws InterruptedException {
        System.out.println("收到fanout.queue1的消息: "+message);
    }
    @RabbitListener(queues="fanout.queue2")
    public void listenFanoutQueue2(String message) throws InterruptedException {
        System.err.println("收到fanout.queue2的消息: "+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "fz1.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue1(String message) throws InterruptedException {
        System.out.println("direct.queue1的消息: "+message);
    }
    @RabbitListener(queues="direct.queue2")
    public void listenDirectQueue2(String message) throws InterruptedException {
        System.err.println("direct.queue2的消息: "+message);
    }

    @RabbitListener(queues="topic.queue1")
    public void listenTopicQueue1(String message) throws InterruptedException {
        System.out.println("topic.queue1的消息: "+message);
    }
    @RabbitListener(queues="topic.queue2")
    public void listenTopicQueue2(String message) throws InterruptedException {
        System.err.println("topic.queue2的消息: "+message);
    }

    @RabbitListener(queues="object.queue")
    public void listenObjectQueue(Message message) throws InterruptedException {
        log.info("消息的ID为：【{}】",message.getMessageProperties().getMessageId());
        log.info("消息为：【{}】", new String(message.getBody()));
        System.out.println("object.queue的消息: "+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "dlx.queue"),
            exchange = @Exchange(name = "dlx.direct", type = ExchangeTypes.DIRECT),
            key = {"dlx"}
    ))
    public void listenDlxQueue(String message) throws InterruptedException {
        System.out.println("dlx.queue的消息: "+message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "delay.queue", durable = "true"),
            exchange = @Exchange(name = "delay.direct", delayed = "true"),
            key = "delay"
    ))
    public void listenDelayMessage(String msg){
        log.info("接收到delay.queue的延迟消息：{}", msg);
    }
}
