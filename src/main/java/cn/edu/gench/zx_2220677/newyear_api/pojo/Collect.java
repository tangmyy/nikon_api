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
public class Collect {

    @TableId(type = IdType.AUTO)

    private Long favoriteId; // 收藏记录 ID
    private Long userId; // 用户 ID
    private Long imageId; // 收藏的图片 ID
    private LocalDateTime collectTime; // 收藏时间，默认为当前时间戳
}
