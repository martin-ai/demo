package com.example.demo.linuxFTP;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.UriParser;
import org.apache.commons.vfs2.provider.ftp.FtpFileSystemConfigBuilder;

import java.io.*;

public class VFSStorage implements FileStorage {
    private String encoding = "UTF-8";
    private VFSStorage.MatchesAllFileSelector fileSelector = new VFSStorage.MatchesAllFileSelector();

    public VFSStorage() {
    }

    public FileObject resolveFile(String filePath, FileSystemOptions opts) throws Exception {
        return opts != null ? VFS.getManager().resolveFile(filePath, opts) : VFS.getManager().resolveFile(filePath);
    }

    public Object getFileSystemManager() throws Exception {
        return VFS.getManager();
    }

    public OutputStream getFileOutputStream(String filePath) throws Exception {
        FileObject file = this.createFileObject(filePath);
        return file != null ? new VFSStorage.AutoCompletedOutputStream(file.getContent().getOutputStream(), file) : null;
    }

    public InputStream getFileInputStream(String filePath) throws Exception {
        FileObject file = this.createFileObject(filePath);
        return file != null ? new VFSStorage.AutoCompletedInputStream(file.getContent().getInputStream(), file) : null;
    }

    public void write(InputStream content, String filePath) throws Exception {
        this.write(content, filePath, true);
    }

    public void write(InputStream content, String filePath, boolean override) throws Exception {
        this.write(content, this.createFileObject(filePath), override);
    }

    public void write(InputStream content, FileObject file, boolean override) throws Exception {
        OutputStream os = null;

        try {
            if (override || !file.exists()) {
                os = file.getContent().getOutputStream();
                FileUtils.pipeStream(content, os);
            }
        } finally {
            Closer.close(new AutoCloseable[]{os});
            file.getContent().close();
            file.close();
        }

    }

    public void delete(String filePath) throws Exception {
        this.delete(this.createFileObject(filePath));
    }

    public void delete(FileObject file) throws Exception {
        if (file.exists()) {
            FileType fileType = file.getType();
            if (fileType == FileType.FOLDER) {
                file.delete(this.fileSelector);
            } else {
                file.delete();
            }
        }

    }

    public void deleteDir(String filePath, FileNameFilter filter) throws Exception {
        this.deleteDir(this.createFileObject(filePath), filter);
    }

    public void deleteDir(FileObject file, final FileNameFilter filter) throws Exception {
        if (file.exists()) {
            FileType fileType = file.getType();
            if (fileType == FileType.FOLDER) {
                if (filter != null) {
                    file.delete(new FileSelector() {
                        public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
                            String filename = fileInfo.getFile().getName().getBaseName();
                            return filter.accept(filename);
                        }

                        public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
                            return true;
                        }
                    });
                } else {
                    file.delete(this.fileSelector);
                }
            } else {
                file.delete();
            }
        }

    }

    public long read(String filePath, OutputStream os) throws Exception {
        return this.read(this.createFileObject(filePath), os);
    }

    public long read(FileObject file, OutputStream os) throws Exception {
        InputStream is = null;

        long var4;
        try {
            if (!file.exists()) {
                var4 = -1L;
                return var4;
            }

            is = file.getContent().getInputStream();
            FileUtils.pipeStream(is, os);
            var4 = file.getContent().getSize();
        } finally {
            file.getContent().close();
            file.close();
        }

        return var4;
    }

    public void copyFile(String source, String target) throws Exception {
        FileObject sourceFile = this.createFileObject(source);
        FileObject targetFile = this.createFileObject(target);
        targetFile.copyFrom(sourceFile, new AllFileSelector());
    }

    public void copyDir(String source, String target, final FileNameFilter filter) throws Exception {
        FileObject sourceFile = this.createFileObject(source);
        FileObject targetFile = this.createFileObject(target);
        targetFile.copyFrom(sourceFile, new FileSelector() {
            public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
                return filter.accept(fileInfo.getFile().getName().getPath());
            }

            public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
                return filter.accept(fileInfo.getFile().getName().getPath());
            }
        });
    }

    public void move(String target, String dest) throws Exception {
        FileObject targetFile = this.createFileObject(target);
        FileObject destFile = this.createFileObject(dest);
        targetFile.moveTo(destFile);
    }

    public boolean isDir(String filePath) throws Exception {
        return this.isDir(this.createFileObject(filePath));
    }

    public boolean isDir(FileObject file) throws Exception {
        return file.exists() ? file.getType() == FileType.FOLDER : false;
    }

    public boolean exists(String filePath) throws Exception {
        return this.exists(this.createFileObject(filePath));
    }

    public boolean exists(FileObject file) throws Exception {
        boolean var2;
        try {
            var2 = file.exists();
        } finally {
            file.close();
        }

        return var2;
    }

    public String[] list(String filePath) throws Exception {
        FileObject fileObject = this.createFileObject(filePath);
        FileObject[] files = fileObject.findFiles(this.fileSelector);
        String[] rslt = new String[files.length];
        int idx = 0;
        FileObject[] var6 = files;
        int var7 = files.length;

        for (int var8 = 0; var8 < var7; ++var8) {
            FileObject file = var6[var8];
            rslt[idx++] = file.getName().getPath();
        }

        return rslt;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    protected FileObject createFileObject(String filePath) throws Exception {
        String scheme = UriParser.extractScheme(filePath);
        if (scheme != null) {
            FileSystemOptions opts = new FileSystemOptions();
            FileSystemConfigBuilder builder = VFS.getManager().getFileSystemConfigBuilder(scheme);
            if (builder instanceof FtpFileSystemConfigBuilder) {
                ((FtpFileSystemConfigBuilder) builder).setPassiveMode(opts, Boolean.TRUE.booleanValue());
                ((FtpFileSystemConfigBuilder) builder).setControlEncoding(opts, this.encoding);
            }

            return VFS.getManager().resolveFile(filePath, opts);
        } else {
            return VFS.getManager().resolveFile(filePath);
        }
    }

    private static class AutoCompletedOutputStream extends FilterOutputStream {
        private FileObject file;

        AutoCompletedOutputStream(OutputStream os, FileObject file) {
            super(os);
            this.file = file;
        }

        public void close() throws IOException {
            super.close();
            this.file.getContent().close();
        }
    }

    private static class AutoCompletedInputStream extends FilterInputStream {
        private FileObject file;

        AutoCompletedInputStream(InputStream is, FileObject file) {
            super(is);
            this.file = file;
        }

        public void close() throws IOException {
            super.close();
            this.file.getContent().close();
        }
    }

    private static class MatchesAllFileSelector implements FileSelector {
        private MatchesAllFileSelector() {
        }

        public boolean includeFile(FileSelectInfo fileInfo) throws Exception {
            return true;
        }

        public boolean traverseDescendents(FileSelectInfo fileInfo) throws Exception {
            return true;
        }
    }
}