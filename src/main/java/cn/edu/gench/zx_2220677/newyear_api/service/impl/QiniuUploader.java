package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.util.QiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;

public class QiniuUploader {
   public static void uploadStream(byte[] data, String key) {
      // 配置上传管理器
      Configuration cfg = new Configuration(Region.region0());
      UploadManager uploadManager = new UploadManager(cfg);

      // 获取上传凭证
      String uploadToken = QiniuUtil.getUploadToken();

      try {
         // 上传字节数组
         Response response = uploadManager.put(data, key, uploadToken);

         // 打印响应
         System.out.println(response.bodyString());
      } catch (QiniuException ex) {
         System.err.println(ex.response.toString());
      }
   }
}

