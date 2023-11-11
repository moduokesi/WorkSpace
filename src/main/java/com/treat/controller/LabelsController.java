package com.treat.controller;

import com.treat.dto.LabelsDTO;
import com.treat.dto.Result;
import com.treat.entity.LabelsConfig;
import com.treat.service.ILabelsConfigService;
import com.treat.service.ILabelsService;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LabelsController {

    @Autowired
    private ILabelsConfigService labelsConfigService;
    @Autowired
    private ILabelsService labelsService;
    @PostMapping("/addLabels")
    public Result addLabels(@RequestBody LabelsDTO labelsDTO){
        if(labelsDTO.getConfigName()==""||labelsDTO.getConfigDescription()==""){
            return Result.fail("请检查必填项是否填写");
        }
        System.out.println(labelsDTO);
        boolean configFlag = labelsConfigService.add(labelsDTO, UserHolder.getUser().getAccount());
        if(!configFlag)
            return Result.fail("配置名已存在");
        boolean labelFlag = labelsService.add(labelsDTO);
        if(configFlag&&labelFlag){
            return Result.ok("操作成功");
        }else{
            return Result.fail("操作失败");
        }
    }

    @GetMapping("/queryAllLabels")
    public Result queryLabels(){
        return labelsConfigService.queryAllLabels(UserHolder.getUser().getAccount());
    }

    @PostMapping("/deleteConfig")
    public Result deleteConfig(@RequestBody LabelsDTO labelsDTO){
        return labelsConfigService.deleteById(labelsDTO.getId());
    }

    @PostMapping("/updateLabels")
    public Result updateLabels(@RequestBody LabelsDTO labelsDTO){
        Result resultDelete = this.deleteConfig(labelsDTO);
        Result resultAdd = this.addLabels(labelsDTO);
        if(resultDelete.getStatus()==200&&resultAdd.getStatus()==200){
            return Result.ok("操作成功");
        }else{
            if(resultDelete.getStatus()!=200)
                return Result.fail(resultDelete.getStatus(),resultDelete.getMsg());
            else
                return Result.fail(resultAdd.getStatus(),resultAdd.getMsg());
        }
    }


}
