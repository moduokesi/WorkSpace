package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.Result;
import com.treat.entity.Script;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IScriptService extends IService<Script> {
    Result scriptShow(String account);

    Result scriptUpload(MultipartFile[] files, String account) throws IOException;

    Result scriptDelete(String scpId);

}
