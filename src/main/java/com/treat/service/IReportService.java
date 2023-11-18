package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.Result;
import com.treat.entity.Report;

public interface IReportService extends IService<Report> {
    Result addReportInfo(Report report, String account);

    Result queryReport(String account);
}
