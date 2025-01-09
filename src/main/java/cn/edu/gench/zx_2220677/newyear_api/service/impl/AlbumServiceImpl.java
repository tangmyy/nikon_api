package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AlbumMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
    // 查询所有图片
    @Override
    public List<Album> getAllAlbums() {
        return baseMapper.selectAllAlbums();
    }

    // 查询所有图片（根据图片标签）
    @Override
    public List<Album> getAlbumsByTag(String tag) {
        return baseMapper.selectAlbumsByTag(tag);
    }

    // 查询所有公开的图片
    @Override
    public List<Album> getAllPublicImages() {
        return baseMapper.findAllPublicImages();
    }

    // 查看所有私人的图片
    @Override
    public List<Album> getPrivateImages() {
        return baseMapper.findPrivateImages();
    }

    // 按图片上传的时间查询（升序）
    @Override
    public List<Album> getAlbumsUploadedAsc() {
        return baseMapper.findUploadUp();
    }

    // 按图片上传的时间查询（降序）
    @Override
    public List<Album> getAlbumsUploadedDesc() {
        return baseMapper.findUploadDecline();
    }

    // 按描述（模糊）查询图片
    @Override
    public List<Album> searchByDescription(String description) {
        return baseMapper.findByDescription(description);
    }
}