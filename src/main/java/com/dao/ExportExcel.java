package com.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
//import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import com.entity.Employee;

@Service
@ManagedBean
public class ExportExcel {

	public static final int COLUMN_INDEX_NO = 0;
	public static final int COLUMN_INDEX_ENAME = 1;
	public static final int COLUMN_INDEX_JOB = 2;
	public static final int COLUMN_INDEX_MGR = 3;
	public static final int COLUMN_INDEX_HIREDATE = 4;
	public static final int COLUMN_INDEX_SALARY = 5;
	public static final int COLUMN_INDEX_COMM = 6;
	public static final int COLUMN_INDEX_DEPTNO = 7;
	private static CellStyle cellStyleFormatNumber = null;
	
	public void writeExcel(List<Employee> emps, String excelFilePath) throws IOException {
		// Create Workbook
		SXSSFWorkbook workbook = new SXSSFWorkbook();

		// Create sheet
		SXSSFSheet sheet = workbook.createSheet("Employee"); // Create sheet with sheet name

		// register the columns you wish to track and compute the column width
		sheet.trackAllColumnsForAutoSizing();

		int rowIndex = 0;

		// Write header
		writeHeader(sheet, rowIndex);

		// Write data
		rowIndex++;
		for (Employee emp : emps) {
			// Create row
			SXSSFRow row = sheet.createRow(rowIndex);
			// Write data on row
			writeEmployee(emp, row);
			rowIndex++;
		}

		// Write footer
		// writeFooter(sheet, rowIndex);

		// Auto resize column witdth
		// int numberOfColumn = 8; // sheet.getRow(0).getPhysicalNumberOfCells();
		// autosizeColumn(sheet, numberOfColumn);

		// Create file excel
		createOutputFile(workbook, excelFilePath);
		System.out.println("Done!!!");
	}
	
	// Write header with format
	private void writeHeader(SXSSFSheet sheet, int rowIndex) {
		// create CellStyle
		CellStyle cellStyle = createStyleForHeader(sheet);

		// Create row
		SXSSFRow row = sheet.createRow(rowIndex);

		// Create cells
		SXSSFCell cell = row.createCell(COLUMN_INDEX_NO);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("No");

		cell = row.createCell(COLUMN_INDEX_ENAME);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("ENAME");

		cell = row.createCell(COLUMN_INDEX_JOB);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("JOB");

		cell = row.createCell(COLUMN_INDEX_MGR);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("MGR");

		cell = row.createCell(COLUMN_INDEX_HIREDATE);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("HIREDATE");

		cell = row.createCell(COLUMN_INDEX_SALARY);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("SALARY");

		cell = row.createCell(COLUMN_INDEX_COMM);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("COMM");

		cell = row.createCell(COLUMN_INDEX_DEPTNO);
		cell.setCellStyle(cellStyle);
		cell.setCellValue("DEPTNO");
	}

	// Write data
	private void writeEmployee(Employee emp, SXSSFRow row) {
		if (cellStyleFormatNumber == null) {
			// Format number
			short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
			// DataFormat df = workbook.createDataFormat();
			// short format = df.getFormat("#,##0");

			// Create CellStyle
			SXSSFWorkbook workbook = row.getSheet().getWorkbook();
			cellStyleFormatNumber = workbook.createCellStyle();
			cellStyleFormatNumber.setDataFormat(format);
		}

		SXSSFCell cell = row.createCell(COLUMN_INDEX_NO);
		cell.setCellValue(emp.getEmpno());

		cell = row.createCell(COLUMN_INDEX_ENAME);
		cell.setCellValue(emp.getEname());

		cell = row.createCell(COLUMN_INDEX_JOB);
		cell.setCellValue(emp.getJob());
		// cell.setCellStyle(cellStyleFormatNumber);

		if(emp.getMgr()==null) {
			cell = row.createCell(COLUMN_INDEX_MGR);
			cell.setCellValue(0);
		}else {
			cell = row.createCell(COLUMN_INDEX_MGR);
			cell.setCellValue(emp.getMgr());
		}
		cell = row.createCell(COLUMN_INDEX_HIREDATE);
		cell.setCellValue(emp.getHiredate());

		cell = row.createCell(COLUMN_INDEX_SALARY);
		cell.setCellValue(emp.getSal());

		if (emp.getComm() == null) {
			cell = row.createCell(COLUMN_INDEX_COMM);
			cell.setCellValue(0);
		} else {
			cell = row.createCell(COLUMN_INDEX_COMM);
			cell.setCellValue(emp.getComm());
		}

		cell = row.createCell(COLUMN_INDEX_DEPTNO);
		cell.setCellValue(emp.getDept().getDeptno());
	}

	// Create CellStyle for header
	private CellStyle createStyleForHeader(Sheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.WHITE.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	// Write footer
//	private void writeFooter(SXSSFSheet sheet, int rowIndex) {
//		// Create row
//		SXSSFRow row = sheet.createRow(rowIndex);
//		SXSSFCell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//		cell.setCellFormula("SUM(E2:E6)");
//	}

	// Auto resize column width
//	private void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
//		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
//			sheet.autoSizeColumn(columnIndex);
//		}
//	}

	// Create output file
	private void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws IOException {
		OutputStream out = new FileOutputStream(excelFilePath);
		workbook.write(out);
	}

}