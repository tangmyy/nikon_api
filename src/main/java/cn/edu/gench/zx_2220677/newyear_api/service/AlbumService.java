package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface AlbumService extends IService<Album> {
    // 查询所有图片
    List<Album> getAllAlbums();

    // 查询所有图片（根据图片标签）
    List<Album> getAlbumsByTag(String tag);
}