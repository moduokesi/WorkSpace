package com.treat.controller;

import com.treat.dto.ChangeDTO;
import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.User;
import com.treat.service.IUserService;
import com.treat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {

        //生成一个token
        Map<String, String> map = new HashMap<>();
        map.put(JwtUtil.token, JwtUtil.getJwtToken(userDTO));

        if (userService.login(userDTO)) {
            return Result.ok(map);
        }else {
            return Result.fail();
        }
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
    public Result changSec(@RequestBody ChangeDTO changeDTO, HttpServletRequest request) {
        changeDTO.setUserAccount(JwtUtil.getUser(request.getHeader("token")).getAccount());
        return userService.changeSec(changeDTO);
    }

    @GetMapping("/personInfo")
    public Result personInfo(HttpServletRequest request) {
        return userService.personInfo(JwtUtil.getUser(request.getHeader("token")).getAccount());
    }
}
