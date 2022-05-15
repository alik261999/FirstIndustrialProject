package com.main.Tests;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {
	
	public static String[] info = new String[11];
	
	public static String[] readExcelData() throws IOException {
		
		FileInputStream fs = new FileInputStream("DataInput.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = sheet.getRow(0);
		DataFormatter df = new DataFormatter();
		for(int i=0;i<10;i++) {
			XSSFCell cell = row.getCell(i);
			info[i] = df.formatCellValue(cell);
			//System.out.println(info[i]);
		}
		wb.close();
		return info;
	}
	

}
