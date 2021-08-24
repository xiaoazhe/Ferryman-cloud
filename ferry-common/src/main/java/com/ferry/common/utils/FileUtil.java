package com.ferry.common.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    /**
     * 保存上传的文件
     *
     * @param file
     * @return 文件下载的url
     * @throws Exception
     */
    public static String saveFile(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            return "";
        }
        File target = new File("file");
        if (!target.isDirectory()) {
            target.mkdirs();
        }
        return getUrl(file);
    }
    //上传阿里云
    public static String getUrl(MultipartFile fileupload) throws OSSException, ClientException, IOException {
        String endpoint = "";
        String accessKeyId = "";
        String accessKeySecret = "";
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 文件桶
        String bucketName = "azhe";
        // 文件名格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        // 该桶中的文件key
        String dateString = sdf.format(new Date()) + ".jpg";//
        // 上传文件
        ossClient.putObject("azhe", dateString, new ByteArrayInputStream(fileupload.getBytes()));

        // 设置URL过期时间为100年，默认这里是int型，转换为long型即可
        Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24 * 365 * 100);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, dateString, expiration);
        return url.toString();
    }
}
