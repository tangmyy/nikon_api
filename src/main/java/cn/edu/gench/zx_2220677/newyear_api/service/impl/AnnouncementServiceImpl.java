//package cn.edu.gench.zx_2220677.newyear_api.service.impl;
//
//import cn.edu.gench.zx_2220677.newyear_api.mapper.AnnouncementMapper;
//import cn.edu.gench.zx_2220677.newyear_api.pojo.Announcement;
//import cn.edu.gench.zx_2220677.newyear_api.service.AnnouncementService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {
//
//    // 查询所有公告（按发布时间升序）
//    @Override
//    public List<Announcement> findAllAnnouncements() {
//        return baseMapper.findAllAnnouncements();
//    }
//
//    // 查询所有公告（按发布时间倒序）
//    @Override
//    public List<Announcement> findReverseTimeAnnouncements() {
//        return baseMapper.findReverseTimeAnnouncements();
//    }
//
//    // 查询某管理员发布的所有公告
//    @Override
//    public List<Announcement> findAnnouncementsByAdminId(Long adminId) {
//        return baseMapper.findAnnouncementsByAdminId(adminId);
//    }
//
//    // 查询指定时间范围内的公告
//    @Override
//    public List<Announcement> findAnnouncementsByTimeRange(String startTime, String endTime) {
//        return baseMapper.findAnnouncementsByTimeRange(startTime, endTime);
//    }
//
//    // 查询用户未读的公告
////    @Override
////    public List<Announcement> findUnreadAnnouncementsByUserId(Long userId) {
////        List<Long> readAnnouncementIds = baseMapper.findReadAnnouncementsByUserId(userId);
////        return baseMapper.findAllAnnouncements().stream()
////                .filter(announcement -> !readAnnouncementIds.contains(announcement.getId()))
////                .toList();
////    }
//}