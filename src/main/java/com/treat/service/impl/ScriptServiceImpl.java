package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.Result;
import com.treat.entity.InFiles;
import com.treat.entity.Script;
import com.treat.mapper.ScriptMapper;
import com.treat.service.IScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ScriptServiceImpl extends ServiceImpl<ScriptMapper, Script> implements IScriptService {
    @Autowired
    private IScriptService scriptService;
    @Override
    public Result scriptShow(String account) {
        QueryWrapper<Script> wrapper = new QueryWrapper<>();
        wrapper.eq("scp_account", account);

        return Result.ok(scriptService.list(wrapper));
    }

    @Override
    public Result scriptUpload(MultipartFile[] files, String account) throws IOException {
        if (files != null && files.length > 0) {
            for(MultipartFile file: files) {
                //获取文件数据
                byte[] data = file.getBytes();

                // 创建目录
                File directory = new File("..\\treatdata\\" + account + "\\pyfile\\");
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                Script script = new Script();
                script.setScpAccount(account);
                // 设置文件名
                String originalFileName = file.getOriginalFilename();
                script.setScpName(originalFileName);

                System.out.println(directory.getPath());
                // 写入文件
                File outputFile = new File(directory.getPath(), originalFileName);
                FileOutputStream outputStream = new FileOutputStream(outputFile);
                outputStream.write(data);
                outputStream.close();

                scriptService.save(script);
            }
            return Result.ok();
        } else {
            return Result.fail("未接收到文件");
        }
    }

    @Override
    public Result scriptDelete(String scpId) {
        if (scpId != null && !scpId.equals("")) {
            scriptService.removeById(scpId);
            return Result.ok();
        } else {
            return Result.fail("未查询到该文件！");
        }
    }
}
