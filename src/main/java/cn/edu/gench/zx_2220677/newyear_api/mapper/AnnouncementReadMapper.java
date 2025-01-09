package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.AnnouncementRead;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementReadMapper extends BaseMapper<AnnouncementRead> {

    // 1. 查询用户已读的所有公告
    List<Long> findReadAnnouncementsByUserId(Long userId);

    // 2. 查询某公告的已读用户列表
    List<Long> findUsersByAnnouncementId(Long announcementId);

    // 3. 判断用户是否已读某公告
    boolean isAnnouncementReadByUser(Long userId, Long announcementId);

    // 4. 插入用户阅读记录
    int insertReadRecord(Long userId, Long announcementId);
}
