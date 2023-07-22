package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.Result;
import com.treat.entity.Organs;

public interface IOrgansService extends IService<Organs> {
    Result separate(String fileName, String token);

    Result orgShow(String substring, String token);
}
