package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlbumService extends IService<Album> {

    // 上传图片
    Album uploadImage(Album album);

    // 根据标签查找
    List<Album> findImagesByTag(String tag);

    // 查看所有公开的图片
    List<Album> findAllPublicImages();

    // 查看所有私人的图片
    List<Album> findPrivateImages();

    // 根据上传时间（升序）
    List<Album> findUploadUp();

    // 根据上传时间（降序）
    List<Album> findUploadDecline();

    // 根据图片描述
    List<Album> findByDescription(String description);

    // 分页查询（按照条件筛选）
    // orderBy 是一个动态参数，用于指定排序顺序
    // "asc" 表示升序，"desc" 表示降序
    IPage<Album> findAlbumsWithConditions(Page<Album> page, String tag, String visibility, String description, String orderBy);
}
