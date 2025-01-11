package cn.edu.gench.zx_2220677.newyear_api.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @TableId(type = IdType.AUTO)

    private Long detailId; // 订单详情 ID
    private Long orderId; // 逻辑关联的订单 ID
    private Long photoId; // 图片 ID
    private BigDecimal price; // 图片单价
    private int quantity; // 图片购买数量，默认为 1
    private LocalDateTime createdTime; // 记录创建时间，默认为当前时间戳
}
