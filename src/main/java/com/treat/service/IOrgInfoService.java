package com.treat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.treat.dto.PdfDTO;
import com.treat.dto.Result;
import com.treat.entity.OrgInfo;

public interface IOrgInfoService extends IService<OrgInfo> {
    Result getPdfInfo(PdfDTO pdfDTO);
}
