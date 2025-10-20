package com.hmall.api.client;

import com.hmall.api.fallback.OrderClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/*
 * @Auther:fz
 * @Date:2025/10/4
 * @Description:
 */
@FeignClient(value = "order-service",fallbackFactory = OrderClientFallbackFactory.class)
public interface OrderClient {
    @PutMapping("/{orderId}")
    void markOrderPaySuccess(@PathVariable("orderId") Long orderId) ;
}
