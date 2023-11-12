package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.LabelsDTO;
import com.treat.entity.Labels;

import java.util.List;

public interface ILabelsService extends IService<Labels> {
    boolean add(LabelsDTO labelsDTO);

    List<Labels> queryLabelsByConfigId(String configId);

    boolean deleteByConfigId(String configId);
}
