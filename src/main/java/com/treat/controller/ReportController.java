package com.treat.controller;

import com.treat.dto.Result;
import com.treat.entity.Report;
import com.treat.service.IReportService;
import com.treat.service.impl.ReportServiceImpl;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {

    @Autowired
    public IReportService reportService;
    @PostMapping("/addReportInfo")
    public Result addReportInfo(@RequestBody Report report){
        return reportService.addReportInfo(report, UserHolder.getUser().getAccount());
    }

    @GetMapping("/queryReport")
    public Result queryReport(){
        return reportService.queryReport(UserHolder.getUser().getAccount());
    }
}
