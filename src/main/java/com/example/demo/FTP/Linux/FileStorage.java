package com.example.demo.FTP.Linux;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileStorage {

    Object getFileSystemManager() throws Exception;

    OutputStream getFileOutputStream(String var1) throws Exception;

    void write(InputStream var1, String var2) throws Exception;

    void write(InputStream var1, String var2, boolean var3) throws Exception;

    void delete(String var1) throws Exception;

    void deleteDir(String var1, FileNameFilter var2) throws Exception;

    InputStream getFileInputStream(String var1) throws Exception;

    long read(String var1, OutputStream var2) throws Exception;

    void copyFile(String var1, String var2) throws Exception;

    void copyDir(String var1, String var2, FileNameFilter var3) throws Exception;

    void move(String var1, String var2) throws Exception;

    String[] list(String var1) throws Exception;

    boolean exists(String var1) throws Exception;

    boolean isDir(String var1) throws Exception;

}
