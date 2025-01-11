package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlbumMapper extends BaseMapper<Album> {


    // 查询所有图片(公开的)
    @Select("SELECT * FROM album WHERE is_public = 'PUBLIC' AND is_deleted = FALSE")
    List<Album> findAllPublicImages();




    // 根据标签查询图片
    @Select("SELECT * FROM album WHERE tags = #{tag} AND is_deleted = FALSE")
    List<Album> findImagesByTag(@Param("tag") String tag);

    // 查看所有私人的图片
    @Select("SELECT * FROM album WHERE is_public = 'PRIVATE' AND is_deleted = FALSE")
    List<Album> findPrivateImages();

    // 按图片上传的时间查询（升序）
    @Select("SELECT * FROM album WHERE is_deleted = FALSE ORDER BY uploaded_time ASC")
    List<Album> findUploadUp();

    // 按图片上传的时间查询（降序）
    @Select("SELECT * FROM album WHERE is_deleted = FALSE ORDER BY uploaded_time DESC")
    List<Album> findUploadDecline();

    // 按描述（模糊）查询图片
    @Select("SELECT * FROM album WHERE description LIKE CONCAT('%', #{description}, '%')")
    List<Album> findByDescription(@Param("description") String description);

}
