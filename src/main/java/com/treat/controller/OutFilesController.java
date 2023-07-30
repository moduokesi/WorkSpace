package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.OutFiles;
import com.treat.service.IOrgansService;
import com.treat.service.IOutFilesService;
import com.treat.utils.JwtUtil;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class OutFilesController {
    @Autowired
    private IOutFilesService outFilesService;

    @Autowired
    private IOrgansService organsService;

    @GetMapping("queryOutFiles")
    public Result queryOutFiles(){
        return outFilesService.queryOutFiles(UserHolder.getUser().getAccount());
    }

    @PostMapping("segOutFiles")
    public Result segOutFiles(@RequestBody OutFiles outFiles) {
        return outFilesService.segOutFiles(outFiles.getFileName(),
                UserHolder.getUser().getAccount());
    }

    @PostMapping("columnShow")
    public Result columnShow(@RequestBody OutFiles outFiles) {
        return outFilesService.columnShow(outFiles.getFileName(),
                UserHolder.getUser().getAccount());
    }

    @PostMapping("pieShow")
    public Result pieShow(@RequestBody OutFiles outFiles) {
        return outFilesService.pieShow(outFiles.getFileName(),
                UserHolder.getUser().getAccount());
    }

    @PostMapping("barShow")
    public Result barShow(@RequestBody OutFiles outFiles) {
        return outFilesService.barShow(outFiles.getFileName(),
                UserHolder.getUser().getAccount());
    }
}
