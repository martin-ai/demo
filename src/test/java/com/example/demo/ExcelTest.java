package com.example.demo;

import com.example.demo.Excel.ExcelService;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelTest extends AiDemoApplicationTests {

    @Autowired
    private ExcelService excelService;

    @Test
    public void test() throws Exception {
        String excelFileName = "src/main/resources/info/test.xlsx";
        Workbook wb = excelService.readExcel(excelFileName);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            String value = excelService.getCellValue(cell);
            System.out.println(value);
        }
        System.out.println(wb);
    }

}
