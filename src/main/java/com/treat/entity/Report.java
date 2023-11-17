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
@TableName(value = "trt_report")
public class Report {
    @TableId(type = IdType.ASSIGN_ID)
    private String repId;
    private String repFileName;
    private String repName;
    private Integer repAge;
    private String repSex;
    private String repAccount;

}
