package com.example.demo;

import com.example.demo.Excel.ExcelService;
import org.apache.poi.hssf.record.chart.SeriesToChartGroupRecord;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelTest extends AiDemoApplicationTests {

    @Autowired
    private ExcelService excelService;

    @Test
    public void test() throws Exception {
        String excelFileName = "src/main/resources/info/test.xls";
        Workbook wb = excelService.readExcel(excelFileName);
        System.out.println(wb);
    }

}
