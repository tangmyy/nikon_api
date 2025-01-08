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
public class CommentLike {

    @TableId(type = IdType.AUTO)


    private Long likeId; // 点赞记录 ID
    private Long userId; // 用户 ID
    private Long commentId; // 评论 ID
    private LocalDateTime createdAt; // 点赞时间，默认为当前时间戳
}
