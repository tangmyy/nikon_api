package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Complaint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ComplaintMapper extends BaseMapper<Complaint> {

    // 1. 查询用户提交的所有投诉记录
    List<Complaint> findComplaintsByUserId(Long userId);

    // 2. 查询用户某条投诉的详情
    Complaint findComplaintByIdAndUserId(Long complaintId, Long userId);

    // 3. 查询所有待处理的投诉（管理员功能）
    List<Complaint> findPendingComplaints();

    // 4. 查询某个投诉的详细信息
    Complaint findComplaintById(Long complaintId);

    // 5. 查询某个管理员处理的所有投诉记录
    List<Complaint> findComplaintsByAdminId(Long adminId);

    // 6. 查询指定时间段内的投诉记录
    List<Complaint> findComplaintsByTimeRange(String startTime, String endTime);

    // 7. 分页查询投诉记录
    List<Complaint> findComplaintsWithPagination(int offset, int limit);
}