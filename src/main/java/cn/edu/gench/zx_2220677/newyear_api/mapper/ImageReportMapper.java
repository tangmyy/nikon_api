package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.ImageReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageReportMapper extends BaseMapper<ImageReport> {

    // 1. 查询用户提交的所有举报记录
    List<ImageReport> findReportsByUserId(Long userId);

    // 2. 查询某张图片的举报记录
    List<ImageReport> findReportsByImageId(Long imageId);

    // 3. 查询所有待处理的举报记录（管理员功能）
    List<ImageReport> findPendingReports();

    // 4. 查询指定管理员处理的举报记录
    List<ImageReport> findReportsByAdminId(Long adminId);

    // 5. 查询指定时间段的举报记录
    List<ImageReport> findReportsByTimeRange(String startTime, String endTime);
}
