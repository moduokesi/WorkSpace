package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.LabelsDTO;
import com.treat.entity.Labels;
import com.treat.entity.LabelsConfig;
import com.treat.mapper.LabelsMapper;
import com.treat.service.ILabelsConfigService;
import com.treat.service.ILabelsService;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LabelsServiceImpl extends ServiceImpl<LabelsMapper, Labels> implements ILabelsService {

    @Autowired
    private ILabelsConfigService labelsConfigService;
    @Override
    public boolean add(LabelsDTO labelsDTO) {
        ArrayList<Labels> arrayList = new ArrayList<>();
        QueryWrapper<LabelsConfig> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("config_name",labelsDTO.getConfigName());
        queryWrapper.eq("config_account", UserHolder.getUser().getAccount());
        LabelsConfig labelsConfig = labelsConfigService.getOne(queryWrapper);
        for(int i=0;i<labelsDTO.getLabelsList().size();i++){
            Labels labels = new Labels();
            labels.setConfigId(labelsConfig.getId());
            labels.setLabelsName(labelsDTO.getLabelsList().get(i).getLabelsName());
            labels.setLabelsNumber(labelsDTO.getLabelsList().get(i).getLabelsNumber());
            arrayList.add(labels);
        }
        boolean flag = this.saveBatch(arrayList);

        return flag;
    }

    @Override
    public List<Labels> queryLabelsByConfigId(String configId) {
        QueryWrapper<Labels> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("config_id",configId);
        queryWrapper.last("order by convert(labels_number ,SIGNED) asc");
        List<Labels> labelsList = this.list(queryWrapper);
        return labelsList;
    }

    @Override
    public boolean deleteByConfigId(String configId) {
        QueryWrapper<Labels> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("config_id",configId);
        int count = this.count(queryWrapper);
        boolean flag = this.remove(queryWrapper);
        if(count==0||flag)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateLabelsByConfigId(LabelsDTO labelsDTO) {
        boolean deleteFlag = this.deleteByConfigId(labelsDTO.getId());
        if(deleteFlag){
            this.add(labelsDTO);
            return true;
        }else{
            return false;
        }
    }
}
