package com.example.demo;

import com.example.demo.linuxFTP.FileHelper;
import org.junit.Test;

/**
 * Created by yixiang.guo on 2018/9/4.
 */
public class FtpTest extends AiDemoApplicationTests {

    @Test
    public void test() throws Exception {
        String[] strings = FileHelper.getPathNames("ftp://10.0.0.158/test/aa");
        System.out.println(strings.length);
    }

}
