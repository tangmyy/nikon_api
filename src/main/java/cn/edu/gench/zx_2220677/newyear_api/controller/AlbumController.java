package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Users;

import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * AlbumController 提供 RESTful API 接口。
 */
@CrossOrigin
@RestController
@RequestMapping("/api/images")
public class AlbumController {

   @Resource
   private AlbumService albumService;

   private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

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

   // Get 查询 模糊搜索
   @GetMapping("/search")
   public List<Album> searchGalleries(@RequestParam("keyword") String keyword,HttpSession session) {
      Users user = (Users) session.getAttribute("user");
      if (user == null) {
         return null;
      }
      return albumService.FuzzySearch(keyword);
   }

   // Get 查询 查询个人图片
   @GetMapping("/user")
   public List<Album> findImagesByUserId(HttpSession session) {
      Users user = (Users) session.getAttribute("user");
      if (user != null) {
         return albumService.findImagesByUserId(user.getUserId());

      } else {
         return null;
      }
   }

   // Delete 删除 删除图片
   @PutMapping("/delete")
   public ResponseEntity<String> deleteImage(@RequestBody Map<String, Long> payload, HttpSession session) {
      Long imageId = payload.get("imageId");
      Users user = (Users) session.getAttribute("user");
      if (user == null) {
         return ResponseEntity.status(401).body("用户未登录");
      }
      try {
         boolean result = albumService.deleteImage(imageId);
         if (result) {
            return ResponseEntity.ok("删除成功");
         } else {
            return ResponseEntity.status(500).body("删除失败");
         }
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(500).body("服务器内部错误");
      }
   }



   // Put 修改 修改图片属性
   @PutMapping("/update")
   public String updateImage(@RequestParam("imageId") Long imageId,
                             @RequestParam("isPublic") String isPublic,
                             @RequestParam("description") String description,
                             @RequestParam("tags") String tagsJson,
                             @RequestParam("price") BigDecimal price,
                             HttpSession session) {
      Users user = (Users) session.getAttribute("user");
      if (user == null) {
         return "用户未登录";
      }

      // 打印接收到的参数
      logger.info("接收到的更新参数：");
      logger.info("imageId: {}", imageId);
      logger.info("isPublic: {}", isPublic);
      logger.info("description: {}", description);
      logger.info("tagsJson: {}", tagsJson);
      logger.info("price: {}", price);
      logger.info("当前用户ID: {}", user.getUserId());
      try {
         return albumService.updateImage(imageId, isPublic, description, tagsJson, price, user.getUserId());
      } catch (IOException e) {
         e.printStackTrace();
         return "更新失败";
      }
   }



}
