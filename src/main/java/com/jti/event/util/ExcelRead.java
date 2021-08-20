package com.jti.event.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {

	public List<List<Object>> readExcelToListXlS(MultipartFile multipartFile)
			throws IOException, InvalidFormatException {
		List<List<Object>> listData = new ArrayList<List<Object>>();
		// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
		HSSFWorkbook workbook = new HSSFWorkbook(multipartFile.getInputStream());

		// 탐색에 사용할 Sheet, Row, Cell 객체
		HSSFSheet curSheet;
		HSSFRow curRow;
		HSSFCell curCell;

		// Sheet 탐색 for문
		// 현재 sheet 반환
		curSheet = workbook.getSheetAt(0);
		// row 탐색 for문
		for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
			// row 0은 헤더정보이기 때문에 무시
			if (rowIndex != 0) {
				List<Object> data = new ArrayList<Object>();
				curRow = curSheet.getRow(rowIndex);
				int cells = curRow.getPhysicalNumberOfCells();

				for (int cellnum = 0; cellnum < cells; cellnum++) {

					HSSFCell cell = curRow.getCell(cellnum);
					// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
					if (cell != null) {
						if (!"".equals(cell.getStringCellValue())) {
							// cell 탐색 for문
							for (int cellIndex = 0; cellIndex < curRow.getPhysicalNumberOfCells(); cellIndex++) {
								curCell = curRow.getCell(cellIndex);
								// cell 스타일이 다르더라도 String으로 반환 받음
								switch (curCell.getCellType()) {
								case HSSFCell.CELL_TYPE_FORMULA:
									data.add(curCell.getCellFormula());
									break;
								case HSSFCell.CELL_TYPE_NUMERIC:
									data.add(curCell.getNumericCellValue());
									break;
								case HSSFCell.CELL_TYPE_STRING:
									data.add(curCell.getStringCellValue());
									break;
								case HSSFCell.CELL_TYPE_BLANK:
									data.add(curCell.getBooleanCellValue());
									break;
								case HSSFCell.CELL_TYPE_ERROR:
									data.add(curCell.getErrorCellValue());
									break;
								default:
									data.add(new String());
									break;
								}
							}
						}
					}
				} // end cell for
				listData.add(data);
			}
		}

		return listData;
	}

	public List<List<Object>> readExcelToListXlsx(MultipartFile multipartFile)
			throws IOException, InvalidFormatException {
		List<List<Object>> listData = new ArrayList<List<Object>>();

		// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
		XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());

		// 탐색에 사용할 Sheet, Row, Cell 객체
		XSSFSheet curSheet;
		XSSFRow curRow;
		XSSFCell cell;

		// Sheet 탐색 for문
		// 현재 sheet 반환
		curSheet = workbook.getSheetAt(0);
		// row 탐색 for문
		for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
			if (rowIndex != 0) {
				List<Object> data = new ArrayList<Object>();
				curRow = curSheet.getRow(rowIndex);
				int cells = curRow.getPhysicalNumberOfCells();
				for (int cellnum = 0; cellnum < cells; cellnum++) {
					cell = curRow.getCell(cellnum);
					if (cell != null) {
						if (!"".equals(cell.getStringCellValue())) {
							// cell 스타일이 다르더라도 String으로 반환 받음
							switch (cell.getCellType()) {
							case XSSFCell.CELL_TYPE_FORMULA:
								data.add(cell.getCellFormula());
								break;
							case XSSFCell.CELL_TYPE_NUMERIC:
								data.add(cell.getNumericCellValue());
								break;
							case XSSFCell.CELL_TYPE_STRING:
								data.add(cell.getStringCellValue());
								break;
							case XSSFCell.CELL_TYPE_BLANK:
								data.add(cell.getBooleanCellValue());
								break;
							case XSSFCell.CELL_TYPE_ERROR:
								data.add(cell.getErrorCellValue());
								break;
							default:
								data.add(new String());
								break;
							}
						}
					}
				} // end cell for
				listData.add(data);
			}
		}

		return listData;
	}

}
