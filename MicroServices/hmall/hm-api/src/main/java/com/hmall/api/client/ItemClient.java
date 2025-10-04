package com.hmall.api.client;


import com.hmall.api.dto.ItemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/*
 * @Auther:fz
 * @Date:2025/10/4
 * @Description:
 */

@FeignClient("item-service")
public interface ItemClient {
    @GetMapping("/items")
    List<ItemDTO> queryByIds(@RequestParam("ids") Collection<Long> ids);
}
