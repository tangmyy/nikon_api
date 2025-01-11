package cn.edu.gench.zx_2220677.newyear_api.config;

import com.qiniu.util.Auth;

public class QiniuConfig {
   public static final String ACCESS_KEY = "<你的 Access Key>";
   public static final String SECRET_KEY = "<你的 Secret Key>";
   public static final String BUCKET_NAME = "<你的存储空间名称>";
   public static final String DOMAIN = "<你的空间绑定的域名>"; // 用于访问上传的资源

   public static Auth getAuth() {
      return Auth.create(ACCESS_KEY, SECRET_KEY);
   }
}

