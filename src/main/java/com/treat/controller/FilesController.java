package com.treat.controller;

import com.treat.dto.Result;
import com.treat.dto.UserDTO;
import com.treat.entity.InFiles;
import com.treat.service.IFilesService;
import com.treat.service.ILabelsConfigService;
import com.treat.utils.JwtUtil;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
public class FilesController {
    @Autowired
    private IFilesService filesService;

    @PostMapping("/segt")
    public Result handleFileUpload(@RequestParam("files") MultipartFile[] files) throws IOException {
        // 处理接收到的文件
        return filesService.storeFiles(files, UserHolder.getUser().getAccount());
    }

    @GetMapping("/filesInfo")
    public Result FileShow(HttpServletRequest request) {
        // 处理接收到的文件
        return filesService.fileshow(UserHolder.getUser().getAccount());
    }

    @GetMapping("/getInfo")
    public Result GetInfo(@RequestParam("fileId") String fileId) {
        // 处理接收到的文件
        return filesService.getInfo(fileId);
    }

    @PostMapping("/deletefile")
    public Result deletefile(@RequestBody InFiles inFile) {

        return filesService.deletefile(inFile.getFileId());
    }

}
