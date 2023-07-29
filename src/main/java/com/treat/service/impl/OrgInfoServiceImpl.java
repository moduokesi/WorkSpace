package com.treat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.entity.OrgInfo;
import com.treat.mapper.OrgInfoMapper;
import com.treat.service.IOrgInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfo> implements IOrgInfoService {
}
