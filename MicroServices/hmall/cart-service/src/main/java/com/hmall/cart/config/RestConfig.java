package com.hmall.cart.config;

/*
 * @Auther:fz
 * @Date:2025/10/3
 * @Description:
 */

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class RestConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
