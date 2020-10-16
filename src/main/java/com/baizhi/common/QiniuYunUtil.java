package com.baizhi.common;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QiniuYunUtil {
    public static void main(String[] args) throws Exception{
        /*upload();*/
    }

    public static String upload(String localFile) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "6zA8zfk25pluDuUU_CQkFImZe4Z_iV6Hgb-FGfJ8";
        String secretKey = "adK2lH0kW8y-DmUJRGosVLJHAROw6s01cWLycbJV";
        String bucket = "zpvideos";
        //文件名+路径
        String localFilePath=localFile;

        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        //String localFilePath = "D:\\HouQiXiangMu\\baizhi-yxue-module-system\\src\\main\\webapp\\upload_file\\videos\\b4d6a834e2661d8d097bed73d06f104a.mp4";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        StringMap putPolicy = new StringMap();
        //自定义返回数据的结果
        /*putPolicy.put("returnBody", "{\"hash\":\"Ftgm-CkWePC9fzMBTRNmPMhGBcSV\",\"key\":\"qiniu.jpg\"}");*/
        //超时时间
        long expireSeconds = 3600;
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        System.out.println(upToken);

        String QiNum=null;
        try {
            //上传
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            /**
             * http://qh5yvg3ne.hn-bkt.clouddn.com/eat.mp4  将资源在七牛云的外网访问链接存入数据库
             */

            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            QiNum=putRet.hash;
            System.out.println("wqeqpweasdajpo============"+QiNum);
            //http://qh5yvg3ne.hn-bkt.clouddn.com/FsfolBFS8UsHn5mZdksamXNcd0bS
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return  QiNum;
    }
}
