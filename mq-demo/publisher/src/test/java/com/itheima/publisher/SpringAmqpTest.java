package com.itheima.publisher;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Slf4j
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

    @Test
    public void TestConfigCallback() throws InterruptedException {
        //1.创建CorrelationData
        CorrelationData correlationData=new CorrelationData(UUID.randomUUID().toString());//随机生成一个唯一的ID
        correlationData.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>(){

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                if(result.isAck()){
                    log.debug("消息发送成功");
                }else
                    log.error("消息发送失败");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("消息发送失败:【{}】", String.valueOf(ex));
            }
        } );

        //2.队列名
        String exchangeName = "fz.direct";

        //3.消息
        String message = "hello, spring amqp!";

        //4.发送消息
        rabbitTemplate.convertAndSend(exchangeName,"red",message,correlationData);

        //5.避免单元测试立即结束
        Thread.sleep(2000);
    }


    @Test
    public void TestDlx(){
        rabbitTemplate.convertAndSend("normal.direct","dlx","hello, spring amqp!",new MessagePostProcessor(){

            //设置消息的过期时间
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
    }

    @Test
    void testPublisherDelayMessage() {
        // 1.创建消息
        String message = "hello, delayed message";
        // 2.发送消息，利用消息后置处理器添加消息头
        rabbitTemplate.convertAndSend("delay.direct", "delay", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 添加延迟消息属性
                message.getMessageProperties().setDelay(10000);
                return message;
            }
        });
    }
}
// TIP ajsdasjdoiasjdoiajdiahd iuyadsgfuyaajKOISj
//jjh
