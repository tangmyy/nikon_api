package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AlbumService extends IService<Album> {
    // 查询所有图片
    List<Album> getAllAlbums();

    // 查询所有图片（根据图片标签）
    List<Album> getAlbumsByTag(String tag);

    // 查询所有公开的图片
    List<Album> getAllPublicImages();

    // 查看所有私人的图片
    List<Album> getPrivateImages();

    // 按图片上传的时间查询（升序）
    List<Album> getAlbumsUploadedAsc();

    // 按图片上传的时间查询（降序）
    List<Album> getAlbumsUploadedDesc();

    // 按描述（模糊）查询图片
    List<Album> searchByDescription(String description);
}