package com.itheima.mp.service.Impl;

/*
 * @Auther:fz
 * @Date:2025/10/1
 * @Description:
 */


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public void deductBalanceById(Long id, Integer money) {
        //查询用户余额
        User user=getById(id);

        //检验用户状态
        if(user==null||user.getStatus() == 2){
            throw new RuntimeException("用户状态异常");
        }

        //检验余额是否充足
        if(user.getBalance() < money){
            throw new RuntimeException("余额不足");
        }

        //扣除余额
//        baseMapper.deductBalanceById(id,money);
        int remainBalance=user.getBalance()-money;
        lambdaUpdate()
                .set(User::getBalance,remainBalance)
                .set(remainBalance==0,User::getStatus,2)
                .eq(User::getId,id)
                .update();
    }

    @Override
    public List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance) {
        return lambdaQuery()
                .like(name!=null,User::getUsername,name)
                .eq(status!=null,User::getStatus,status)
                .ge(minBalance!=null,User::getBalance,minBalance)//大于最小值
                .le(maxBalance!=null,User::getBalance,maxBalance)//小于最大值
                .list();
    }


}
