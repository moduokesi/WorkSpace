package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.ImageDTO;
import com.treat.dto.Result;
import com.treat.entity.OutFiles;

import java.util.Map;

public interface IOutFilesService extends IService<OutFiles> {

    Result queryOutFiles(String token);

    Result segOutFiles(String name, String account);

    Result pieShow(String fileName, String token);

    Result barShow(String fileName, String token);

    Result columnShow(String fileName, String token);

    Result queryOutOne(String fileName, String account);

    Result segExists(String fileName, String account);

    Result uploadImage(ImageDTO imageDTO);
}
