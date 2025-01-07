package cn.edu.gench.zx_2220677.newyear_api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blacklist {
    private Long userId; // 被列入黑名单的用户 ID
    private String reason; // 被列入黑名单的原因
    private LocalDateTime startTime; // 开始时间，默认为当前时间戳
    private LocalDateTime endTime; // 结束时间（可为空，代表永久封禁）
    private LocalDateTime createdAt; // 添加到黑名单的时间，默认为当前时间戳

}
