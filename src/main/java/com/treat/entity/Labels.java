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
@TableName(value = "trt_labels")
public class Labels {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String labelsName;
    private String labelsNumber;
    private String configId;
}
