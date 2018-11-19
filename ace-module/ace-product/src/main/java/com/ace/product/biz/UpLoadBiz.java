package com.ace.product.biz;

import com.ace.product.vo.FtpFileUtil;
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

    public void upLoadImg(String path) {
        // TODO Auto-generated method stub
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Boolean flag = FtpFileUtil.uploadFile(path, inputStream);
        if (flag == true) {
            System.out.println("ftp上传成功！");
        }
    }
}
