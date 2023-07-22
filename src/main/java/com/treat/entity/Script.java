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
@TableName(value = "trt_script")
public class Script {
    @TableId(type = IdType.ASSIGN_ID)
    private String scpId;
    private String scpName;
    private String scpAccount;
}
