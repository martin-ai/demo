package com.example.demo.python;

import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

@Service
public class PythonService {

    //交互式 执行Python
    public void interactivePython() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a=[5,2,3,9,4,0]");
        interpreter.exec(Py.newStringUTF8("print('阿斯房价送到·')"));
        System.out.println("阿斯房价送到");
    }

    //文件式 执行Python
    public void filePython(String filePath) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(filePath);
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        PyObject pyObject = pyFunction.__call__(new PyInteger(15), new PyInteger(12));
        System.out.println(pyObject);
    }
}
