package com.example.inout.controller;

import com.example.inout.vo.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InOutController {
	@SuppressWarnings("resource")
	@RequestMapping("/export")
	public void exportExcel(HttpServletResponse response, HttpSession session, String name) throws Exception {

		String[] tableHeaders = {"id", "姓名", "年龄"};

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		HSSFCellStyle cellStyle = workbook.createCellStyle();

		Font font = workbook.createFont();
		font.setColor(HSSFColor.RED.index);
		cellStyle.setFont(font);

		// 将第一行的三个单元格给合并
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
		HSSFRow row = sheet.createRow(0);
		HSSFCell beginCell = row.createCell(0);
		beginCell.setCellValue("通讯录");
		beginCell.setCellStyle(cellStyle);

		row = sheet.createRow(1);
		// 创建表头
		for (int i = 0; i < tableHeaders.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(tableHeaders[i]);
			cell.setCellStyle(cellStyle);
		}

		List<User> users = new ArrayList<>();
		users.add(new User(1L, "张三", 20));
		users.add(new User(2L, "李四", 21));
		users.add(new User(3L, "王五", 22));

		for (int i = 0; i < users.size(); i++) {
			row = sheet.createRow(i + 2);

			User user = users.get(i);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getName());
			row.createCell(2).setCellValue(user.getAge());
		}

		OutputStream outputStream = response.getOutputStream();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=template.xls");

		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}

	@SuppressWarnings("resource")
	@RequestMapping("/import")
	public String importExcel(@RequestParam("file") MultipartFile file) throws Exception{
		InputStream inputStream = file.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		//HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = workbook.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum();
		for (int i = 2; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int id = (int) row.getCell(0).getNumericCellValue();
			String name = row.getCell(1).getStringCellValue();
			int age = (int) row.getCell(2).getNumericCellValue();

			System.out.println(id + "-" + name + "-" + age);
		}
		return "ok";
	}
}
