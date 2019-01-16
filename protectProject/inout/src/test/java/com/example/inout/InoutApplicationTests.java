package com.example.inout;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InoutApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void createExcel() throws IOException {
		// 获取桌面路径
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String desktop = fsv.getHomeDirectory().getPath();
		String filePath = desktop + "/template.xls";

		File file = new File(filePath);
		OutputStream outputStream = new FileOutputStream(file);
		//一个excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//一张叫Sheet1的表格
		HSSFSheet sheet = workbook.createSheet("Sheet1");
		//第一行
		HSSFRow row = sheet.createRow(0);
		//第i个格子
		row.createCell(0).setCellValue("id");
		row.createCell(1).setCellValue("订单号");
		row.createCell(2).setCellValue("下单时间");
		row.createCell(3).setCellValue("个数");
		row.createCell(4).setCellValue("单价");
		row.createCell(5).setCellValue("订单金额");
		row.createCell(6).setCellValue("订单折扣");
		row.createCell(7).setCellValue("实付金额");
		row.setHeightInPoints(30); // 设置行的高度
		//第二行
		HSSFRow row1 = sheet.createRow(1);
		row1.createCell(0).setCellValue("1");
		row1.createCell(1).setCellValue("NO00001");

		// 日期格式化
		HSSFCellStyle cellStyle2 = workbook.createCellStyle();
		HSSFCreationHelper creationHelper = workbook.getCreationHelper();
		cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
		sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度

		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellStyle(cellStyle2);
		cell2.setCellValue(new Date());

		row1.createCell(3).setCellValue(2);


		// 保留两位小数
		HSSFCellStyle cellStyle3 = workbook.createCellStyle();
		cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		HSSFCell cell4 = row1.createCell(4);
		cell4.setCellStyle(cellStyle3);
		cell4.setCellValue(29.5);


		// 货币格式化
		HSSFCellStyle cellStyle4 = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setFontName("华文行楷");
		font.setFontHeightInPoints((short)15);
		font.setColor(HSSFColor.RED.index);
		cellStyle4.setFont(font);

		HSSFCell cell5 = row1.createCell(5);
		cell5.setCellFormula("D2*E2");  // 设置计算公式

		// 获取计算公式的值
		HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
		cell5 = e.evaluateInCell(cell5);
		System.out.println(cell5.getNumericCellValue());

		HSSFCellStyle cellStyle6 = workbook.createCellStyle();
		cellStyle6.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		HSSFCell cell6 = row1.createCell(6);
		cell6.setCellStyle(cellStyle6);
		cell6.setCellValue(0.80);

		HSSFCell cell7 = row1.createCell(7);
		cell7.setCellFormula("F2*G2");
		HSSFFormulaEvaluator e2 = new HSSFFormulaEvaluator(workbook);
		cell7 = e2.evaluateInCell(cell7);
		System.out.println(cell7.getNumericCellValue());

		workbook.setActiveSheet(0);
		workbook.write(outputStream);
		outputStream.close();
	}

	@Test
	public void readExcel() throws IOException{
		FileSystemView fsv = FileSystemView.getFileSystemView();
		String desktop = fsv.getHomeDirectory().getPath();
		String filePath = desktop + "/template.xls";

		FileInputStream fileInputStream = new FileInputStream(filePath);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		POIFSFileSystem fileSystem = new POIFSFileSystem(bufferedInputStream);
		HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
		HSSFSheet sheet = workbook.getSheet("Sheet1");

		int lastRowIndex = sheet.getLastRowNum();
		System.out.println(lastRowIndex);
		for (int i = 0; i <= lastRowIndex; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row == null) { break; }

			short lastCellNum = row.getLastCellNum();
			for (int j = 0; j < lastCellNum; j++) {
				HSSFCell cell = row.getCell(j);
				System.out.print(cell.toString() + "  ");
			}
			System.out.print("\n");
		}
		bufferedInputStream.close();
	}
}

