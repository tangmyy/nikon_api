package cn.edu.gench.zx_2220677.newyear_api.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long commentId; // 评论 ID
    private Long parentCommentId; // 上级评论 ID（用于回复评论）
    private Long imageId; // 图片 ID
    private Long userId; // 用户 ID
    private String commentText; // 评论内容
    private boolean isLiked; // 是否点赞，默认为 false
    private LocalDateTime createdAt; // 创建时间，默认为当前时间戳

}
