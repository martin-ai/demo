package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fail.config")
public class FileConfig {

    private String pythonPath;

    public String getPythonPath() {
        return pythonPath;
    }

    public void setPythonPath(String pythonPath) {
        this.pythonPath = pythonPath;
    }

    public String getPythonPath(String fileName) {
        return String.join("\\", getPythonPath(), fileName);
    }

}
