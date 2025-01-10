package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlbumMapper extends BaseMapper<Album> {

    // 上传图片
    @Select("<script>" +
            "SELECT * FROM album " +
            "WHERE is_deleted = FALSE " +
            "<if test='album.fileName != null and album.fileName != \"\"'>" +
            "  AND file_name = #{album.fileName} " +
            "</if>" +
            "<if test='album.filePath != null and album.filePath != \"\"'>" +
            "  AND file_path = #{album.filePath} " +
            "</if>" +
            "<if test='album.visibility != null and album.visibility != \"\"'>" +
            "  AND visibility = #{album.visibility} " +
            "</if>" +
            "<if test='album.description != null and album.description != \"\"'>" +
            "  AND description = #{description} " +
            "</if>" +
            "<if test='album.tag != null and album.tag != \"\"'>" +
            "  AND tag = #{album.tag} " +
            "</if>" +
            "<if test='album.price != null'>" +
            "  AND price = #{album.price} " +
            "</if>" +
            "<if test='album.userId != null'>" +
            "  AND user_id = #{album.userId} " +
            "</if>" +
            "</script>")
    Album uploadImage(@Param("album") Album album);

    // 根据标签查询图片
    @Select("SELECT * FROM album WHERE tag = #{tag} AND is_deleted = FALSE")
    List<Album> findImagesByTag(@Param("tag") String tag);

    // 查询所有公开的图片
    @Select("SELECT * FROM album WHERE visibility = 'PUBLIC' AND is_deleted = FALSE")
    List<Album> findAllPublicImages();

    // 查看所有私人的图片
    @Select("SELECT * FROM album WHERE visibility = 'PRIVATE' AND is_deleted = FALSE")
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
