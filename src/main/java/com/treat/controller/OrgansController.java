package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.Organs;
import com.treat.entity.OutFiles;
import com.treat.service.IOrgansService;
import com.treat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrgansController {

    @Autowired
    private IOrgansService organsService;

    @PostMapping("/separate")
    public Result separate(@RequestBody OutFiles outFiles, HttpServletRequest request) {
        return organsService.separate(outFiles.getFileName(), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @GetMapping("orgShow")
    public Result orgShow(@RequestParam String fileName, HttpServletRequest request) {
        return organsService.orgShow(fileName.substring(0, fileName.indexOf(".")), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @GetMapping("orgInfo")
    public Result orgInfo(@RequestParam String fileName,HttpServletRequest request) {
        return organsService.orgInfo(fileName.substring(0, fileName.indexOf(".")), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }
}
