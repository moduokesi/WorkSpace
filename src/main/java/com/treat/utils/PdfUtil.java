package com.treat.utils;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;

import java.io.IOException;

public class PdfUtil {
    public static LineSeparator getLine() {
        LineSeparator line = new LineSeparator(new SolidLine());
        line.setWidth(414);
        line.setHorizontalAlignment(HorizontalAlignment.CENTER);

        float marginTop = 1f;
        float marginBottom = 1f;
        line.setMarginTop(marginTop);
        line.setMarginBottom(marginBottom);
        return line;
    }

    public static Cell getCells(String s) throws IOException {
        //宋体
        PdfFont songFont = PdfFontFactory.createFont("C:\\Windows\\Fonts\\simsun.ttc,1", PdfEncodings.IDENTITY_H, false);

        return new Cell().add(new Paragraph(new Text(s).setFont(songFont).setFontSize(9)))
                .setHeight(13f).setWidth(100f);
    }

    public static String getTabs(int tabCount, String s) {
        int nameLength = s.length();
        int requiredTabs = Math.max(tabCount - nameLength / 4, 0);

        // 使用制表符构造空格字符串
        return new String(new char[requiredTabs]).replace('\0', '\t');
    }


    public static Paragraph getParagraph(String infoText, boolean flag) throws IOException {
        PdfFont kaiTiFont = PdfFontFactory.createFont("c:\\windows\\fonts\\simkai.ttf", "Identity-H", true);

        Paragraph infoParagraph = new Paragraph(infoText)
                .setFont(kaiTiFont)
                .setFontSize(11);

        if (flag == true) {
            infoParagraph.setBold();
        }

        infoParagraph.setMarginLeft(55f);
        infoParagraph.setMarginRight(55f);
        infoParagraph.setMarginTop(0.3f);
        infoParagraph.setMarginBottom(0.3f);

        return infoParagraph;
    }

    public static Paragraph getTextParagraph(String prefix, String s, float left, float bottom, float width) throws IOException {
        PdfFont kaiTiFont = PdfFontFactory.createFont("c:\\windows\\fonts\\simkai.ttf", "Identity-H", true);

        Text text = new Text(prefix + s)
                .setFont(kaiTiFont);
       return new Paragraph(text)
                .setFixedPosition(left, bottom, width);
    }
}
