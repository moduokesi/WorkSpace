package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.dto.Result;
import com.treat.entity.Organs;
import com.treat.mapper.OrgansMapper;
import com.treat.service.IOrgansService;
import com.treat.utils.PyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrgansServiceImpl extends ServiceImpl<OrgansMapper, Organs> implements IOrgansService {
    @Autowired
    private IOrgansService organsService;

    @Override
    public Result separate(String name, String account) {
        QueryWrapper<Organs> wrapper = new QueryWrapper<>();
        wrapper.eq("org_account", account);
        wrapper.eq("org_origin", name.substring(0, name.lastIndexOf(".")));
        File directory = new File("D:\\Workspaces\\Project\\treattest\\treatdata\\" + account + "\\outfile\\" + name.substring(0, name.indexOf("."))); // 替换为您要读取的目录路径

        if (organsService.list(wrapper).size() > 0) {
            return Result.fail("该文件已进行过分离操作！");
        }
        // 检查目录是否存在
        if (directory.exists() && directory.isDirectory()) {
            // 获取目录下的所有文件和子目录
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals("nii")) {
                        continue;
                    }
                    String fileName = file.getName();
                    String numOrder = fileName.substring(fileName.lastIndexOf("_") + 1);
                    //如果已经创建过

                    //设置器官属性
                    Organs organ = new Organs();
                    organ.setOrgName(fileName);
                    organ.setOrgNum(numOrder.substring(0, numOrder.indexOf(".")));
                    organ.setOrgAccount(account);
                    organ.setOrgOrigin(name.substring(0, name.lastIndexOf(".")));

                    organsService.save(organ);
                }
            }
        } else {
            System.out.println("指定的目录不存在或不是一个有效的目录。");
        }


        String fileName = name.substring(0, name.lastIndexOf(".")) + ".nii.gz";
        PyUtil.separateOraganStl(fileName, account, name.substring(0, name.lastIndexOf(".")));
        PyUtil.separateOraganNii(fileName, account, name.substring(0, name.lastIndexOf(".")));

        return Result.ok();
    }

    @Override
    public Result orgShow(String origin, String account) {
        if (origin == null || origin.equals("")) {
            return Result.fail("未查询到相应数据！");
        }
        if (account == null || account.equals("")) {
            return Result.fail("未查询到相应数据！");
        }

        QueryWrapper<Organs> wrapper = new QueryWrapper<>();
        wrapper.eq("org_origin", origin);
        wrapper.eq("org_account", account);

        List<Organs> list = organsService.list(wrapper);

        if (list.size() <= 0) {
            return Result.fail("未查询到相应数据！");
        }

        return Result.ok(list);
    }
}
