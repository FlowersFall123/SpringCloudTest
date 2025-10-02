package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
//        user.setId(5L);
        user.setUsername("lisi");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .select("id","username","info","balance")
                .like("username","o")
                .ge("balance", 1000);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        //更新条件
        List<Long> ids=List.of(1L,2L,4L);
        int amount=200;
        //2.定义条件
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>().in("id",ids);
        //3.调用自定义sql方法
        userMapper.updateBalance(queryWrapper,amount);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }
}