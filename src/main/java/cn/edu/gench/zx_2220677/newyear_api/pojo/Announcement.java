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
public class Announcement {

    @TableId(type = IdType.AUTO)

    private Long announcementId; // 公告 ID
    private Long createdAdminId; // 创建公告的管理员 ID
    private String title; // 公告标题
    private String content; // 公告内容
    private LocalDateTime createdTime; // 创建时间，默认为当前时间戳

}
