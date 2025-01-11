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
public class Complaint {

    @TableId(type = IdType.AUTO)

    private Long complaintId; // 投诉 ID
    private Long userId; // 投诉用户 ID
    private String complaintText; // 投诉内容
    private String status; // 投诉状态，默认为 "PENDING"
    private Long handledBy; // 处理管理员 ID（可以为空，代表尚未处理）
    private LocalDateTime handledTime; // 处理时间（如果已处理）
    private LocalDateTime createdTime; // 创建时间，默认为当前时间戳

}
