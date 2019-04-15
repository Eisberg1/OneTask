package com.example.util;

import com.example.po.Report;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/08 16:25
 * Description:
 */
public class ExcelUtil {

    public static List<Report> importExcel(InputStream inputStream, String suffix, int startRow) throws IOException {
        List<Report> list = new ArrayList();
        Workbook workbook = null;
        if ("xls".equals(suffix)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if ("xlsx".equals(suffix)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            return null;
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        int lastRowNum = sheet.getLastRowNum();
        if (startRow > lastRowNum) {
            return null;
        }
        Report report = null;
        Row row = null;
        Cell cell = null;
        for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
            report = new Report();
            row = sheet.getRow(rowNum);
            //String format = parse(cell);
            report.setRId((int) (row.getCell(0).getNumericCellValue()));
            report.setRName(row.getCell(1).getStringCellValue());
            report.setRY((float) (row.getCell(2).getNumericCellValue()));
            report.setRX(row.getCell(3).getDateCellValue());
            System.out.println(report);

            //short firstCellNum = row.getFirstCellNum();
            //short lastCellNum = row.getLastCellNum();
            //if (lastCellNum!=0){
            //    for (int cellNum=firstCellNum;firstCellNum<=lastCellNum;cellNum++){
            //        cell = row.getCell(cellNum);
            //        if (cell==null){
            //
            //        }
            //    }
            //}
            list.add(report);
        }
        return list;
    }

    public static String parse(Cell cell) {
        String cellStr = null;
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                cellStr = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellStr = "";
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                SimpleDateFormat sdf = null;
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if (cell.getCellType() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("h:mm");
                    } else {
                        sdf = new SimpleDateFormat("YYYY/MM/dd");
                    }
                    Date date = cell.getDateCellValue();
                    cellStr = sdf.format(date);
                } else {
                    double v = cell.getNumericCellValue();
                    DecimalFormat format = new DecimalFormat();
                    String formatString = cell.getCellStyle().getDataFormatString();
                    if (formatString.equals("General")) {
                        format.applyPattern("#");
                    }
                    cellStr = format.format(v);
                }
                break;
            default:
                cellStr = "";
        }
        return cellStr;
    }


    public static XSSFWorkbook exportExcel(List<Report> list) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = null;
        Report report = null;
        for (int i = 1; i <= list.size(); i++) {
            row = sheet.createRow(i);
            //Report report = list.get(i - 1);
            report = list.get(i - 1);
            row.createCell(0).setCellValue(report.getRId());
            row.createCell(1).setCellValue(report.getRName());
            row.createCell(2).setCellValue(report.getRY());
            row.createCell(3).setCellValue(report.getRX());
        }
        return workbook;
    }


}
