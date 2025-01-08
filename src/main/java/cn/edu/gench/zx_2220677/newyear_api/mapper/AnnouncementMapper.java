package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    // 1. 查询所有公告（按发布时间倒序）
    List<Announcement> findAllAnnouncements();

    // 2. 查询公告详情
    Announcement findAnnouncementById(Long announcementId);

    // 3. 查询某管理员发布的所有公告
    List<Announcement> findAnnouncementsByAdminId(Long adminId);

    // 4. 查询指定时间范围内的公告
    List<Announcement> findAnnouncementsByTimeRange(String startTime, String endTime);

    // 5. 分页查询公告
    List<Announcement> findAnnouncementsWithPagination(int offset, int limit);
}