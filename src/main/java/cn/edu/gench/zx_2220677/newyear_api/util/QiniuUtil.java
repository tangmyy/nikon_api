package cn.edu.gench.zx_2220677.newyear_api.util;

import cn.edu.gench.zx_2220677.newyear_api.config.QiniuConfig;
import com.qiniu.util.Auth;

public class QiniuUtil {
   public static String getUploadToken() {
      Auth auth = QiniuConfig.getAuth();
      return auth.uploadToken(QiniuConfig.BUCKET_NAME);
   }
}
