package cn.edu.gench.zx_2220677.newyear_api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementRead {
    private Long userId; // 用户 ID
    private Long announcementId; // 公告 ID
    private LocalDateTime readAt; // 阅读时间，默认为当前时间戳
}
