package com.hmall.api.client;

/*
 * @Auther:fz
 * @Date:2025/10/4
 * @Description:
 */

import com.hmall.api.fallback.PayClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "pay-service",fallbackFactory = PayClientFallbackFactory.class)
public interface PayClient {

}
