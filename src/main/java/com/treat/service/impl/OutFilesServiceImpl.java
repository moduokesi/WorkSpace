package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.ImageDTO;
import com.treat.dto.Result;
import com.treat.entity.InFiles;
import com.treat.entity.Organs;
import com.treat.entity.OutFiles;
import com.treat.mapper.OutFilesMapper;
import com.treat.service.IOrgansService;
import com.treat.service.IOutFilesService;
import com.treat.utils.PyUtil;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class OutFilesServiceImpl extends ServiceImpl<OutFilesMapper, OutFiles> implements IOutFilesService {
    @Autowired
    private IOutFilesService outFilesService;

    @Override
    public synchronized Result segOutFiles(String fileName, String fileAccount) {

        OutFiles outFile = new OutFiles();
        outFile.setFileAccount(fileAccount);
        outFile.setFileName(fileName.substring(0, fileName.indexOf(".")));

        QueryWrapper<OutFiles> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name", outFile.getFileName());
        wrapper.eq("file_account", fileAccount);

        // 如果已经分割过
        if (outFilesService.getOne(wrapper) != null) {
            return Result.fail("该文件已进行过分割操作！");
        }

        //调用模型
        PyUtil.SegFiles(fileName, fileAccount);
        //调用python脚本，将结果分为stl和nii文件
        PyUtil.NiiToStl(fileName, fileAccount);

        // 创建目录
        File directory = new File("..\\treatdata\\" + fileAccount + "\\outfile\\");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        if (outFilesService.getOne(wrapper) != null) {
            return Result.ok();
        }

        outFile.setFileUrl("../treatdata/" + fileAccount + "/outfile/");
        outFile.setFileType("stl");

        String stlFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + fileAccount +  "\\outfile\\" + outFile.getFileName() + ".stl";
        System.out.println(stlFilePath);
        File stlFile = new File(stlFilePath);
        long size = stlFile.length();

        // 将B转换单位
        double fileSize = Double.valueOf(size);
        double fileSizeInKB = fileSize / 1024; // 转换为KB
        double fileSizeInMB = fileSizeInKB / 1024; // 转换为MB

        if (fileSizeInKB >= 1) {
            //如果小于MB
            if (fileSizeInMB < 1) {
                String fileKB = String.valueOf(fileSizeInKB);
                int index = fileKB.indexOf(".");
                outFile.setFileSize(fileKB.substring(0, index + 2) + "KB"); // 设置文件大小为KB
            }else {
                String fileMB = String.valueOf(fileSizeInMB);
                int index = fileMB.indexOf(".");
                outFile.setFileSize(fileMB.substring(0, index + 2) + "MB"); // 设置文件大小为MB
            }
        } else {
            outFile.setFileSize(String.valueOf(fileSize) + "B");
        }

        outFilesService.save(outFile);

        return Result.ok();
    }

    @Override
    public Result pieShow(String fileName, String fileAccount) {

        ArrayList<Double> array = PyUtil.OrganSurfaceMeasure(fileName, fileAccount);
        double[] nums = new double[array.size()];

        for (int i = 0; i < array.size(); i++) {
            nums[i] = array.get(i);
        }

        return Result.ok(nums);
    }

    @Override
    public Result barShow(String fileName, String fileAccount) {
        ArrayList<Double> array = PyUtil.OrganVolumnMeasure(fileName, fileAccount);
        double[] nums = new double[array.size()];

        for (int i = 0; i < array.size(); i++) {
            nums[i] = array.get(i);
        }

        return Result.ok(nums);
    }

    @Override
    public Result columnShow(String fileName, String fileAccount) {
        ArrayList<Double> array = PyUtil.OrganDiameterMeasure(fileName, fileAccount);
        double[] nums = new double[array.size()];

        for (int i = 0; i < array.size(); i++) {
            nums[i] = array.get(i);
        }

        return Result.ok(nums);
    }

    @Override
    public Result queryOutOne(String fileName, String account) {
        QueryWrapper<OutFiles> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name", fileName).eq("file_account", account);

        if (outFilesService.getOne(wrapper) == null) {
            return Result.fail();
        }

        return Result.ok();
    }

    @Override
    public Result segExists(String fileName, String fileAccount) {
        OutFiles outFile = new OutFiles();
        outFile.setFileAccount(fileAccount);
        outFile.setFileName(fileName.substring(0, fileName.indexOf(".")));

        QueryWrapper<OutFiles> wrapper = new QueryWrapper<>();
        wrapper.eq("file_name", outFile.getFileName());
        wrapper.eq("file_account", fileAccount);

        // 如果已经分割过
        if (outFilesService.getOne(wrapper) != null) {
            return Result.fail();
        }else {
            return Result.ok();
        }
    }

    @Override
    public Result uploadImage(ImageDTO imageDTO) {
        String account = UserHolder.getUser().getAccount();
        // 创建目录
        File directory = new File("..\\treatdata\\" + account + "\\imgfile\\");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        int indexOfInfile = imageDTO.getImageUrl().indexOf("infile");
        String result = "";

        if (indexOfInfile != -1) {
            result = imageDTO.getImageUrl().substring(indexOfInfile + "infile".length() + 1);
        }
        String fileName = result.substring(0, result.indexOf('.'));
        String imgFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\"
                + account + "\\imgfile\\" + fileName + ".png";

        // 移除Base64字符串前缀
        String base64Image = imageDTO.getImageData().replaceFirst("data:image/png;base64,", "");

        // 将Base64字符串解码为字节数组
        byte[] imageDataBytes;
        try {
            imageDataBytes = Base64.getDecoder().decode(base64Image);
        } catch (IllegalArgumentException e) {
            // 处理Base64解码异常
            e.printStackTrace();
            // 返回错误结果
            return Result.fail("Base64解码失败");
        }

        // 将图像数据保存到文件
        try (FileOutputStream fos = new FileOutputStream(imgFilePath)) {
            fos.write(imageDataBytes);
        } catch (IOException e) {
            // 处理IO异常
            e.printStackTrace();
            // 返回错误结果
            return Result.fail("保存图像文件失败");
        }

        return Result.ok();
    }


    @Override
    public Result queryOutFiles(String account) {
        QueryWrapper<OutFiles> wrapper = new QueryWrapper<>();
        wrapper.eq("file_account", account);

        return Result.ok(outFilesService.list(wrapper));
    }

}
