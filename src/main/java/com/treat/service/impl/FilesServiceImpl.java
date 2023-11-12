package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.Result;
import com.treat.entity.InFiles;
import com.treat.entity.OutFiles;
import com.treat.mapper.FilesMapper;
import com.treat.service.IFilesService;
import com.treat.service.IOutFilesService;
import com.treat.utils.CacheClient;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FilesServiceImpl extends ServiceImpl<FilesMapper, InFiles> implements IFilesService {
    @Autowired
    private IFilesService filesService;

    @Autowired
    private IOutFilesService outFilesService;

    @Resource
    private CacheClient cacheClient;

    @Override
    public Result storeFiles(MultipartFile[] files, String account) throws IOException {
        //存在异常返回错误
        if (files == null || files.length <= 0) {
            return Result.fail("未接收到文件");
        }

        for(MultipartFile file: files) {
            //获取文件数据
            byte[] data = file.getBytes();

            // 创建目录
            File directory = new File("..\\treatdata\\" + account + "\\infile\\");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // 存储数据对象
            InFiles newFile = new InFiles();
            newFile.setFileAccount(account);
            newFile.setFileUrl("..\\treatdata\\" + account + "\\infile\\");

            // 设置文件名和文件类型
            String originalFileName = file.getOriginalFilename();
            newFile.setFileName(originalFileName.substring(0, originalFileName.lastIndexOf(".")));
            newFile.setFileType(originalFileName.substring(originalFileName.lastIndexOf(".") + 1));
            // 写入文件
            File outputFile = new File(directory.getPath(), originalFileName);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(data);
            outputStream.close();

            // 将B转换单位
            double fileSize = Double.valueOf(file.getSize());
            double fileSizeInKB = fileSize / 1024; // 转换为KB
            double fileSizeInMB = fileSizeInKB / 1024; // 转换为MB

            if (fileSizeInKB >= 1) {
                //如果小于MB
                if (fileSizeInMB < 1) {
                    String fileKB = String.valueOf(fileSizeInKB);
                    int index = fileKB.indexOf(".");
                    newFile.setFileSize(fileKB.substring(0, index + 2) + "KB"); // 设置文件大小为KB
                }else {
                    String fileMB = String.valueOf(fileSizeInMB);
                    int index = fileMB.indexOf(".");
                    newFile.setFileSize(fileMB.substring(0, index + 2) + "MB"); // 设置文件大小为MB
                }
            } else {
                newFile.setFileSize(String.valueOf(fileSize) + "B");
            }


            filesService.save(newFile);
        }
        return Result.ok();
    }

    @Override
    public Result fileshow(String account) {
        if (account != null && !account.equals("")) {
            QueryWrapper<InFiles> wrapper = new QueryWrapper<>();
            wrapper.eq("file_account", account);

            List<InFiles> files = filesService.list(wrapper);

            return Result.ok(files);
        }
        return Result.fail("未查询到账号");
    }

    @Override
    public Result deletefile(String fileId) {
        if (fileId == null || fileId.equals("")) {
            return Result.fail("未查询到该文件！");
        }
        String file = filesService.getById(fileId).getFileName();
        String fileName = file;

        //检查是否存在后缀
        int dotIndex = file.indexOf(".");
        if (dotIndex != -1) {
            fileName = file.substring(0, dotIndex);
        }

        String fileAccount = UserHolder.getUser().getAccount();
        filesService.removeById(fileId);

        QueryWrapper<OutFiles> wrapper = new QueryWrapper<>();
        wrapper.eq("file_account", fileAccount).eq("file_name", fileName);

        //如果stl文件不存在
        outFilesService.remove(wrapper);

        return Result.ok();
    }

    @Override
    public Result getInfo(String fileId) {
        InFiles file = filesService.getById(fileId);

        if (fileId == null || fileId.equals("") || file == null) {
            return Result.fail("未查询到该文件！");
        }

//        String url = file.getFileUrl() + file.getFileName() + "." + file.getFileType();
        return Result.ok(file);
    }
}
