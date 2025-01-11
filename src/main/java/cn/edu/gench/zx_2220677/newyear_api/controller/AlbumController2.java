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
import java.time.LocalDateTime;
import java.util.List;

/**
 * AlbumController 提供 RESTful API 接口。
 */
@RestController
@RequestMapping("/api/images")
public class AlbumController2 {

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





   // 根据标签查询图片
   @Operation(summary = "标签查询接口", description = "此接口用于用户按标签查询图片")
   @GetMapping("/tag/{tag}")
   public List<Album> findImagesByTag(@PathVariable String tag) {
      return albumService.findImagesByTag(tag);
   }

   // 查询所有公开的图片
   @Operation(summary = "公开图片查询接口", description = "此接口用于查询所有公开的图片")
   @GetMapping("/public")
   public List<Album> findAllPublicImages() {
      return albumService.findAllPublicImages();
   }

   // 查询所有私人图片
   @Operation(summary = "私人图片查询接口", description = "此接口用于查询所有私人的图片")
   @GetMapping("/private")
   public List<Album> findPrivateImages() {
      return albumService.findPrivateImages();
   }

   // 按上传时间升序查询
   @Operation(summary = "上传时间（升序）查询接口", description = "此接口用于查询按上传时间（升序）的图片")
   @GetMapping("/upload/asc")
   public List<Album> findUploadUp() {
      return albumService.findUploadUp();
   }

   // 按上传时间降序查询
   @Operation(summary = "上传时间（降序）查询接口", description = "此接口用于查询按上传时间（降序）的图片")
   @GetMapping("/upload/desc")
   public List<Album> findUploadDecline() {
      return albumService.findUploadDecline();
   }

   // 按描述模糊查询图片
   @Operation(summary = "描述模糊查询接口", description = "此接口用于查询按描述模糊查询的图片")
   @GetMapping("/search")
   public List<Album> findByDescription(@RequestParam String description) {
      return albumService.findByDescription(description);
   }

   // 分页查询
   // orderBy 是一个动态参数，用于指定排序顺序
   // "asc" 表示升序，"desc" 表示降序
   @Operation(summary = "分页查询接口", description = "支持按标签、公开/私有状态、描述模糊查询并分页")
   @GetMapping("/page")
   public IPage<Album> findAlbumsWithPagination(
         @RequestParam(defaultValue = "1") int pageNo,  // 默认页码为第1页
         @RequestParam(defaultValue = "10") int pageSize,  // 默认每页10条
         @RequestParam(required = false) String tag,
         @RequestParam(required = false) String visibility,
         @RequestParam(required = false) String description,
         @RequestParam(required = false, defaultValue = "desc") String orderBy) {

      Page<Album> page = new Page<>(pageNo, pageSize);
      return albumService.findAlbumsWithConditions(page, tag, visibility, description, orderBy);
   }
}
