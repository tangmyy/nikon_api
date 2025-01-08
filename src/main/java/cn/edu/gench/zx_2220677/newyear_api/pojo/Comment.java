package cn.edu.gench.zx_2220677.newyear_api.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @TableId(type = IdType.AUTO)
    private Long commentId;             // 评论 ID
    private Long parentCommentId;       // 上级评论 ID
    private Long imageId;               // 图片 ID
    private Long userId;                // 用户 ID
    private String commentText;         // 评论内容
    private Boolean isLiked;            // 是否点赞
    private Long createdAt;             // 创建时间
}
