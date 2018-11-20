package com.ace.product.biz;

import com.ace.product.vo.FtpDownLoad;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by CDZ on 2018/11/19.
 */
@Service
public class UpLoadBiz {

    public boolean upLoadImg(String filename,InputStream inputStream) {
        return FtpDownLoad.uploadFile(filename,inputStream);
//        Boolean flag = FtpFileUtil.uploadFile(path, inputStream);
//        if (flag == true) {
//            System.out.println("ftp上传成功！");
//        }
    }
}
