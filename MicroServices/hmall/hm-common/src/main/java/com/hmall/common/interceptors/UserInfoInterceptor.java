package com.hmall.common.interceptors;

/*
 * @Auther:fz
 * @Date:2025/10/5
 * @Description:
 */

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取登陆用户信息
        String userId = request.getHeader("userId");
        //2.判断是否获取了用户，如果就存到ThreadLocal
        if(StrUtil.isNotBlank(userId)){
            UserContext.setUser(Long.valueOf(userId));
        }
        //3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //4.清理ThreadLocal
        UserContext.removeUser();
    }
}
