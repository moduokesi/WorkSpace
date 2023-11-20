package com.treat.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.treat.dto.PdfDTO;
import com.treat.dto.Result;
import com.treat.service.IFilesService;
import com.treat.service.IOrgInfoService;
import com.treat.utils.PyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DiagnoseController {
    @Autowired
    private IOrgInfoService orgInfoService;

    @GetMapping("/diagnose")
    public Result sendMessage(@RequestParam("message") String message) {
        System.out.println(message);
        String result = PyUtil.Diagnose(message);

        String cleanResult = result.replace("*", "");

        // 将清理后的结果解析为 JSONObject
        JSONObject json = JSONUtil.parseObj(cleanResult);

        return Result.ok(json.get("result"));
    }


    @PostMapping("/getPdfInfo")
    public Result getPdfInto(@RequestBody PdfDTO pdfDTO) {

        return orgInfoService.getPdfInfo(pdfDTO);
    }
}
