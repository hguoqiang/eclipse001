package com.offcn;

import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

/**
 * Simple table example.
 */
//@WrapToTest
public class PdfDemo {
	public static final String DEST = "d:\\pdftest\\5_1.pdf";

	public static void main(String args[]) throws IOException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new PdfDemo().createPdf(DEST);
	}

	public void createPdf(String dest) throws IOException {
		
		// Initialize PDF writer
		PdfWriter writer = new PdfWriter(dest);
		// Initialize PDF document
		PdfDocument pdf = new PdfDocument(writer);
		// Initialize document
		Document document = new Document(pdf, PageSize.A4.rotate());
		// 设定页边距
		document.setMargins(20, 20, 20, 20);
		PdfFont font=PdfFontFactory.createFont("STSongStd-Light","UniGB-UCS2-H",false);
		document.setFont(font);
		
	
		// 设定字体  设定标题
		document.add(new Paragraph("2017年11月与2016年同期大白菜价格走势").setTextAlignment(TextAlignment.CENTER).setFont(font).setBold());
		
		
		// 创建表格对象：
		Table table1 = new Table(new float[]{32});
		table1.setWidthPercent(100);
		
		Table table2 = new Table(new float[]{32});
		table1.setWidthPercent(100);
		
		Table table3 = new Table(new float[]{32});
		table1.setWidthPercent(100);
		
	
		for (int i = 0; i < 30; i++) {
			
			
		}
		
		
	
		// 把表格添加到文档对象
		document.add(table1);
		document.add(table2);
		document.add(table3);
		// Close document
		document.close();
	}
}