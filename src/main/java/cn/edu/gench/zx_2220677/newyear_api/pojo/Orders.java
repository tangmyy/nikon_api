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
public class Orders {

    @TableId(type = IdType.AUTO)

    private Long orderId; // 订单 ID
    private Long userId; // 买家用户 ID
    private BigDecimal totalAmount; // 订单总额
    private String orderStatus; // 订单状态，默认为 "PENDING"
    private LocalDateTime updateTIme; // 更新时间，默认为当前时间戳，并在更新时自
    private LocalDateTime createdTime; // 创建时间，默认为当前时间戳

}
