package com.treat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "trt_labels_config")
public class LabelsConfig {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String configName;
    private String configDescription;
    private String configAccount;

}
