package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnnouncementService extends IService<Announcement> {
    // 查询所有公告（按发布时间升序）
    List<Announcement> findAllAnnouncements();

    // 查询所有公告（按发布时间倒序）
    List<Announcement> findReverseTimeAnnouncements();

    // 查询某管理员发布的所有公告
    List<Announcement> findAnnouncementsByAdminId(Long adminId);

    // 查询指定时间范围内的公告
    List<Announcement> findAnnouncementsByTimeRange(String startTime, String endTime);

    // 查询用户未读的公告
    List<Announcement> findUnreadAnnouncementsByUserId(Long userId);
}