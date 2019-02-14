package com.example.demo.FTP.Linux;


public abstract class FileManager {
    private static FileStorage storage = new VFSStorage();

    public FileManager() {
    }

    public static FileStorage getFileStorage() {
        return storage;
    }
}
