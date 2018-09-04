package com.example.demo.linuxFTP;

import java.io.InputStream;
import java.io.OutputStream;

public abstract class FileHelper {

    public static void writeFile(InputStream is, String path) throws Exception {
        FileManager.getFileStorage().write(is, path);
        FileStory.addCreatedFile(path);
    }

    public static InputStream readFile(String path) throws Exception {
        return FileManager.getFileStorage().getFileInputStream(path);
    }

    public static void readFile(OutputStream os, String path) throws Exception {
        FileManager.getFileStorage().read(path, os);
    }

    public static void deleteFile(String path) throws Exception {
        FileManager.getFileStorage().delete(path);
    }

    public static boolean existsFile(String path) throws Exception {
        return FileManager.getFileStorage().exists(path);
    }

    public static void writeFile(InputStream is, String filename, String dir) throws Exception {
        if (dir != null) {
            try {
                FileHelper.writeFile(is, dir + filename);
            } finally {
                Closer.close(is);
            }
        }
    }

    public static String[] getPathNames(String path) throws Exception {
        return FileManager.getFileStorage().list(path);
    }

}