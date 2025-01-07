package cn.edu.gench.zx_2220677.newyear_api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavorite {

    private Long favoriteId; // 收藏记录 ID
    private Long userId; // 用户 ID
    private Long imageId; // 收藏的图片 ID
    private LocalDateTime createdAt; // 收藏时间，默认为当前时间戳
}
