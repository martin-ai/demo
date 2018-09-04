package com.example.demo.linuxFTP;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileUtils {

    public FileUtils() {
    }

    public static String getFilename(String filePath) {
        String filename = filePath;
        int lastIndex = filePath.lastIndexOf("\\");
        if (lastIndex == -1) {
            lastIndex = filePath.lastIndexOf("/");
        }

        if (lastIndex != -1) {
            filename = filePath.substring(lastIndex + 1);
        }

        return filename;
    }

    public static String getFilenameWithoutExtName(String filePath) {
        String filename = getFilename(filePath);
        int idx = filename.lastIndexOf(".");
        return idx != -1 ? filename.substring(0, idx) : filename;
    }

    public static String getFileExtName(String fileName) {
        int idx = fileName.lastIndexOf(".");
        return idx != -1 ? fileName.substring(idx + 1) : "";
    }

    public static void mkdir(File dir) {
        if (!dir.exists()) {
            File parent = getParentFile(dir);
            if (parent != null && !parent.exists()) {
                mkdir(parent);
            }

            int tryTimes = 3;
            boolean exists = false;

            while (!(exists = dir.mkdirs())) {
                --tryTimes;
                if (tryTimes < 0) {
                    break;
                }
            }

            if (!exists) {
                throw new RuntimeException("无法创建文件：" + dir.getPath());
            }
        }
    }

    public static String getParent(String path) {
        if (path.indexOf("\\") != -1) {
            return path.substring(0, path.lastIndexOf("\\"));
        } else if (path.indexOf("/") != -1) {
            return path.substring(0, path.lastIndexOf("/"));
        } else {
            throw new RuntimeException("无法得到父路径");
        }
    }

    public static String getParent(File dir) {
        if (dir.exists()) {
            return dir.getParent();
        } else {
            String path = switchPathSeparator(dir.getAbsolutePath());
            return (new File(path)).getParent();
        }
    }

    public static File getParentFile(File dir) {
        if (dir.exists()) {
            return dir.getParentFile();
        } else {
            String path = switchPathSeparator(dir.getAbsolutePath());
            return (new File(path)).getParentFile();
        }
    }

    public static void delete(File file) {
        if (file.exists()) {
            int tryTimes = 3;
            boolean del = false;

            while (!(del = file.delete())) {
                --tryTimes;
                if (tryTimes < 0) {
                    break;
                }
            }

            if (!del) {
                if (file.canWrite()) {
                    throw new RuntimeException("无法删除文件：" + file.getAbsolutePath() + "，可能文件正在被使用");
                } else {
                    throw new RuntimeException("无法删除文件：" + file.getAbsolutePath());
                }
            }
        }
    }

    public static void delete(File file, boolean delDir) {
        if (file.isDirectory()) {
            if (!delDir) {
                return;
            }

            File[] var2 = file.listFiles();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                File f = var2[var4];
                delete(f, delDir);
            }
        }

        delete(file);
    }

    public static void pipeStream(InputStream is, OutputStream os) {
        try {
            byte[] e = new byte['ꀀ'];
            boolean len = false;

            int len1;
            while ((len1 = is.read(e)) != -1) {
                os.write(e, 0, len1);
            }

            os.flush();
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }

    public static void copy(File source, File target) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(target);
            in = fis.getChannel();
            out = fos.getChannel();
            int e = 67076096;
            long size = in.size();

            for (long position = 0L; position < size; position += in.transferTo(position, (long) e, out)) {
                ;
            }
        } catch (Exception var27) {
            throw new RuntimeException("无法复制文件：" + source.getAbsolutePath(), var27);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception var26) {
                    ;
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (Exception var25) {
                    ;
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception var24) {
                    ;
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception var23) {
                    ;
                }
            }

        }

    }

    public static long length(File dir) {
        if (!dir.isDirectory()) {
            return dir.length();
        } else {
            long total = 0L;
            File[] var3 = dir.listFiles();
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                File file = var3[var5];
                total += length(file);
            }

            return total;
        }
    }

    public static String switchPathSeparator(String path) {
        char separator = File.separatorChar;
        String pathSeparator = separator == 92 ? separator + "\\" : separator + "";
        return path.replaceAll("[/\\\\]", pathSeparator);
    }

    public static String appendSperatorPrefix(String path) {
        return !path.startsWith("\\") && !path.startsWith("/") ? (path.indexOf("\\") != -1 ? "\\" + path : "/" + path) : path;
    }

    public static String appendSeparatorSuffix(String path) {
        return !path.endsWith("\\") && !path.endsWith("/") ? (path.indexOf("\\") != -1 ? path + "\\" : path + "/") : path;
    }

    public static String stripSpreartorPrefix(String path) {
        return !path.startsWith("\\") && !path.startsWith("/") ? path : path.substring(1);
    }

    public static String stripSpreartorSuffix(String path) {
        return !path.endsWith("\\") && !path.endsWith("/") ? path : path.substring(0, path.length() - 1);
    }
}
