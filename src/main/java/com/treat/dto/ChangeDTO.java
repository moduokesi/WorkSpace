package com.treat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDTO {
    private String userAccount;
    private String userPhone;
    private String userEmail;
    private String userSecproblem;
}
