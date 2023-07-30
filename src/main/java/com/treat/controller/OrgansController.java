package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.Organs;
import com.treat.entity.OutFiles;
import com.treat.service.IOrgansService;
import com.treat.utils.JwtUtil;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrgansController {

    @Autowired
    private IOrgansService organsService;

    @PostMapping("/separate")
    public Result separate(@RequestBody OutFiles outFiles) {
        return organsService.separate(outFiles.getFileName(), UserHolder.getUser().getAccount());
    }

    @GetMapping("orgShow")
    public Result orgShow(@RequestParam String fileName) {
        return organsService.orgShow(fileName.
                substring(0, fileName.indexOf(".")), UserHolder.getUser().getAccount());
    }

    @GetMapping("orgInfo")
    public Result orgInfo(@RequestParam String fileName) {
        return organsService.orgInfo(fileName.
                substring(0, fileName.indexOf(".")), UserHolder.getUser().getAccount());
    }
}
