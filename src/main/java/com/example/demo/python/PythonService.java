package com.example.demo.python;

import com.example.demo.FileConfig;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class PythonService {

    @Autowired
    private FileConfig fileConfig;
    private static String CMD_PYTHON = "python";


    //交互式 执行Python
    public void interactivePython() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a=[5,2,3,9,4,0]");
        interpreter.exec(Py.newStringUTF8("print('阿斯房价送到·')"));//Py.newStringUTF8解决乱码问题
    }

    //文件式 执行Python 不可使用第三方库 ImportError: No module named numpy
    public void filePython() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(getPythonFilePath("test.py"));
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        PyObject pyObject = pyFunction.__call__(new PyInteger(15), new PyInteger(12));
        System.out.println(pyObject);
    }

    //文件式 执行Python 使用CMD命令 需搭有python环境 可以使用第三方库
    public void CmdFilePython() {
        try {
            String[] args = new String[]{CMD_PYTHON, getPythonFilePath("test.py"), String.valueOf(1), String.valueOf(2)};
            Process proc = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private String getPythonFilePath(String fileName) {
        return String.join("\\", System.getProperty("user.dir"), fileConfig.getPythonPath(fileName));
    }

}
