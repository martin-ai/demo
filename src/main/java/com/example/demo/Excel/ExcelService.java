package com.example.demo.Excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static org.apache.poi.ss.usermodel.CellType.STRING;

@Service
public class ExcelService {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public Workbook readExcel(String excelFileName) throws Exception {
        File excelFile = new File(excelFileName);
        checkExcelValid(excelFile);
        FileInputStream in = new FileInputStream(excelFile);
        return getWorkbook(in, excelFile);
    }

    private Workbook getWorkbook(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {  //Excel 2003
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {  // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    private void checkExcelValid(File file) throws Exception {
        if (!file.exists()) {
            throw new Exception("文件不存在");
        }
        if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
            throw new Exception("文件不是Excel");
        }
    }

    public String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        String cellValue;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellValue = DateFormatUtils.format(date, "yyyy-MM-dd");
                } else {
                    cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case STRING:
                cellValue = StringUtils.trim(cell.getStringCellValue());
                break;
            default:
                cell.setCellType(STRING);
                cellValue = StringUtils.trim(cell.getStringCellValue());

        }
        return cellValue;
    }

}
