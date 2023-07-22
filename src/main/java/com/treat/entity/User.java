package com.treat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.treat.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "trt_user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private String userId;
    private String userName;
    private String userAccount;
    private String userPwd;
    private String userPhone;
    private String userEmail;
    private String userSecproblem;
}
