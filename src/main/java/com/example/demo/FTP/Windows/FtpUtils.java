package com.example.demo.FTP.Windows;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yixiang.guo on 2018/9/4.
 */
public class FtpUtils {

    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "10.0.0.158";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "ftpUser1";
    //密码
    private static final String FTP_PASSWORD = "111111";
    //图片路径
    private static final String FTP_BASEPATH = "/test";

    public static boolean login() {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            reply = ftp.getReplyCode();
            System.out.println(reply);
            success = ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            System.out.println(reply);
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(FTP_BASEPATH);
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            ftp.logout();
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    public static boolean uploadFile(String originFileName, InputStream input) {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(FTP_BASEPATH);
            ftp.changeWorkingDirectory(FTP_BASEPATH);
            ftp.storeFile(originFileName, input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }

}
