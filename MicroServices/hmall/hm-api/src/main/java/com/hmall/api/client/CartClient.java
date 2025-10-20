package com.hmall.api.client;

import com.hmall.api.fallback.CartClientFallbackFactory;
import com.hmall.api.fallback.ItemClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/*
 * @Auther:fz
 * @Date:2025/10/5
 * @Description:
 */
@FeignClient(value = "cart-service" ,fallbackFactory = CartClientFallbackFactory.class)
public interface CartClient {
    @DeleteMapping("/carts")
    void deleteCartItemByIds(@RequestParam("ids") Collection<Long> ids);
}
