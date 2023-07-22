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
@TableName(value = "trt_outfiles")
public class OutFiles {
    @TableId(type = IdType.ASSIGN_ID)
    private String fileId;
    private String fileAccount;
    private String fileUrl;
    private String fileName;
    private String fileSize;
    private String fileType;

}
