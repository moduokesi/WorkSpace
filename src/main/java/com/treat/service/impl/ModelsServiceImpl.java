package com.treat.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.Result;
import com.treat.entity.Models;
import com.treat.mapper.ModelsMapper;
import com.treat.service.IModelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelsServiceImpl extends ServiceImpl<ModelsMapper, Models> implements IModelsService {
    @Autowired
    private IModelsService modelsService;
    @Override
    public Result getModelInfo(String account) {
        if (account == null || account.equals("")) {
            return Result.fail("该用户不存在");
        }
        QueryWrapper<Models> wrapper = new QueryWrapper<>();
        wrapper.eq("model_account", account);

        List<Models> list = modelsService.list(wrapper);
        return Result.ok(list);
    }

    @Override
    public Result deleteModel(String modelId) {
        modelsService.removeById(modelId);
        return Result.ok();
    }

    @Override
    public Result getModelDetail(String modelId) {
        if (modelId == null || modelId.equals("")) {
            return Result.fail("该用户不存在");
        }
        Models model = modelsService.getById(modelId);
        return Result.ok(model);
    }

    @Override
    public Result addModel(Models model, String account) {
        boolean exist = this.isExist(model, account);
        if(!exist){
            model.setModelAccount(account);
            model.setModelUrl("./usr/local/treatdata/models/");
            model.setModelTime(new DateTime());
            this.save(model);
            return Result.ok("操作成功");
        }
        return Result.fail("此文件名已存在");
    }

    public boolean isExist(Models model, String account){
        QueryWrapper<Models> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("model_name",model.getModelName())
                .eq("model_account",account);
        int count = this.count(queryWrapper);
        if(count>0)
            return true;
        else
            return false;
    }

}
