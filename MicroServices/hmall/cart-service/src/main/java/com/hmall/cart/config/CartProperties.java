package com.hmall.cart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @Auther:fz
 * @Date:2025/10/6
 * @Description:
 */
@Component
@Data
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxItems;
}
