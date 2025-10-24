package com.hmall.order.listener;

import com.hmall.api.client.PayClient;
import com.hmall.api.dto.PayOrderDTO;
import com.hmall.order.constants.MQConstants;
import com.hmall.order.domain.po.Order;
import com.hmall.order.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDelayMessageListener {
    private final IOrderService orderService;
    private final PayClient payClient;

    @RabbitListener(bindings = @QueueBinding(
            value=@Queue(name = MQConstants.DELAY_QUEUE_NAME),
            exchange=@Exchange(name = MQConstants.DELAY_EXCHANGE_NAME,delayed = "true"),
            key = MQConstants.DELAY_ORDER_KEY
    ))
    public void ListenerOrderDelayMessage(Long id) {
        //1.查询订单
        Order order=orderService.getById(id);
        //2.检测订单状态，判断是否已支付
        if(order == null || order.getStatus() != 1){
            return;
        }
        //3.未支付，需要查询支付流水状态
        PayOrderDTO payOrderDTO = payClient.queryPayOrderByBizOrderNo(id);
        //4.判断是否支付
        if(payOrderDTO != null && payOrderDTO.getStatus() == 3){
            //4.1已支付，标记订单状态为已支付
            orderService.markOrderPaySuccess(id);
        }else {
            //4.2未支付，取消订单，回复库存
            orderService.cancelOrder(id);
        }
    }
}
