package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.OutFiles;
import com.treat.service.IOrgansService;
import com.treat.service.IOutFilesService;
import com.treat.utils.JwtUtil;
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
    public Result queryOutFiles(HttpServletRequest request){
        return outFilesService.queryOutFiles(JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @PostMapping("segOutFiles")
    public Result segOutFiles(@RequestBody OutFiles outFiles, HttpServletRequest request) {
        return outFilesService.segOutFiles(outFiles.getFileName(), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @PostMapping("columnShow")
    public Result columnShow(@RequestBody OutFiles outFiles, HttpServletRequest request) {
        return outFilesService.columnShow(outFiles.getFileName(), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @PostMapping("pieShow")
    public Result pieShow(@RequestBody OutFiles outFiles, HttpServletRequest request) {
        return outFilesService.pieShow(outFiles.getFileName(), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }

    @PostMapping("barShow")
    public Result barShow(@RequestBody OutFiles outFiles, HttpServletRequest request) {
        return outFilesService.barShow(outFiles.getFileName(), JwtUtil.getUser(request.getHeader("token")).getAccount());
    }
}
