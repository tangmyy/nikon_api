package cn.edu.gench.zx_2220677.newyear_api;


import com.google.gson.Gson;

import com.qiniu.cdn.CdnManager;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

@Component //注解将该类标记为Spring的组件
//测试类所在包名 test 和 启动类所在包名java 一致(都在同一包src下)，不需要加配置。如果不一致要加配置（加属性）
//@SpringBootTest(classes = SanGengBlogApplication.class)//指定启动类的字节码
@SpringBootTest
//@ConfigurationProperties 的作用: 让JavaBean中属性值要和配置文件application.xml进行映射
@ConfigurationProperties(prefix = "oss") //从application.xml 配置文件中读取 内容
public class OOSTest {

   //    读取的时候，要创建成员变量（变量名要和application.xml文件中的名字一致）
   //    并且 成员变量必须创建setter方法，进行自动赋值  （自动读取配置文件赋值）
   private String accessKey;
   private String secretKey;
   private String bucket;
   public void setAccessKey(String accessKey) {
      this.accessKey = accessKey;
   }
   public void setSecretKey(String secretKey) {
      this.secretKey = secretKey;
   }
   public void setBucket(String bucket) {
      this.bucket = bucket;
   }

   @Test
   public void testOss(){
      //构造一个带指定 Region 对象的配置类
      Configuration cfg = new Configuration(Region.region0());
      cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
      //...其他参数参考类注释

      UploadManager uploadManager = new UploadManager(cfg);
      //...生成上传凭证，然后准备上传
      String accessKey = "ZyQh2E_Sn4XM4RvI4yYgerx2Q0gS3qqgCBU7ZIkQ";
      String secretKey = "Imat_mD4i0nTb4MBg8nUswak42wnWnqgBVfKAqhL";
      String bucket = "mirroyun";

      //默认不指定key的情况下，以文件内容的hash值作为文件名
      String Filename = "DSC_1464.JPG";
      // String key = Filename;

      try {
         //上传文件——图片格式（本机绝对路径）
         InputStream inputStream = new FileInputStream("C:\\Users\\TAOER\\Desktop\\精选\\" + Filename);
         // byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
         // ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
         Auth auth = Auth.create(accessKey, secretKey);  //创建凭证
         String upToken = auth.uploadToken(bucket);      //上传凭证

         try {
            // Response response = uploadManager.put(byteInputStream,Filename,upToken,null, null);
            Response response = uploadManager.put(inputStream,Filename,upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
         } catch (QiniuException ex) {
            ex.printStackTrace();
            if (ex.response != null) {
               System.err.println(ex.response);

               try {
                  String body = ex.response.toString();
                  System.err.println(body);
               } catch (Exception ignored) {
               }
            }
         }
      // } catch (UnsupportedEncodingException ex) {
      } catch (Exception ex) {
         //ignore  异常类型 改大一点
      }

   }

   @Test
   public void testShowOss(){
      //构造一个带指定 Region 对象的配置类
      Configuration cfg = new Configuration(Region.region0());
      //...其他参数参考类注释

      String accessKey = "ZyQh2E_Sn4XM4RvI4yYgerx2Q0gS3qqgCBU7ZIkQ";
      String secretKey = "Imat_mD4i0nTb4MBg8nUswak42wnWnqgBVfKAqhL";
      String bucket = "mirroryun";
      String key = "zhangxingnb666";

      Auth auth = Auth.create(accessKey, secretKey);
      BucketManager bucketManager = new BucketManager(auth, cfg);
      try {
         FileInfo fileInfo = bucketManager.stat(bucket, key);
         System.out.println(fileInfo.hash);
         System.out.println(fileInfo.fsize);
         System.out.println(fileInfo.mimeType);
         System.out.println(fileInfo.putTime);
      } catch (QiniuException ex) {
         System.err.println(ex.response.toString());
      }
   }

   @Test
   public void testGetDownloadUrl(){
      String fileName = "mirror/zhangxingnb666";
      String domainOfBucket = "http://devtools.qiniu.com";
      String encodedFileName = null;
      try {
         encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }
      String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);

      String accessKey = "ZyQh2E_Sn4XM4RvI4yYgerx2Q0gS3qqgCBU7ZIkQ";
      String secretKey = "Imat_mD4i0nTb4MBg8nUswak42wnWnqgBVfKAqhL";
      Auth auth = Auth.create(accessKey, secretKey);
      long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
      String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
      System.out.println(finalUrl);
   }

   @Test
   public void testGetBucket(){
      //构造一个带指定 Region 对象的配置类
      Configuration cfg = new Configuration(Region.region0());
      //...其他参数参考类注释
      String accessKey = "ZyQh2E_Sn4XM4RvI4yYgerx2Q0gS3qqgCBU7ZIkQ";
      String secretKey = "Imat_mD4i0nTb4MBg8nUswak42wnWnqgBVfKAqhL";
      String bucket = "mirroryun";

      Auth auth = Auth.create(accessKey, secretKey);
      BucketManager bucketManager = new BucketManager(auth, cfg);

      //文件名前缀
      String prefix = "";
      //每次迭代的长度限制，最大1000，推荐值 1000
      int limit = 1000;
      //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
      String delimiter = "";

      //列举空间文件列表
      BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
      while (fileListIterator.hasNext()) {
         //处理获取的file list结果
         FileInfo[] items = fileListIterator.next();
         for (FileInfo item : items) {
            System.out.println(item.key);
            System.out.println(item.hash);
            System.out.println(item.fsize);
            System.out.println(item.mimeType);
            System.out.println(item.putTime);
            System.out.println(item.endUser);
         }
      }
   }

   @Test
   public void testGetURL(){
      String host = "http://video.example.com";
      String fileName = "zhangxingnb666";

      //查询参数
      StringMap queryStringMap = new StringMap();
      queryStringMap.put("name", "七牛");
      queryStringMap.put("year", 2017);
      queryStringMap.put("年龄", 28);

      //链接过期时间
      long deadline = System.currentTimeMillis() / 1000 + 3600;

      //签名密钥，从后台域名属性中获取
      String encryptKey = "xxx";
      String signedUrl;
      try {
         signedUrl = CdnManager.createTimestampAntiLeechUrl(host, fileName,
               queryStringMap, encryptKey, deadline);
         System.out.println(signedUrl);
      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }

}
