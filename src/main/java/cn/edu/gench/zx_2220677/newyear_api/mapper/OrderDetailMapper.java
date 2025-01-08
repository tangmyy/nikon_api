package cn.edu.gench.zx_2220677.newyear_api.mapper;


import cn.edu.gench.zx_2220677.newyear_api.pojo.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    // 1. 查询某个订单的所有详情
    List<OrderDetail> findDetailsByOrderId(Long orderId);

    // 2. 查询某个图片出现在的所有订单详情
    List<OrderDetail> findDetailsByPhotoId(Long photoId);

    // 3. 统计某个图片的销售总量
    Integer countTotalSalesByPhotoId(Long photoId);
}