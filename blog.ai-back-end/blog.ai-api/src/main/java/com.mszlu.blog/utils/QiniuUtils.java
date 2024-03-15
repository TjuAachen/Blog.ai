package com.mszlu.blog.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QiniuUtils {

    public static  final String url = "http://rmxak971p.hb-bkt.clouddn.com/";

    @Value("${qiniu.accessKey}")
    private  String accessKey;
    @Value("${qiniu.accessSecretKey}")
    private  String accessSecretKey;

    public  boolean upload(MultipartFile file,String fileName){


        Configuration cfg = new Configuration(Region.autoRegion());

        UploadManager uploadManager = new UploadManager(cfg);

        String bucket = "neu5500group33";

        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
                Response response = uploadManager.put(uploadBytes, fileName, upToken);

                DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
                return true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        return false;
    }

    public void deleteAll() throws QiniuException {
        String bucket = "wwwmszlucom";
        Auth auth = Auth.create(accessKey, accessSecretKey);
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huabei());
        BucketManager bucketManager = new BucketManager(auth,cfg);
        FileListing fileListing = bucketManager.listFiles(bucket, "/", "", 1000, "");
        FileInfo[] items = fileListing.items;
        for (FileInfo info : items){

        }
    }
}
