package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {

    // 查询用户收藏的所有图片
    List<Long> findImageIdsByUserId(Long userId);

    // 查询用户是否收藏某张图片
    Collect findFavoriteByUserIdAndImageId(Long userId, Long imageId);

}
