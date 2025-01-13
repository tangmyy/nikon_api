package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Wishlist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WishlistMapper extends BaseMapper<Wishlist> {

    // 1. 检查图片是否在用户的愿望单中
    @Select("SELECT * FROM wishlist WHERE user_id = #{userId} AND image_id = #{imageId}")
    @Results({
        @Result(property = "wishlistId", column = "wishlist_id"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "imageId", column = "image_id")
    })
    Wishlist findWishlistByUserIdAndImageId(@Param("userId") Long userId, @Param("imageId") Long imageId);

    // 2. 查询某张图片被添加到愿望单的次数（管理员功能）
    @Select("SELECT COUNT(*) FROM wishlist WHERE image_id = #{imageId}")
    Integer countWishlistByImageId(@Param("imageId")Long imageId);

    // 3. 从愿望单中批量移除图片
    @Delete("<script>DELETE FROM wishlist WHERE user_id = #{userId} AND image_id IN " +
            "<foreach collection='imageIds' item='imageId' open='(' separator=',' close=')'>" +
            "#{imageId}" +
            "</foreach></script>")
    int batchRemoveImagesFromWishlist(@Param("userId") Long userId, @Param("imageIds") List<Long> imageIds);

    // 4. 计算愿望单中所有选中图片的总价
    @Select("SELECT SUM(price) FROM images i INNER JOIN wishlist w ON i.image_id = w.image_id WHERE w.user_id = #{userId}")
    BigDecimal calculateTotalPriceOfWishlist(@Param("userId") Long userId);

    // 5. 计算愿望单中单张图片的价格（传入图片的ID）
    @Select("SELECT price FROM images WHERE image_id = #{imageId}")
    BigDecimal calculatePriceByImageId(@Param("imageId") Long imageId);

    // 6. 选定愿望单中的图片（然后计算总价）
    @Select("<script>" + "SELECT SUM(price) FROM images i " + 
            "INNER JOIN wishlist w ON i.image_id = w.image_id " + 
            "WHERE w.user_id = #{userId} AND w.image_id IN " + 
            "<foreach collection='imageIds' item='imageId' open='(' separator=',' close=')'>" + 
            "#{imageId}" + 
            "</foreach></script>")
    BigDecimal calculateTotalPriceOfSelectedImages(@Param("userId") Long userId, @Param("imageIds") List<Long> imageIds);
}
