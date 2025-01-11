package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Users;

import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * AlbumController 提供 RESTful API 接口。
 */
@RestController
@RequestMapping("/api/images")
public class AlbumController {

   @Resource
   private AlbumService albumService;

   // 图片上传
   @Operation(summary = "图片上传接口", description = "此接口用于用户上传图片")
   @PostMapping("/upload")
   public String uploadImage(@RequestParam("file") MultipartFile file,
                             @RequestParam("isPublic") String isPublic,
                             @RequestParam("description") String description,
                            @RequestParam("tags") String tagsJson,
                            @RequestParam("price") BigDecimal price,
                            HttpSession session) {
      Users user = (Users) session.getAttribute("user");
      if (user == null) {
         return "用户未登录";
      }
      try {
         return albumService.uploadImage(file, isPublic, description, tagsJson, price, user.getUserId());
      } catch (IOException e) {
         e.printStackTrace();
         return "上传失败";
      }
   }

   // Get 查询 查询所有图片
   @GetMapping("/public")
   public List<Album> findAllImages() {
      return albumService.findAllImages();
   }



}
