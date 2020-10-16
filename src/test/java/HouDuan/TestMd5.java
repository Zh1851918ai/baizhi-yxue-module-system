package HouDuan;

import com.baizhi.common.MD5Util;

import java.io.File;

public class TestMd5 {
    public static void main(String[] args) {
        String fileMD5 = MD5Util.getFileMD5(new File("D:\\HouQiXiangMu\\baizhi-yxue-module-system\\src\\main\\webapp\\upload_file\\videos\\b4d6a834e2661d8d097bed73d06f104a.mp4"));
        System.out.println(fileMD5);
    }
}
