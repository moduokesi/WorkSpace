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
@TableName(value = "trt_orginfo")
public class OrgInfo {
    @TableId(type = IdType.ASSIGN_ID)
    private String orgId;
    private String orgOrgan;
    private String orgAccount;
    private String orgName;
    private String orgDiameter;
    private String orgSurface;
    private String orgVolume;
}
