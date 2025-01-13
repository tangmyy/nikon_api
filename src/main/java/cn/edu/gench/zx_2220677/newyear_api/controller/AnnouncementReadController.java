//package cn.edu.gench.zx_2220677.newyear_api.controller;
//
//import cn.edu.gench.zx_2220677.newyear_api.pojo.AnnouncementRead;
//import cn.edu.gench.zx_2220677.newyear_api.service.AnnouncementReadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/announcements/read")
//public class AnnouncementReadController {
//
//    private final AnnouncementReadService announcementReadService;
//
//    @Autowired
//    public AnnouncementReadController(AnnouncementReadService announcementReadService) {
//        this.announcementReadService = announcementReadService;
//    }
//
//    // 标记公告为已读
//    @PostMapping("/{userId}/{announcementId}/read")
//    public ResponseEntity<Void> markAsRead(@PathVariable Long userId, @PathVariable Long announcementId) {
//        if (announcementReadService.markAsRead(userId, announcementId)) {
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.status(400).build(); // Or another appropriate error code
//        }
//    }
//
//    // 检查是否已读
//    @GetMapping("/{userId}/{announcementId}/read")
//    public ResponseEntity<Boolean> checkReadStatus(@PathVariable Long userId, @PathVariable Long announcementId) {
//        boolean isRead = announcementReadService.isRead(userId, announcementId);
//        return ResponseEntity.ok(isRead);
//    }
//}