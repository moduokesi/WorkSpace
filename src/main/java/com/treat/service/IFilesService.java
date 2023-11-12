package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.Result;
import com.treat.entity.InFiles;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface IFilesService extends IService<InFiles> {
    Result storeFiles(MultipartFile[] files, String account) throws IOException;

    Result fileshow(String account);

    Result deletefile(String fileId);

    Result getInfo(String fileId);
}
