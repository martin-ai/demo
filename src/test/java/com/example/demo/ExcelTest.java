package com.example.demo;

import com.example.demo.Tools.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelTest extends AiDemoApplicationTests {

    @Autowired
    private ExcelUtils excelUtils;

    @Test
    public void test() throws Exception {
        String excelFileName = "src/main/resources/info/test.xlsx";
        Workbook wb = excelUtils.readExcel(excelFileName);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        for (Cell cell : row) {
            String value = excelUtils.getCellValue(cell);
            System.out.println(value);
        }
        System.out.println(wb);
    }

}
