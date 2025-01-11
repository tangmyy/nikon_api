package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AnnouncementReadMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.AnnouncementRead;
import cn.edu.gench.zx_2220677.newyear_api.service.AnnouncementReadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementReadServiceImpl extends ServiceImpl<AnnouncementReadMapper, AnnouncementRead> implements AnnouncementReadService {

    // 查询用户已读的所有公告
    @Override
    public List<Long> findReadAnnouncementsByUserId(Long userId) {
        return baseMapper.findReadAnnouncementsByUserId(userId);
    }

    // 查询某公告的已读用户列表
    @Override
    public List<Long> findUsersByAnnouncementId(Long announcementId) {
        return baseMapper.findUsersByAnnouncementId(announcementId);
    }

    // 判断用户是否已读某公告
    @Override
    public boolean isAnnouncementReadByUser(Long userId, Long announcementId) {
        return baseMapper.isAnnouncementReadByUser(userId, announcementId);
    }

    // 标记公告为已读
    @Override
    public void markAsRead(Long userId, Long announcementId) {
        AnnouncementRead announcementRead = new AnnouncementRead();
        announcementRead.setUserId(userId);
        announcementRead.setAnnouncementId(announcementId);
        save(announcementRead);
    }
}