package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Wishlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WishlistMapper extends BaseMapper<Wishlist> {

    // 1. 查询用户的愿望单
    List<Wishlist> findWishlistByUserId(Long userId);

    // 2. 检查图片是否在用户的愿望单中
    Wishlist findWishlistByUserIdAndImageId(Long userId, Long imageId);

    // 3. 查询某张图片被添加到愿望单的次数（管理员功能）
    Integer countWishlistByImageId(Long imageId);
}
