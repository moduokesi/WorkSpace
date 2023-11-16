package com.treat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PdfDTO {
    String name;
    String gender;
    String age;
    String number;
    String checkDate;
    String fileName;
    String diagnose1;
    String diagnose2;
    List<String[]> list;
}
