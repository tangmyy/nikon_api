//package cn.edu.gench.zx_2220677.newyear_api.controller;
//
//import cn.edu.gench.zx_2220677.newyear_api.pojo.Announcement;
//import cn.edu.gench.zx_2220677.newyear_api.service.AnnouncementService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/announcements")
//public class AnnouncementController {
//
//    private final AnnouncementService announcementService;
//
//    @Autowired
//    public AnnouncementController(AnnouncementService announcementService) {
//        this.announcementService = announcementService;
//    }
//
//    // 获取所有公告（按发布时间升序）
//    @GetMapping
//    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
//        List<Announcement> announcements = announcementService.getAllAnnouncements();
//        return ResponseEntity.ok(announcements);
//    }
//
//    // 获取所有公告（按发布时间倒序）
//    @GetMapping("/newest")
//    public ResponseEntity<List<Announcement>> getReverseTimeAnnouncements() {
//        List<Announcement> announcements = announcementService.getReverseTimeAnnouncements();
//        return ResponseEntity.ok(announcements);
//    }
//
//    // 根据管理员ID获取公告
//    @GetMapping("/admin/{adminId}")
//    public ResponseEntity<List<Announcement>> getAnnouncementsByAdminId(@PathVariable Long adminId) {
//        List<Announcement> announcements = announcementService.getAnnouncementsByAdminId(adminId);
//        return ResponseEntity.ok(announcements);
//    }
//
//    // 根据时间范围获取公告
//    @GetMapping("/time-range")
//    public ResponseEntity<List<Announcement>> getAnnouncementsByTimeRange(
//            @RequestParam String startTime,
//            @RequestParam String endTime) {
//        List<Announcement> announcements = announcementService.getAnnouncementsByTimeRange(startTime, endTime);
//        return ResponseEntity.ok(announcements);
//    }
//}