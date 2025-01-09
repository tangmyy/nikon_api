package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.UserFavorite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserFavoriteMapper extends BaseMapper<UserFavorite> {

    // 查询用户收藏的所有图片
    List<Long> findImageIdsByUserId(Long userId);

    // 查询用户是否收藏某张图片
    UserFavorite findFavoriteByUserIdAndImageId(Long userId, Long imageId);

    // 查询某张图片被收藏的总次数
    Long countFavoritesByImageId(Long imageId);

    // 查询某张图片被哪些用户收藏（管理员功能）
    List<Long> findUserIdsByImageId(Long imageId);
}
