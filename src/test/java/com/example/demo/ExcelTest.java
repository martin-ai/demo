package com.example.demo;

import com.example.demo.Excel.ExcelService;
import org.apache.poi.hssf.record.chart.SeriesToChartGroupRecord;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class ExcelTest extends AiDemoApplicationTests {

    @Autowired
    private ExcelService excelService;

    @Test
    public void test() throws Exception {
        String excelFileName = "src/main/resources/info/MDMM_Database_Structure.xlsx";
        Workbook wb = excelService.readExcel(excelFileName);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        for (Cell cell : row) {

        }
        System.out.println(wb);
    }

}
