package com.treat.dto;


import com.treat.entity.Labels;
import com.treat.entity.LabelsConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class LabelsDTO  {
    private String id;
    private String configName;
    private String configDescription;
    private List<Labels> labelsList;

}
