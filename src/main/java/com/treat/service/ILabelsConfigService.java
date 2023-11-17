package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.LabelsDTO;
import com.treat.dto.Result;
import com.treat.entity.LabelsConfig;

public interface ILabelsConfigService extends IService<LabelsConfig> {
    boolean add(LabelsDTO labelsDTO,String account);

    Result queryAllLabels(String account);

    Result deleteById(String configId);


    Result updateLabelsConfig(LabelsDTO labelsDTO);
}
