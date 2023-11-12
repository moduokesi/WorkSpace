package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.Result;
import com.treat.entity.Models;

public interface IModelsService extends IService<Models> {
    Result getModelInfo(String account);

    Result deleteModel(String modelId);

    Result getModelDetail(String modelId);
}
