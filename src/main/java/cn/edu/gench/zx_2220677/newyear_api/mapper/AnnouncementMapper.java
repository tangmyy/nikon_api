package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface AnnouncementMapper extends BaseMapper<Announcement> {
    // 查询所有公告（按发布时间升序）
    @Select("SELECT * FROM announcement ORDER BY created_at ASC")
    List<Announcement> findAllAnnouncements();

    // 查询所有公告（按发布时间倒序）
    @Select("SELECT * FROM announcement ORDER BY created_at DESC")
    List<Announcement> findReverseTimeAnnouncements();

    // 查询某管理员发布的所有公告
    @Select("SELECT * FROM announcement WHERE created_by = #{adminId}")
    List<Announcement> findAnnouncementsByAdminId(@Param("adminId") Long adminId);

    // 查询指定时间范围内的公告
    @Select("SELECT * FROM announcement WHERE created_at BETWEEN #{startTime} AND #{endTime}")
    List<Announcement> findAnnouncementsByTimeRange(@Param("startTime") String startTime, @Param("endTime") String endTime);
    // 查询用户已读的公告ID列表
    @Select("SELECT announcement_id FROM announcement_read WHERE user_id = #{userId}")
    List<Long> findReadAnnouncementsByUserId(@Param("userId") Long userId);
}

