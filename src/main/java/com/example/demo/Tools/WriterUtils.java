package com.example.demo.Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriterUtils {

    public static void writer(String filename, String data) {
        File file = new File(filename);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            fos.write(data.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
