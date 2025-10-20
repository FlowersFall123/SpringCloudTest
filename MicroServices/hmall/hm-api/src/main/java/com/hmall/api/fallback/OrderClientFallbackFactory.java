package com.hmall.api.fallback;

import com.hmall.api.client.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class OrderClientFallbackFactory implements FallbackFactory<OrderClient> {
    @Override
    public OrderClient create(Throwable cause) {
        return new OrderClient() {
            @Override
            public void markOrderPaySuccess(Long orderId) {
                log.error("订单支付失败", cause);
                throw new RuntimeException("订单支付失败");
            }
        };
    }
}
