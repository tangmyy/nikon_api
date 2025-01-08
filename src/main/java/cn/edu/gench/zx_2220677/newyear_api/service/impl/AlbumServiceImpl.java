package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.AlbumMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements AlbumService {
@Autowired
  private AlbumMapper albumMapper;


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

    public void setBaseMapper(AlbumMapper albumMapper) {
        this.baseMapper = albumMapper;
    }
}