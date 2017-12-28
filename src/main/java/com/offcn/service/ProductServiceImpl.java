package com.offcn.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.offcn.dao.ProductMapper;
import com.offcn.pojo.Product;

//http://localhost:8080/day03-work/getProductByNameAndRelease_Time1.action
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override // 先写死 ，大白菜2017-11-01-------2017-11-30
	public List<Product> getProductByNameAndRelease_Time1() {
		List<Product> list = productMapper.getProductByNameAndRelease_Time1();
		return list;
	}

	// 先写死 ，大白菜2016-11-01-------2016-11-30
	public List<Product> getProductByNameAndRelease_Time2() {
		List<Product> list = productMapper.getProductByNameAndRelease_Time2();
		return list;
	}

	@Override
	public void writeToData(String jsonStr) {

		FileOutputStream out = null;
		try {

			out = new FileOutputStream("src/main/webapp/json/data3.json");

			out.write(jsonStr.getBytes());

			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override // 大白菜 2017一个整年度每个月份的平均价值
	public List<Double> getProductByMonthAVG2017() {

		List<Double> list = productMapper.getProductByMonthAVG2017();
		return list;
	}

	@Override // 大白菜 2016一个整年度每个月份的平均价值
	public List<Double> getProductByMonthAVG2016() {

		List<Double> list = productMapper.getProductByMonthAVG2016();
		return list;
	}

	public void getPDF(HttpServletRequest request, HttpServletResponse response,ServletOutputStream servletOutputStream) throws Exception {

		// 获取下载路劲
		String path = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println(path);
		// 生成的 PDF 文档名称
		String fileName = "demo.pdf";
	
		createPDF(path + fileName);
		
		File file = new File(path + fileName); // 根据文件路径获得 File 文件
		
		//response.setContentType("application/x-xls;charset=GBK");
		//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型   
		  response.setContentType("multipart/form-data");   
		// 文件名
		response.setHeader("Content-Disposition",
				"attachment;filename=\"" + new String(fileName.getBytes(), "ISO8859-1") + "\"");
	
		response.setContentLength((int) file.length());
		
		byte[] buffer = new byte[4096];// 缓冲区
		OutputStream output = null;
		BufferedInputStream input = null;
		try {
			output = servletOutputStream;
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			// 遍历，开始下载
			while ((n = input.read(buffer, 0, 4096)) > -1) {
				output.write(buffer, 0, n);
			}
			output.flush();
			//response.flushBuffer();
		} catch (Exception e) {
			// 异常自己捕捉
		} finally {
			// 关闭流，不可少
			if (input != null)
				input.close();
			if (output != null)
				output.close();
		}
	}

	
	public void createPDF(String fileName ) throws IOException {
		
		
		// Initialize PDF writer
		PdfWriter writer = new PdfWriter(fileName);
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
		
		// 2017数据
		List<Product> list1 = productMapper.getProductByNameAndRelease_Time1();
		
		// 2016年数据
		List<Product> list2 = productMapper.getProductByNameAndRelease_Time2();
		/*// 创建表格对象：
		Table table1 = new Table(new float[]{list1.size()+1});
			
		Table table2 = new Table(new float[]{list1.size()+1});

		Table table3 = new Table(new float[]{list1.size()+1});	
	
		table1.addCell(new Cell(1,1).add(new Paragraph("")));
		for (int i = 0; i < list1.size(); i++) {
			table1.addCell(new Cell(1,1).add(new Paragraph(""+(i+1))));
		}
		
		table2.addCell(new Cell(1,1).add(new Paragraph("2017")));
		for (int i = 0; i < list1.size(); i++) {
			Product product = list1.get(i);
			table2.addCell(new Cell(1,1).add(new Paragraph(""+product.getAverage_price())));
		}
		
		
		
		table3.addCell(new Cell(1,1).add(new Paragraph("2016")));
		for (int i = 0; i < list2.size(); i++) {
			Product product = list2.get(i);
			table3.addCell(new Cell(1,1).add(new Paragraph(""+product.getAverage_price())));
		}
		// 把表格添加到文档对象
		document.add(table1);
		document.add(table2);
		document.add(table3);
		*
		*/
		

		float[] cellSize = new float[31];

	

		Table price_table = new Table(cellSize);

		price_table.setMarginLeft(55f);

		price_table.setAutoLayout();

		for (int i = 0; i <= 92; i++) {

			Cell cell = new Cell(1, 1);

			if (i >= 0 && i <= 30) {
				if (i != 0) {
					cell.add(new Paragraph(i + ""));
				}
			} else if (i >= 31 && i <= 61) {

				if (i == 31) {
					cell.add(new Paragraph("2017"));
				} else {

					cell.add(new Paragraph(list1.get(i - 32).getAverage_price() + ""));
				}
			} else if (i >= 62 && i <= 92) {
				if (i == 62) {
					cell.add(new Paragraph("2016"));
				} else {

					cell.add(new Paragraph(list2.get(i - 63).getAverage_price() + ""));
				}
			}

			price_table.addCell(cell);
		}
		
		
		document.add(price_table);
		
		
		
		// 添加图片
		getLineJPG();
		Image fox = new Image(ImageDataFactory.create("d:/chart/dabaicai.jpg"));		
		document.add(fox);
		
		
		// Close document
		document.close();


}
	
	// 得到图像JFreeChart
	
	public void getLineJPG() throws IOException{
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
		// 2017数据
		List<Product> list1 = productMapper.getProductByNameAndRelease_Time1();
		for (int i = 0; i < list1.size(); i++) {
			Product product = list1.get(i);
			product.getAverage_price();
			dataset.addValue(product.getAverage_price(), "2017", product.getRelease_date().substring(8) );
			
		}
		
		// 2016年数据
		List<Product> list2 = productMapper.getProductByNameAndRelease_Time2();
		for (int i = 0; i < list2.size(); i++) {
			Product product = list2.get(i);
			
			dataset.addValue(product.getAverage_price(), "2016", product.getRelease_date().substring(8));
		}
		
	
		JFreeChart chart=ChartFactory.createLineChart("", "日期", "价格",dataset,PlotOrientation.VERTICAL, true, true, true);
		CategoryPlot plot = chart.getCategoryPlot();
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		//设置折线上显示数据
		
		renderer.setItemLabelsVisible(true);
		ChartUtilities.saveChartAsJPEG(new File("d:/chart/dabaicai.jpg"), chart,900, 600);
		System.out.println("报表生成完毕");
		}
	
	
}