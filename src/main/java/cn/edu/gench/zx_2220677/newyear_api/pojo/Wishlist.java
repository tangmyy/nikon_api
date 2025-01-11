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
public class Wishlist {

    @TableId(type = IdType.AUTO)

    private Long wishlistId; // 愿望单 ID
    private Long userId; // 用户 ID
    private Long imageId; // 图片 ID
    private LocalDateTime addTime; // 图片添加到愿望单的时间，默认为当前时间戳
}
