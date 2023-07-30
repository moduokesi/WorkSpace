package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.Script;
import com.treat.service.IScriptService;
import com.treat.utils.JwtUtil;
import com.treat.utils.UserHolder;
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
    public Result scriptShow() {
        return scriptService.scriptShow(UserHolder.getUser().getAccount());
    }

    @PostMapping("scriptUpload")
    public Result scriptUpload(@RequestParam("file") MultipartFile[] files) throws IOException {
        return scriptService.scriptUpload(files,
                UserHolder.getUser().getAccount());
    }

    @PostMapping("scriptDelete")
    public Result scriptDelete(@RequestBody Script script) throws IOException {
        return scriptService.scriptDelete(script.getScpId());
    }
}
