package com.hmall.api.client;

import com.hmall.api.fallback.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @Auther:fz
 * @Date:2025/10/4
 * @Description:
 */
@FeignClient(value = "user-service",fallbackFactory =  UserClientFallbackFactory.class)
public interface UserClient {
    @PutMapping("/users/money/deduct")
    void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);
}
