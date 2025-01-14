package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AlbumMapper extends BaseMapper<Album> {


    // 查询所有图片(公开的)
    @Select("SELECT * FROM album WHERE is_public = 'PUBLIC' AND is_deleted = FALSE")
    List<Album> findAllPublicImages();

    // 模糊搜索
    @Select("SELECT * FROM album WHERE description LIKE CONCAT('%', #{keyword}, '%') OR tags LIKE CONCAT('%', #{keyword}, '%')")
    List<Album> findByKeyword(@Param("keyword") String keyword);

    // 查看用户私人的图片
    @Select("SELECT * FROM album WHERE is_deleted = FALSE AND user_id = #{userId}")
    List<Album> selectImagesByUserId(@Param("userId") Long userId);

    // 更新
    @Update("UPDATE album SET is_public = #{isPublic}, description = #{description}, tags = #{tags}, price = #{price} WHERE image_id = #{imageId}")
    int updateImage(@Param("imageId") Long imageId, @Param("isPublic") String isPublic, @Param("description") String description, @Param("tags") String tags, @Param("price") BigDecimal price);

    // 删除
    @Update("UPDATE album SET is_deleted = TRUE WHERE image_id = #{imageId}")
    int deleteById(@Param("imageId") Long imageId);

    // 按描述（模糊）查询图片
    @Select("SELECT * FROM album WHERE description LIKE CONCAT('%', #{description}, '%')")
    List<Album> findByDescription(@Param("description") String description);

}
