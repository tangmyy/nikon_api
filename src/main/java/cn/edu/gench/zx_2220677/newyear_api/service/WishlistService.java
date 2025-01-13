package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Wishlist;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
import java.util.List;

public interface WishlistService extends IService<Wishlist> {

    // 检查图片是否在愿望单中
    boolean isWishlistItemExists(Long userId, Long imageId);

    // 查询图片被添加到愿望单的次数
    Integer getImageWishlistCount(Long imageId);

    // 批量移除愿望单中的图片
    boolean removeImagesFromWishlist(Long userId, List<Long> imageIds);

    // 计算愿望单中选中图片的总价
    BigDecimal calculateTotalPriceOfSelectedImages(Long userId, List<Long> imageIds);

    // 计算单张图片的价格
    BigDecimal getPriceByImageId(Long imageId);

    // 计算愿望单中所有图片的总价
    BigDecimal calculateTotalPriceOfWishlist(Long userId);

    // 获取愿望单中的图片
    List<Album> getWishlistImages(Long userId);

    // 分页查询愿望单图片
    IPage<Album> getWishlistImagesPage(Long userId, Page<Album> page);
}
