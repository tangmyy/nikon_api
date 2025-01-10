package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AlbumMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {

    @Resource
    private AlbumMapper albumMapper;


    // 上传图片
    @Override
    public Album uploadImage(Album album) {
        // 七牛云传输
        return albumMapper.uploadImage(album);
    }

    // 根据标签查询图片
    @Override
    public List<Album> findImagesByTag(String tag) {
        return albumMapper.findImagesByTag(tag);
    }

    // 查询所有公开的图片
    @Override
    public List<Album> findAllPublicImages() {
        return albumMapper.findAllPublicImages();
    }

    // 查询所有私人的图片
    @Override
    public List<Album> findPrivateImages() {
        return albumMapper.findPrivateImages();
    }

    // 按上传时间（升序）查询图片
    @Override
    public List<Album> findUploadUp() {
        return albumMapper.findUploadUp();
    }

    // 按上传时间（降序）查询图片
    @Override
    public List<Album> findUploadDecline() {
        return albumMapper.findUploadDecline();
    }

    // 按描述模糊查询图片
    @Override
    public List<Album> findByDescription(String description) {
        return albumMapper.findByDescription(description);
    }

    // 根据条件+分页查询图片
    // orderBy 是一个动态参数，用于指定排序顺序
    // "asc" 表示升序，"desc" 表示降序
    @Override
    public IPage<Album> findAlbumsWithConditions(Page<Album> page, String tag, String visibility, String description, String orderBy) {
        QueryWrapper<Album> queryWrapper = new QueryWrapper<>();

        // 添加条件筛选
        if (tag != null && !tag.isEmpty()) {
            queryWrapper.eq("tag", tag);
        }
        if (visibility != null && !visibility.isEmpty()) {
            queryWrapper.eq("visibility", visibility);
        }
        if (description != null && !description.isEmpty()) {
            queryWrapper.like("description", description);
        }
        queryWrapper.eq("is_deleted", false); // 过滤软删除的数据

        // 按上传时间排序
        if ("asc".equalsIgnoreCase(orderBy)) {
            queryWrapper.orderByAsc("uploaded_time");
        } else if ("desc".equalsIgnoreCase(orderBy)) {
            queryWrapper.orderByDesc("uploaded_time");
        }

        // 返回分页结果
        return baseMapper.selectPage(page, queryWrapper);
    }
}
