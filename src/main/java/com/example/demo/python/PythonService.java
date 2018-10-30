package com.example.demo.python;

import com.example.demo.FileConfig;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PythonService {

    @Autowired
    private FileConfig fileConfig;

    //交互式 执行Python
    public void interactivePython() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a=[5,2,3,9,4,0]");
        interpreter.exec(Py.newStringUTF8("print('阿斯房价送到·')"));//解决乱码问题
    }

    //文件式 执行Python
    public void filePython() {
        String pythonPath = String.join("\\", System.getProperty("user.dir"), fileConfig.getPythonPath("test.py"));
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(pythonPath);
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        PyObject pyObject = pyFunction.__call__(new PyInteger(15), new PyInteger(12));
        System.out.println(pyObject);
    }

}
