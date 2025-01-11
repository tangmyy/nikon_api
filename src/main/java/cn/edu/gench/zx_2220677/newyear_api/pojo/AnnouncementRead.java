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
public class AnnouncementRead {

    @TableId(type = IdType.AUTO)

    private Long userId; // 用户 ID
    private Long announcementId; // 公告 ID
    private LocalDateTime readTime; // 阅读时间，默认为当前时间戳
}
