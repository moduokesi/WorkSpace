package com.treat.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.treat.dto.MyException;
import com.treat.dto.UserDTO;
import com.treat.utils.JwtUtil;
import com.treat.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.treat.utils.RedisContants.LOGIN_TOKEN_KEY;
import static com.treat.utils.RedisContants.LOGIN_TOKEN_TTL;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //获取token串
        String token = request.getHeader(JwtUtil.token);
        StringBuffer requestURL = request.getRequestURL();
        System.out.println("拦截的请求："+requestURL);

        // 根据token串获取用户Map
        Map<Object, Object> userMap = stringRedisTemplate.
                opsForHash().entries(LOGIN_TOKEN_KEY + token);

        //判断用户是否存在或者token是否超时
        if (userMap.isEmpty()) {
            throw new MyException("超时或不合法的Token！");
        }

        //将map转为javaBean
        UserDTO user = BeanUtil.fillBeanWithMap(userMap, new UserDTO(),false);

        response.setHeader(JwtUtil.token, token);
        response.setHeader("Access-Control-Expose-Headers", JwtUtil.token); // 将token暴露出来(用于跨域)

        // 存入到threadlocal中
        UserHolder.saveUser(user);
        // 刷新token有效期
        stringRedisTemplate.expire(LOGIN_TOKEN_KEY + token, LOGIN_TOKEN_TTL, TimeUnit.MINUTES);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户信息
        UserHolder.removeUser();
    }
}
