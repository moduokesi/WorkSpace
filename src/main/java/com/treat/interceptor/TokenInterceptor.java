package com.treat.interceptor;

import com.treat.dto.MyException;
import com.treat.dto.UserDTO;
import com.treat.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader(JwtUtil.token);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("拦截的请求："+requestURL);
        UserDTO user = JwtUtil.getUser(token);// 根据token获取用户对象

        //判断用户是否存在或者token是否超时
        if (user == null) {
            throw new MyException("超时或不合法的Token！");
        }

        String newToken = JwtUtil.getJwtToken(user);           // 生成一个新的token
        response.setHeader(JwtUtil.token, newToken);
        response.setHeader("Access-Control-Expose-Headers", JwtUtil.token); // 将token暴露出来(用于跨域)

        request.setAttribute("user", user);   //传入对应角色的信息
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
