package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AlbumMapper extends BaseMapper<Album> {

    //继承CURD的方法

    // 查询所有图片
    List<Album> findAllImages();

    // 根据标签查询图片
    List<Album> findImagesByTag(String tag);

    // 查询所有公开的图片
    List<Album> findAllPublicImages();
}
