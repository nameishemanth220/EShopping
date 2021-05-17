package com.testyantra.eshopping.genericlibs;

import java.io.FileInputStream;
import java.time.LocalDateTime;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary implements IAutoConstants {
	
	public static String getData(String sheetName, int rowNumber, int cellNumber) {
		
		try {
			
			FileInputStream fin = new FileInputStream(XL_PATH);
			Workbook workbook=WorkbookFactory.create(fin);
			return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Double getNumericData(String sheetName, int rowNumber, int cellNumber) {
		
		try {
			
			FileInputStream fin = new FileInputStream(XL_PATH);
			Workbook workbook=WorkbookFactory.create(fin);
			return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getNumericCellValue();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Boolean getBooleanData(String sheetName, int rowNumber, int cellNumber) {
		
		try {
			
			FileInputStream fin = new FileInputStream(XL_PATH);
			Workbook workbook=WorkbookFactory.create(fin);
			return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getBooleanCellValue();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static LocalDateTime getDate(String sheetName, int rowNumber, int cellNumber) {
		
		try {
			
			FileInputStream fin = new FileInputStream(XL_PATH);
			Workbook workbook=WorkbookFactory.create(fin);
			return workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellNumber).getLocalDateTimeCellValue();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
