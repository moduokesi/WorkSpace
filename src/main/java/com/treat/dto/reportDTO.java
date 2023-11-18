package com.treat.dto;

import com.treat.entity.OrgInfo;
import lombok.Data;

import java.util.List;

@Data
public class reportDTO {
    private String repFileName;
    private String repName;
    private Integer repAge;
    private String repSex;
    private String repAccount;
    private List<OrgInfo> orgInfoList;
}
