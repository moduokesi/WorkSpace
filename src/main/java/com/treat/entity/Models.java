package com.treat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "trt_models")
public class Models {
    @TableId(type = IdType.ASSIGN_ID)
    private String modelId;
    private String modelAccount;
    private String modelName;
    private String modelDescription;
    private String modelUrl;
    private Date modelTime;
}
