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
@TableName(value = "trt_organs")
public class Organs {
    @TableId(type = IdType.ASSIGN_ID)
    private String orgId;
    private String orgAccount;
    private String orgNum;
    private String orgName;
    private String orgOrigin;
}
