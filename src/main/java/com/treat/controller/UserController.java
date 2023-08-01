package com.treat.controller;

import cn.hutool.core.bean.BeanUtil;
import com.treat.dto.ChangeDTO;
import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.User;
import com.treat.service.IUserService;
import com.treat.utils.JwtUtil;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        return userService.login(userDTO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/forget")
    public Result forget(@RequestBody ChangeDTO changeDTO) {
        return userService.forget(changeDTO);
    }

    @PostMapping("/change")
    public Result change(@RequestBody UserDTO userDTO, HttpServletRequest request) {

        userDTO.setAccount(JwtUtil.getUser(request.getHeader("token")).getAccount());
        return userService.change(userDTO);
    }

    @PostMapping("/changeSec")
    public Result changSec(@RequestBody ChangeDTO changeDTO) {
        changeDTO.setUserAccount(UserHolder.getUser().getAccount());
        return userService.changeSec(changeDTO);
    }

    @GetMapping("/personInfo")
    public Result personInfo() {
        return userService.personInfo(UserHolder.getUser().getAccount());
    }
}
