package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.AnnouncementRead;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnnouncementReadService extends IService<AnnouncementRead> {
    // 查询用户已读的所有公告
    List<Long> findReadAnnouncementsByUserId(Long userId);

    // 查询某公告的已读用户列表
    List<Long> findUsersByAnnouncementId(Long announcementId);

    // 判断用户是否已读某公告
    boolean isAnnouncementReadByUser(Long userId, Long announcementId);

    // 标记公告为已读
    void markAsRead(Long userId, Long announcementId);
}