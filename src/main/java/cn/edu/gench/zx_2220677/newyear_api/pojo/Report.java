package cn.edu.gench.zx_2220677.newyear_api.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @TableId(type = IdType.AUTO)

    private Long reportId; // 举报记录 ID
    private Long imageId; // 被举报的图片 ID
    private Long userId; // 举报用户的 ID
    private String reportReason; // 举报理由
    private Long adminId; // 处理该举报的管理员 ID
    private LocalDateTime processedAt; // 举报处理时间
    private String status; // 举报状态，默认为 "PENDING"
    private LocalDateTime reportTime; // 举报时间，默认为当前时间戳

}
