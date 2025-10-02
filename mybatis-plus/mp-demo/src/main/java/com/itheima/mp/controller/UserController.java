package com.itheima.mp.controller;

/*
 * @Auther:fz
 * @Date:2025/10/1
 * @Description:
 */

import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api("用户管理接口")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;

    @PostMapping
    @ApiOperation("新增用户接口")
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        User user=new User();
        BeanUtils.copyProperties(userFormDTO,user);
        iUserService.save(user);
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除用户接口")
    public void deleteUser(@ApiParam("用户id") @PathVariable Long id){
        iUserService.removeById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户接口")
    public UserVO queryUserById(@ApiParam("用户id") @PathVariable Long id){
        User user = iUserService.getById(id);
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @GetMapping()
    @ApiOperation("根据id批量查询用户接口")
    public List<UserVO> queryUserByIds(@ApiParam("用户id集合") @RequestParam("ids") List<Long>ids){
        List<User> userList = iUserService.listByIds(ids);
        return userList.stream().map((user)->{
            UserVO vo=new UserVO();
            BeanUtils.copyProperties(user,vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @PutMapping("/{id}/deduction/{money}")
    @ApiOperation("扣减用户余额接口")
    public void deductBalanceById(@ApiParam("用户id") @PathVariable("id") Long id,
                                @ApiParam("扣减金额") @PathVariable("money") Integer money){
        iUserService.deductBalanceById(id,money);

    }

    @GetMapping("/list")
    @ApiOperation("根据复杂条件查询用户接口")
    public List<User> queryUsers(@ApiParam("用户数据") @RequestBody UserQuery userQuery){
        return iUserService.queryUsers(userQuery.getName(),userQuery.getStatus(),userQuery.getMinBalance(),userQuery.getMaxBalance());
    }
}
