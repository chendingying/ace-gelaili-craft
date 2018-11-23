package com.ace.product.vo;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 *  * @param host FTP服务器hostname
 * @param port FTP服务器端口
 * @param username FTP登录账号
 * @param password FTP登录密码
 * @param remotePath FTP服务器上的相对路径
 * @param fileName 要下载的文件名
 * @param localPath 下载后保存到本地的路径
 */
/**
 * Created by CDZ on 2018/11/20.
 */
@Component
@PropertySource({"classpath:ftp.properties"})
public class FtpDownLoad {
//    /**
//     * Description: 向FTP服务器上传文件
//     * @param host FTP服务器hostname
//     * @param port FTP服务器端口
//     * @param username FTP登录账号
//     * @param password FTP登录密码
//     * @param basePath FTP服务器基础目录
//     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
//     * @param filename 上传到FTP服务器上的文件名
//     * @param input 输入流
//     * @return 成功返回true，否则返回false
//     */

    @Value("${ftp.host}")
    private  String host;

    @Value("${ftp.port}")
    private  Integer port;

    @Value("${ftp.username}")
    private  String username;

    @Value("${ftp.password}")
    private  String password;

    @Value("${ftp.basePath}")
    private  String basePath;

    @Value("${ftp.filePath}")
    private  String filePath;

    public  boolean uploadFile(String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.setControlEncoding("utf-8");
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            filename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
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
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件

     * @return
     */
    public boolean downloadFile(String localPath) {

//        String host = "192.168.249.211";
//        Integer port = 21;
//        String username = "ftpuser";
//        String password = "123456";
//        String remotePath ="/pub";
        String fileName = "工艺信息导入模板.xlsx";
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            ftp.setControlEncoding("utf-8");
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.changeWorkingDirectory(basePath);// 转移到FTP服务器目录
            ftp.enterLocalPassiveMode(); //在这里加上这行代码。重要,要不在listfiles时候会卡住不动,同时不会报错
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                //创建本地的文件时候要把编码格式转回来
//                String localFileName=new String(ff.getName().getBytes("UTF-8"),"UTF-8");
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(new String(ff.getName().getBytes("UTF-8"),"ISO-8859-1"), is);
                    is.close();
                }
            }
            ftp.logout();
            result = true;
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
        return result;
    }

    public static void main(String[] args) {
        try {
//            FileInputStream in=new FileInputStream(new File("D:\\马.jpg"));
//            boolean flag = uploadFile("马.jpg",in);
//            System.out.println(flag);
//            downloadFile("D:\\");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
