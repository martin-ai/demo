package com.example.demo.linuxFTP;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

//FIXME：无法检测到子线程 文件历史
public abstract class FileStory {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileStory.class);
    private static ThreadLocal<Set<String>> createdFilesHolder = createHashSetThreadLocal();
    private static ThreadLocal<Set<String>> deletingFilesHolder = createHashSetThreadLocal();
    private static ThreadLocal<Set<String>> tmpFilesHolder = createHashSetThreadLocal();

    public static void addCreatedFile(String filepath) {
        if (StringUtils.isNotBlank(filepath)) {
            createdFilesHolder.get().add(filepath);
        }
    }

    public static void addDeletingFile(String fileDir, String filename) {
        if (StringUtils.isNotBlank(filename)) {
            deletingFilesHolder.get().add(fileDir + filename);
        }
    }

    public static void addTmpFile(String filepath) {
        if (StringUtils.isNotBlank(filepath)) {
            tmpFilesHolder.get().add(filepath);
        }
    }

    public static void cleanUpCreatedFile() {
        deleteFiles(createdFilesHolder.get());
    }

    public static void cleanUpDeletingFile() {
        deleteFiles(deletingFilesHolder.get());
    }

    public static void cleanUpTmpFile() {
        deleteFiles(tmpFilesHolder.get());
    }

    public static void clear() {
        createdFilesHolder.get().clear();
        deletingFilesHolder.get().clear();
        tmpFilesHolder.get().clear();
    }

    private static void deleteFiles(Set<String> filepaths) {
        filepaths.stream().forEach(x -> {
            try {
                FileHelper.deleteFile(x);
            } catch (Exception e) {
                LOGGER.info(String.format("删除文件\"%s\"失败", x), e);
            }
        });
    }

    private static ThreadLocal<Set<String>> createHashSetThreadLocal() {
        return ThreadLocal.withInitial(() -> new HashSet<String>());
    }

}