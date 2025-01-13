package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface LikeImageMapper extends BaseMapper<LikeImage> {

    /**
     * 检查用户是否点赞过某图片
     * @param userId 用户ID
     * @param imageId 图片ID
     * @return 如果用户点赞过返回true，否则返回false
     */
    @Select("SELECT CASE WHEN EXISTS (SELECT 1 FROM like_image WHERE user_id = #{userId} AND image_id = #{imageId} AND status = 'LIKED') THEN true ELSE false END")
    boolean checkIfLiked(@Param("userId") Long userId, @Param("imageId") Long imageId);

    /**
     * 插入点赞和评分记录，如果已存在则更新状态
     * @param likeImage 点赞和评分记录实体
     * @return 影响行数
     */
    @Insert("INSERT INTO like_image (image_id, user_id, value, image_like_time, status) " +
            "VALUES (#{imageId}, #{userId}, #{value}, NOW(), #{status}) " +
            "ON DUPLICATE KEY UPDATE value = #{value}, status = #{status}, image_like_time = NOW()")
    int insertOrUpdateLikeAndScoreRecord(LikeImage likeImage);

    /**
     * 统计特定图片的点赞数量（status为LIKED）
     * @param imageId 图片ID
     * @return 点赞数量
     */
    @Select("SELECT COUNT(*) FROM like_image WHERE image_id = #{imageId} AND status = 'LIKED'")
    int countLikesForImage(@Param("imageId") Long imageId);

    /**
     * 更新评分
     * @param likeImage 点赞和评分记录实体
     * @return 影响行数
     */
    @Update("UPDATE like_image SET value = #{value} WHERE user_id = #{userId} AND image_id = #{imageId}")
    int updateScore(LikeImage likeImage);
}