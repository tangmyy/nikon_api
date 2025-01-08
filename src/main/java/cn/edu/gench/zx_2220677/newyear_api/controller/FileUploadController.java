package cn.edu.gench.zx_2220677.newyear_api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    // 名称 头像
    public String up(String nickname, MultipartFile photo, HttpServletRequest request)throws IOException {

        // request用于获取服务器动态链接
        System.out.println(nickname);
        // 获取图片名称
        System.out.println(photo.getOriginalFilename());
        // 获取文件类型
        System.out.println(photo.getContentType());

        String path = request.getServletContext().getRealPath("/upload/");
        // 上面这个是使用动态的服务器路径，下面这个是固定的本地路径
        // 想要获取需要去application.properties中增加配置
        // String path = "C:\\Users/TAOER/Desktop";
        System.out.println(path);
        saveFile(photo,path);
        return "upload上传成功";
    }

    public void saveFile(MultipartFile photo,String path)throws IOException {
      //判断文件是否存在如果不存在则创建一个
      File dir =new File(path);
      if(!dir.exists()){
         //创建目录
         dir.mkdir();
      }
      File file = new File(path+photo.getOriginalFilename());
      photo.transferTo(file);
    }


}
