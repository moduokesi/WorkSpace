package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.Models;
import com.treat.service.IModelsService;
import com.treat.service.impl.ModelsServiceImpl;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ModelsController {
    @Autowired
    private IModelsService modelsService;

    @GetMapping("/getModelInfo")
    public Result GetModelInfo() {
        return modelsService.getModelInfo(UserHolder.getUser().getAccount());
    }

    @PostMapping("/modelInfo")
    public Result ModelInfo(@RequestParam("files") MultipartFile[] files) {

        return Result.ok();
    }

    @PostMapping("/getModelDetail")
    public Result GetModelDetail(@RequestBody Models model) {
        return modelsService.getModelDetail(model.getModelId());
    }

    @PostMapping("/deleteModel")
    public Result DeleteModel(@RequestBody Models model){
        return modelsService.deleteModel(model.getModelId());
    }


    @PostMapping("/addModel")
    public Result AddModel(@RequestBody Models model){
        if(model.getModelName().equals("")||model.getModelDescription().equals("")){
            return Result.fail("请检查必填项");
        }
        return modelsService.addModel(model,UserHolder.getUser().getAccount());
    }

    @PostMapping("/uploadModel")
    public Result uploadModel(@RequestParam("file") MultipartFile[] multipartFiles){
        return Result.ok("操作成功");
    }
}

