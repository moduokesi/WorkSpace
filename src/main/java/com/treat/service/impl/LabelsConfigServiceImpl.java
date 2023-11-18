package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.LabelsDTO;
import com.treat.dto.MyException;
import com.treat.dto.Result;
import com.treat.entity.Labels;
import com.treat.entity.LabelsConfig;
import com.treat.mapper.LabelsConfigMapper;
import com.treat.service.ILabelsConfigService;
import com.treat.service.ILabelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LabelsConfigServiceImpl extends ServiceImpl<LabelsConfigMapper, LabelsConfig> implements ILabelsConfigService {
    @Autowired
    private ILabelsService labelsService;
    @Override
    public boolean add(LabelsDTO labelsDTO, String account) {
        LabelsConfig labelsConfig = new LabelsConfig();
        if(!isExist(labelsDTO,account)){
            labelsConfig.setConfigName(labelsDTO.getConfigName());
            labelsConfig.setConfigDescription(labelsDTO.getConfigDescription());
            labelsConfig.setConfigAccount(account);
            this.save(labelsConfig);
            return true;
        }else{
          return false;
        }
    }
    @Override
    public Result queryAllLabels(String account) {
        List<LabelsDTO> labelsDTOList=new ArrayList<>();
        QueryWrapper<LabelsConfig> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("config_account",account);
        List<LabelsConfig> labelsConfigList = this.list(queryWrapper);
        if (labelsConfigList!=null&&labelsConfigList.size()!=0){
            for (LabelsConfig labelsConfig:labelsConfigList) {
                List<Labels> labelsList = labelsService.queryLabelsByConfigId(labelsConfig.getId());
                LabelsDTO labelsDTO = new LabelsDTO();
                labelsDTO.setLabelsList(labelsList);
                labelsDTO.setId(labelsConfig.getId());
                labelsDTO.setConfigName(labelsConfig.getConfigName());
                labelsDTO.setConfigDescription(labelsConfig.getConfigDescription());
                labelsDTOList.add(labelsDTO);
            }
            return Result.ok(labelsDTOList);
        }else
            return Result.fail("暂无标签配置");

    }
    @Override
    public Result deleteById(String configId) {
        boolean labelsFlag = labelsService.deleteByConfigId(configId);
        boolean configFlag = this.removeById(configId);
        if(labelsFlag&&configFlag){
            return Result.ok("删除成功");
        }
        return Result.ok();
    }

    @Override
    public Result updateLabelsConfig(LabelsDTO labelsDTO) {
        String configId = labelsDTO.getId();
        QueryWrapper<LabelsConfig> queryWrapper=new QueryWrapper<>();
        LabelsConfig labelsConfig = this.getById(configId);
        boolean configFlag = this.updateById(labelsConfig);
        boolean labelsFlag = labelsService.updateLabelsByConfigId(labelsDTO);
        if(configFlag&&labelsFlag){
            return Result.ok("修改成功");
        }else{
            return Result.fail("修改失败");
        }
    }

    public boolean isExist(LabelsDTO labelsDTO, String account){
        QueryWrapper<LabelsConfig> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("config_name",labelsDTO.getConfigName())
                .eq("config_account",account);
        int count = this.count(queryWrapper);
        if(count!=0)
            return true;
        return false;
    }
}
