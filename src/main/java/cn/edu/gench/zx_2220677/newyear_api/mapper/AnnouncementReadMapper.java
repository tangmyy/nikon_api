package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.AnnouncementRead;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnnouncementReadMapper extends BaseMapper<AnnouncementRead> {
    // 1. 查询用户已读的所有公告
    @Select("SELECT announcement_id FROM announcement_read WHERE user_id = #{userId}")
    List<Long> findReadAnnouncementsByUserId(@Param("userId") Long userId);

    // 2. 查询某公告的已读用户列表
    @Select("SELECT user_id FROM announcement_read WHERE announcement_id = #{announcementId}")
    List<Long> findUsersByAnnouncementId(@Param("announcementId") Long announcementId);

    // 3. 判断用户是否已读某公告
    @Select("SELECT COUNT(*) > 0 FROM announcement_read WHERE user_id = #{userId} AND announcement_id = #{announcementId}")
    boolean isAnnouncementReadByUser(@Param("userId") Long userId, @Param("announcementId") Long announcementId);
}