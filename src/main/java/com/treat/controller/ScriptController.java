package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.Script;
import com.treat.service.IScriptService;
import com.treat.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class ScriptController {
    @Autowired
    private IScriptService scriptService;

    @GetMapping("scriptShow")
    public Result scriptShow(HttpServletRequest request) {
        return scriptService.scriptShow(JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @PostMapping("scriptUpload")
    public Result scriptUpload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws IOException {
        return scriptService.scriptUpload(files, JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @PostMapping("scriptDelete")
    public Result scriptDelete(@RequestBody Script script) throws IOException {
        return scriptService.scriptDelete(script.getScpId());
    }
}
