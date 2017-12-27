package com.offcn.jsoup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// excel存取类，解析页面时提取页面产品数据，存入excel文档。
public class Excel {
	
		private String filePath; // 保存excel文档的路径
	    private XSSFWorkbook workbook;
	    private XSSFSheet sheet;
	    private int rowCount;    //总记录数
	    
	    /**
	     * 
	     * @param file 文件名
	     * @param sheetName sheet名
	     */
	    public Excel(String file, String sheetName) {
	        super();
	        this.filePath = file;
	        newSheet(sheetName);
	    }

	    /**
	     * 初始化excel表格，生成标题（第一行数据）
	     * @param sheetName    sheet标签名
	     * @param columnNames 列名
	     */
	    private void newSheet(String sheetName){
	        workbook = new XSSFWorkbook();
	        //创建sheet
	        sheet = workbook.createSheet(sheetName);
	        //设置初始记录行数
	        rowCount = 0;
	    }
	   
	    /**
	     * 插入一行数据
	     * @param rowData 数据内容
	     */
	    public void insertRow(String[] rowData){
	    	// 传进一行数据就创建一行
	        XSSFRow row = sheet.createRow(rowCount);
	        // 一行里面有多少列，就创建多少单元格
	        for (int i = 0; i < rowData.length; i++) {
	        	
	            XSSFCell cell = row.createCell(i);
	            // 往每个单元格插入数据
	            cell.setCellValue(rowData[i]);
	        }
	        rowCount++;
	    }
	    /**
	     * excel文件存储到磁盘
	     */
	    public void saveFile(){
	        try {
	            File file = new File(filePath);
	            FileOutputStream fileOutputStream = new FileOutputStream(file);
	            workbook.write(fileOutputStream);
	        } catch (FileNotFoundException e) {
	            System.out.println(filePath + "保存文件错误:" + e.getMessage());
	        } catch (IOException e) {
	            System.out.println(filePath + "保存文件错误:" + e.getMessage());
	        }
	    }
	    /**
	     * 返回excel总行数
	     * @return
	     */
	    public int size() {
	        return rowCount;
	    }
	    
}
