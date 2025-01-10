package cn.edu.gench.zx_2220677.newyear_api.mapper;


import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeImageMapper extends BaseMapper<LikeImage> {

    // 查询某张图片的所有点赞记录
    List<LikeImage> findLikesByImageId(Long imageId);

    // 查询用户对某张图片的点赞记录
    LikeImage findLikeByUserIdAndImageId(Long userId, Long imageId);

    // 查询某张图片的点赞总数
    Long countLikesByImageId(Long imageId);

    // 查询用户自己点赞过的所有图片
    List<Long> findImageIdsByUserId(Long userId);

    // 插入点赞记录
    int insertLike(Long imageId, Long userId, Long value);

    // 删除点赞记录（取消点赞）
    int deleteLike(Long userId, Long imageId);
}