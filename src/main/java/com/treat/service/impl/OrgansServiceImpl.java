package com.treat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.treat.entity.OrgInfo;
import com.treat.dto.Result;
import com.treat.entity.Organs;
import com.treat.mapper.OrgansMapper;
import com.treat.service.IOrgInfoService;
import com.treat.service.IOrgansService;
import com.treat.utils.PyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrgansServiceImpl extends ServiceImpl<OrgansMapper, Organs> implements IOrgansService {
    @Autowired
    private IOrgansService organsService;

    @Autowired
    private IOrgInfoService orgInfoService;

    @Override
    public synchronized Result separate(String name, String account) {
        QueryWrapper<Organs> wrapper = new QueryWrapper<>();
        wrapper.eq("org_account", account);
        wrapper.eq("org_origin", name.substring(0, name.lastIndexOf(".")));
        File directory = new File("D:\\Workspaces\\Project\\treattest\\treatdata\\" + account + "\\outfile\\" + name.substring(0, name.indexOf("."))); // 替换为您要读取的目录路径

        if (organsService.list(wrapper).size() > 0) {
            return Result.fail("该文件已进行过分离操作！");
        }
        if(!directory.exists()){
            directory.mkdir();
        }
        String fileName1 = name.substring(0, name.lastIndexOf(".")) + ".nii.gz";
        PyUtil.separateOraganStl(fileName1, account, name.substring(0, name.lastIndexOf(".")));
        PyUtil.separateOraganNii(fileName1, account, name.substring(0, name.lastIndexOf(".")));
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
        wrapper.last("order by convert(org_num ,SIGNED) asc");

        List<Organs> list = organsService.list(wrapper);

        if (list.size() <= 0) {
            return Result.fail("未查询到相应数据！");
        }

        return Result.ok(list);
    }

    @Override
    public Result orgInfo(String fileName, String fileAccount) {
        QueryWrapper<OrgInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("org_name", fileName);
        wrapper.eq("org_account", fileAccount);


        List<OrgInfo> list = orgInfoService.list(wrapper);
        if (!list.isEmpty()) {
            return Result.ok(list);
        }

        ArrayList<Double> array1 = PyUtil.OrganDiameterMeasure(fileName, fileAccount);
        ArrayList<Double> array2 = PyUtil.OrganSurfaceMeasure(fileName, fileAccount);
        ArrayList<Double> array3 = PyUtil.OrganVolumnMeasure(fileName, fileAccount);

        OrgInfo[] organs = new OrgInfo[array1.size()];

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        for (int i = 0; i < organs.length; i++) {
            organs[i] = new OrgInfo(); // 实例化每个元素

            String organ = (i == 0) ? "脾脏" : (i == 1) ? "右肾" : (i == 2) ? "左肾" : (i == 3) ? "胆囊" : (i == 4) ? "食道" : (i == 5) ? "肝" : (i == 6) ? "胃" : (i == 7) ? "主动脉" : (i == 8) ? "下腔静脉" : (i == 9) ? "胰腺" : (i == 10) ? "右肾上腺" : (i == 11) ? "左肾上腺" : (i == 12) ? "十二指肠" : (i == 13) ? "膀胱" : (i == 14) ? "前列腺/子宫" : "未知器官";
            String value1 = String.valueOf(Double.parseDouble(decimalFormat.format(array1.get(i))));
            String value2 = String.valueOf(Double.parseDouble(decimalFormat.format(array2.get(i))));
            String value3 = String.valueOf(Double.parseDouble(decimalFormat.format(array3.get(i))));

            organs[i].setOrgAccount(fileAccount);
            organs[i].setOrgName(fileName);
            organs[i].setOrgOrgan(organ);
            organs[i].setOrgDiameter(value1);
            organs[i].setOrgSurface(value2);
            organs[i].setOrgVolume(value3);
            //存入数据库中
            orgInfoService.save(organs[i]);
        }

        return Result.ok(organs);
    }
}
