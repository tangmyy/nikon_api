package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;


public interface AlbumService extends IService<Album> {

    // 上传图片
    String uploadImage(MultipartFile file, String isPublic, String description, String tagsJson, BigDecimal price, Long userId) throws IOException;

    List<Album> findAllImages();
    List<Album> FuzzySearch(String keyword);

    List<Album> findImagesByUserId(Long userId);

    String deleteImage(Long imageId);
    String updateImage(Long imageId, String isPublic, String description, String tagsJson, BigDecimal price, Long userId) throws IOException;


}
