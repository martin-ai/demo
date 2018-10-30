package com.example.demo;

import com.example.demo.python.PythonService;
import org.junit.Test;
import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;

public class PythonTest extends AiDemoApplicationTests {

    @Autowired
    private PythonService pythonService;

    @Test
    public void test1() {
        pythonService.interactivePython();
    }

    @Test
    public void test2() {
        String projectPath = System.getProperty("user.dir");
        String pythonName = "\\resources\\python\\test.py";
        String pythonPath = String.join("\\", projectPath, pythonName);
        System.out.println(pythonPath);
        pythonService.filePython(pythonPath);
    }

}
