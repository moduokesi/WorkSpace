package com.treat.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.treat.dto.Result;
import com.treat.utils.PyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DiagnoseController {

    @GetMapping("/diagnose")
    public Result sendMessage(@RequestParam("message") String message) {
        System.out.println(message);
//        return Result.ok();
        JSONObject json = JSONUtil.parseObj(PyUtil.Diagnose(message));
        return Result.ok(json.get("result"));

    }

    


}
