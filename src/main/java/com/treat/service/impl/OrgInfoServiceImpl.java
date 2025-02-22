package com.treat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.treat.dto.PdfDTO;
import com.treat.dto.Result;
import com.treat.entity.OrgInfo;
import com.treat.mapper.OrgInfoMapper;
import com.treat.service.IOrgInfoService;
import com.treat.utils.PdfUtil;
import com.treat.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.treat.utils.PdfUtil.*;
import static com.treat.utils.PdfUtil.getLine;

@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfo> implements IOrgInfoService {
    @Autowired
    private IOrgInfoService orgInfoService;
    @Override
    public Result getPdfInfo(PdfDTO pdfDTO) {
        // 定义输出PDF文件的路径
        String pdfName = pdfDTO.getFileName();
        pdfName = pdfName.substring(0, pdfName.indexOf('.'));
        String account = UserHolder.getUser().getAccount();
        String outputPath = "C:\\Users\\86130\\Pictures\\Image\\" + pdfName + ".pdf";
        // 定义图片路径
        String imagePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" +
                account + "\\imgfile\\" + pdfName + ".png";

        String logoPath = "D:\\Workspaces\\Project\\treattest\\treatment\\src\\main\\resources\\" +
                "treatfront\\assets\\images\\3D医疗图标.png";

        // 从图片路径字符串创建Path对象
        Path path = Paths.get(imagePath);
        // 检查文件是否存在
        boolean exists = Files.exists(path);

        if (!exists) {
            return Result.fail();
        }

        try {
            // 创建PdfWriter实例，指定输出文件路径
            PdfWriter writer = new PdfWriter(outputPath);

            // 使用PdfWriter初始化PdfDocument对象
            PdfDocument pdf = new PdfDocument(writer);

            // 使用PdfDocument对象初始化Document对象
            Document document = new Document(pdf);

            // 创建 "Times New Roman" 字体
            PdfFont timesNewRomanFont = PdfFontFactory.createFont("c:\\windows\\fonts\\times.ttf", "Identity-H", true);
            // 创建楷体字体
            PdfFont kaiTiFont = PdfFontFactory.createFont("c:\\windows\\fonts\\simkai.ttf", "Identity-H", true);
            //宋体
            PdfFont songFont = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simsun.ttc,1", PdfEncodings.IDENTITY_H, false);

            // 添加标题 "MED-AI诊断报告书"，设置字体和居中
            Text medAiText = new Text("MED-AI ").setFont(timesNewRomanFont).setFontSize(18).setBold();
            Text diagnosisReportText = new Text("诊断报告书").setFont(kaiTiFont).setFontSize(18).setBold();

            // 创建包含文本的段落并居中
            Paragraph textParagraph = new Paragraph()
                    .add(medAiText)
                    .add(diagnosisReportText)
                    .setTextAlignment(TextAlignment.CENTER);

            // 添加图片并设置大小
            Image logo = new Image(ImageDataFactory.create(logoPath));
            logo.scaleAbsolute(35, 35)
                    .setFixedPosition(470f,  765f);

            Paragraph title = new Paragraph()
                    .add(textParagraph)
                    .setTextAlignment(TextAlignment.CENTER); // 居中整个标题

            // 设置左右缩进为4.44cm
            title.setMarginLeft(72f); // 1 inch = 72 points
            title.setMarginRight(72f);

            document.add(title);
            document.add(logo);

            // 添加分割线
            document.add(getLine());

            // 添加姓名、性别和年龄信息，并设置为五号字体（使用 "Times New Roman" 字体）
            String name = pdfDTO.getName();
            String gender = pdfDTO.getGender();
            String age = pdfDTO.getAge();
            String checkDate = pdfDTO.getCheckDate();
            String number = pdfDTO.getNumber();
            String fileName = pdfDTO.getFileName();

            // 创建一个空行段落
            Paragraph emptyLine = new Paragraph("\n");

            document.add(PdfUtil
                    .getTextParagraph("姓名：", name, 91f, 740f, 200f));
            document.add(PdfUtil
                    .getTextParagraph("性别：", gender, 290f, 740f, 200f));
            document.add(PdfUtil
                    .getTextParagraph("年龄：", age, 438f, 740f, 200f));
            // 将空行段落添加到文档
            document.add(emptyLine);

            document.add(PdfUtil
                    .getTextParagraph("检查日期：", checkDate, 91f, 721f, 200f));
            document.add(PdfUtil
                    .getTextParagraph("门诊号：", number, 290f, 721f, 200f));
            document.add(emptyLine);
            document.add(getTextParagraph("出自医疗数据文件：", fileName, 91f, 702f, 400f));

            // 添加分割线
            document.add(getLine());

            // 添加图片并设置大小
            Image image = new Image(ImageDataFactory.create(imagePath));
            image.scaleAbsolute(1200, 454);
            image.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(image);

            Text diagTex = new Text("临床诊断：").setFont(kaiTiFont).setFontSize(11).setBold();
            String diagnose = pdfDTO.getDiagnose1();
            Text diagTex1 = new Text(diagnose).setFont(kaiTiFont).setFontSize(11);
            Paragraph diagPar = new Paragraph().add(diagTex)
                    .add(diagTex1).setMarginLeft(55f).setMarginRight(55f).setMarginTop(0.3f);
            document.add(diagPar);

            // 添加分割线
            document.add(getLine());
            document.add(getParagraph("器官信息：", true));

            Table table = new Table(4);

            table.addHeaderCell(getCells("器官名称"));
            table.addHeaderCell(getCells("最大直径（单位：mm）"));
            table.addHeaderCell(getCells("表面积（单位：cm²）"));
            table.addHeaderCell(getCells("体积（单位：cm³）"));

            //填充表格内容
            List<String[]> tableData = new ArrayList<>();
            for (String[] row: pdfDTO.getList()) {
                tableData.add(row);
            }

            for (String[] row : tableData) {
                for (String cell : row) {
                    table.addCell(new Paragraph(cell).setFont(songFont).setFontSize(9));
                }
            }

            table.setTextAlignment(TextAlignment.CENTER);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            document.add(table);

            // 添加分割线
            document.add(getLine());

            String patient = pdfDTO.getDiagnose2();
            Text patTex = new Text("病理诊断：").setFont(kaiTiFont).setFontSize(11).setBold();
            Text patTex1 = new Text(patient).setFont(kaiTiFont).setFontSize(11);
            Paragraph patPar = new Paragraph().add(patTex)
                    .add(patTex1).setMarginLeft(55f).setMarginRight(55f).setMarginTop(0.3f);
            document.add(patPar);

            // 添加分割线
            document.add(getLine());

            Text signTex = new Text("医师签名：").setFont(kaiTiFont).setFontSize(11).setBold();
            Paragraph signPar = new Paragraph().add(signTex)
                    .setMarginLeft(55f).setMarginRight(55f).setMarginTop(0.3f);
            document.add(signPar);

            // 关闭Document，完成PDF的写入
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
