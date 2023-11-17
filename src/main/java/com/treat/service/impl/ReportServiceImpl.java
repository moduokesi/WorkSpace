package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.Result;
import com.treat.entity.OrgInfo;
import com.treat.entity.Report;
import com.treat.mapper.ReportMapper;
import com.treat.service.IOrgInfoService;
import com.treat.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {

    @Autowired
    public IOrgInfoService orgInfoService;

    @Override
    public Result addReportInfo(Report report, String account) {
        String repFileName = report.getRepFileName();
        repFileName = repFileName.substring(0, repFileName.indexOf("."));
        System.out.println(repFileName);
        report.setRepFileName(repFileName);
        if (!isExist(report, account)) {
            report.setRepAccount(account);
            this.save(report);
            return Result.ok("操作成功");
        } else {
            return this.updateReport(report, account);
        }
    }

    @Override
    public Result queryReport(String account) {
        QueryWrapper<Report> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("rep_account",account);
        List<Report> reportList = this.list(queryWrapper);
        return Result.ok(reportList) ;

    }

    public boolean isExist(Report report, String account){
        QueryWrapper<Report> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("rep_file_name",report.getRepFileName())
                .eq("rep_account",account);
        int count = this.count(queryWrapper);
        if(count!=0)
            return true;
        else
            return false;
    }

    public Result updateReport(Report report, String account){
        QueryWrapper<Report> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("rep_file_name",report.getRepFileName())
                .eq("rep_account",account);
        Report oldReport = this.getOne(queryWrapper);
        oldReport.setRepAge(report.getRepAge());
        oldReport.setRepSex(report.getRepSex());
        oldReport.setRepName(report.getRepName());
        boolean flag = this.updateById(oldReport);
        if(flag)
            return Result.ok();
        else
            return Result.fail();
    }
}
