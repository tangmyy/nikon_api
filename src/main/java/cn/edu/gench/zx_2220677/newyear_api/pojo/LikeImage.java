package cn.edu.gench.zx_2220677.newyear_api.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeImage {

    @TableId(type = IdType.AUTO)
    private Long likeId;          // 点赞记录 ID
    private Long imageId;         // 图片 ID
    private Long userId;          // 用户 ID
    private BigDecimal value;     // 评分
    private Status status;        // 点赞状态：LIKED 或 UNLIKED
    private LocalDateTime imageLikeTime;  // 点赞时间

    // 点赞状态的枚举类型
    public enum Status {
        LIKED,     // 已点赞
        UNLIKED    // 未点赞
    }
}
