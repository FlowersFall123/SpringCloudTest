package com.itheima.mp.service;

import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/*
 * @Auther:fz
 * @Date:2025/10/1
 * @Description:
 */
@SpringBootTest
class IUserServiceTest {
    @Autowired
    IUserService iUserService;

    @Test
    void testInsert() {
        User user = new User();
        /**
         * @TableId(value = "id",type = IdType.AUTO)
         */
//        user.setId(5L);//User里面设置了自增
        user.setUsername("lisi");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        iUserService.save(user);
    }
}