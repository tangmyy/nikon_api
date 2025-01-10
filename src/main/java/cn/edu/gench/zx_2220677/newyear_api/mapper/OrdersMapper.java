package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

    // 1. 查询用户的所有订单
    List<Orders> findOrdersByUserId(Long userId);

    // 2. 查询用户的指定状态订单
    List<Orders> findOrdersByUserIdAndStatus(Long userId, String status);

    // 3. 查询订单详情
    Orders findOrderById(Long orderId);

    // 4. 查询所有订单（管理员功能）
    List<Orders> findAllOrders();

    // 5. 按状态查询订单（管理员功能）
    List<Orders> findOrdersByStatus(String status);

    // 6. 查询指定时间段的订单
    List<Orders> findOrdersByTimeRange(String startTime, String endTime);
}
