package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface AlbumMapper extends BaseMapper<Album> {
    // 查询所有图片
    @Select("SELECT * FROM album")
    List<Album> selectAllAlbums();

    // 查询所有图片（根据图片标签）
    @Select("SELECT * FROM album WHERE tag = #{tag}")
    List<Album> selectAlbumsByTag(String tag);
}