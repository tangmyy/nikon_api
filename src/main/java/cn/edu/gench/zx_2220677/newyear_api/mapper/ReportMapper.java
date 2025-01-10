package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper extends BaseMapper<Report> {

    // 1. 查询用户提交的所有举报记录
    List<Report> findReportsByUserId(Long userId);

    // 2. 查询某张图片的举报记录
    List<Report> findReportsByImageId(Long imageId);

    // 3. 查询所有待处理的举报记录（管理员功能）
    List<Report> findPendingReports();

    // 4. 查询指定管理员处理的举报记录
    List<Report> findReportsByAdminId(Long adminId);

    // 5. 查询指定时间段的举报记录
    List<Report> findReportsByTimeRange(String startTime, String endTime);
}
