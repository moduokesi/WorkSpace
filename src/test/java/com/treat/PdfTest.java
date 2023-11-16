package com.treat;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.treat.utils.PdfUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.treat.utils.PdfUtil.*;

@SpringBootTest
public class PdfTest {
    @Test
    public static void main(String[] args) {
        // 定义输出PDF文件的路径
        String outputPath = "C:\\Users\\86130\\Pictures\\Image\\first.pdf";
        // 定义图片路径
        String imagePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\1234567890\\imgfile\\01a7d587-5815-479d-8292-9b4e6ee8f74b.png";

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


            // 设置标题字体大小为小二
            PdfFont titleFont = PdfFontFactory.createFont("c:\\windows\\fonts\\times.ttf", "Identity-H", true);

            // 添加标题 "MED-AI诊断报告书"，设置字体和居中
            Text medAiText = new Text("MED-AI ").setFont(timesNewRomanFont).setFontSize(18).setBold();
            Text diagnosisReportText = new Text("诊断报告书").setFont(kaiTiFont).setFontSize(18).setBold();

            Paragraph title = new Paragraph().add(medAiText).add(diagnosisReportText).setTextAlignment(TextAlignment.CENTER);
            // 设置左右缩进为4.44cm
            title.setMarginLeft(72f); // 1 inch = 72 points
            title.setMarginRight(72f);
            // 设置段前为5.95磅
            title.setMarginTop(5.95f);

            document.add(title);

            // 添加分割线
            document.add(getLine());

            // 添加姓名、性别和年龄信息，并设置为五号字体（使用 "Times New Roman" 字体）
            String name = "李鑫";
            String gender = "女";
            String age = "21";
            String checkDate = "2023 年 11 月 11 日 15:09";
            String number = "";
            String fileName = "";

            // 构造空格字符串
            String infoText = "姓名：" + name +
                    getTabs(7, name) + "性别：" + gender + getTabs(4, gender) + "年龄：" + age;
            // 构造空格字符串
            String infoText1 = "检查日期：" + checkDate +
                    "\t" + "门诊号：" + number;

            // 向Document中添加信息段落
            document.add(getParagraph(infoText, false));
            document.add(getParagraph(infoText1, false));
            document.add(getParagraph("出自医疗数据文件：" + fileName, false));

            // 添加分割线
            document.add(getLine());

            // 添加图片并设置大小（使用厘米为单位）
            Image image = new Image(ImageDataFactory.create(imagePath));
            image.scaleAbsolute(1200, 454);
            image.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(image);

            Text diagTex = new Text("临床诊断：").setFont(kaiTiFont).setFontSize(11).setBold();
            String diagnose = "患者梁志超，21 岁，门诊号 3-317，于 2023 年 11 月 11 日 15:02 进行了多项器官检查。根据检查结果，患者的器官状况整体较为正常，未见明显异常或病变迹象。具体而言，脾脏、肾脏、肝脏、胃、主动脉等器官的大小、表面积和体积均在正常范围内。";
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
            tableData.add(new String[]{"脾脏", "248", "17.10", "15.15"});

            for (String[] row : tableData) {
                for (String cell : row) {
                    table.addCell(new Paragraph(cell).setFont(songFont).setFontSize(10));
                }
            }

            table.setTextAlignment(TextAlignment.CENTER);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            document.add(table);

            // 添加分割线
            document.add(getLine());

            String patient = "综合上述器官检查结果，目前未发现明显的器官功能异常或结构异常，但建议患者定期体检，密切关注身体状况。进一步的病史采集和临床评估，以及必要的血液检查等可以提供更全面的健康评估。请患者咨询医生，根据医生的建议进行必要的随访和治疗。";
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
    }



}
