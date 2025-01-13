package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.WishlistMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Wishlist;
import cn.edu.gench.zx_2220677.newyear_api.service.WishlistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Service
@Transactional
@Slf4j
public class WishlistServiceImpl extends ServiceImpl<WishlistMapper, Wishlist> implements WishlistService {

    @Autowired
    private WishlistMapper wishlistMapper;

    @Autowired
    private AlbumService albumService;


    // 检查图片是否在用户的愿望单中
    @Cacheable(value = "wishlist", key = "#userId + ':' + #imageId")
    @Override
    public boolean isWishlistItemExists(Long userId, Long imageId) {
        if (userId == null || imageId == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        try {
            Wishlist wishlist = wishlistMapper.findWishlistByUserIdAndImageId(userId, imageId);
            return wishlist != null;
        } catch (Exception e) {
            log.error("查询愿望单失败", e);
            throw new RuntimeException("查询失败");
        }
    }


    // 2. 查询某张图片被添加到愿望单的次数
    @Override
    public Integer getImageWishlistCount(Long imageId) {
        Integer count = wishlistMapper.countWishlistByImageId(imageId);
        return count == null ? 0 : count;
    }

    // 从愿望单中批量移除图片
    @CacheEvict(value = "wishlist", key = "#userId")
    @Override
    public boolean removeImagesFromWishlist(Long userId, List<Long> imageIds) {
        // 建议添加参数校验和异常处理
        if (userId == null || imageIds == null || imageIds.isEmpty()) {
            throw new IllegalArgumentException("参数不能为空");
        }
        try {
            int rowsAffected = wishlistMapper.batchRemoveImagesFromWishlist(userId, imageIds);
            return rowsAffected > 0;
        } catch (Exception e) {
            log.error("批量删除愿望单失败", e);
            throw new RuntimeException("删除失败");
        }
    }

    // 计算愿望单中所有选中图片的总价
    @Override
    public BigDecimal calculateTotalPriceOfSelectedImages(Long userId, List<Long> imageIds) {
        // 调用Mapper计算总价
        BigDecimal totalPrice = wishlistMapper.calculateTotalPriceOfSelectedImages(userId, imageIds);
        // 如果查询结果为空，返回0
        return totalPrice == null ? BigDecimal.ZERO : totalPrice;
    }

    // 计算愿望单中单张图片的价格
    @Override
    public BigDecimal getPriceByImageId(Long imageId) {
        // 调用Mapper查询图片价格
        BigDecimal price = wishlistMapper.calculatePriceByImageId(imageId);
        // 如果查询结果为空，返回0
        return price == null ? BigDecimal.ZERO : price;
    }

    // 选定愿望单中的图片
    @Override
    public BigDecimal calculateTotalPriceOfWishlist(Long userId) {
        // 调用Mapper计算愿望单总价
        BigDecimal totalPrice = wishlistMapper.calculateTotalPriceOfWishlist(userId);
        // 如果查询结果为空，返回0
        return totalPrice == null ? BigDecimal.ZERO : totalPrice;
    }

    @Cacheable(value = "wishlist", key = "#userId")
    @Override
    public List<Album> getWishlistImages(Long userId) {
        // 1. 获取用户的愿望单列表
        LambdaQueryWrapper<Wishlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Wishlist::getUserId, userId);
        List<Wishlist> wishlists = this.list(queryWrapper);
        
        // 2. 获取所有图片ID
        List<Long> imageIds = wishlists.stream()
                .map(Wishlist::getImageId)
                .collect(Collectors.toList());
                
        // 3. 查询并返回图片信息
        if (!imageIds.isEmpty()) {
            LambdaQueryWrapper<Album> albumWrapper = new LambdaQueryWrapper<>();
            albumWrapper.in(Album::getImageId, imageIds);
            return albumService.list(albumWrapper);
        }
        return Collections.emptyList();
    }

    @Override
    public IPage<Album> getWishlistImagesPage(Long userId, Page<Album> page) {
        // 1. 获取用户的愿望单列表
        LambdaQueryWrapper<Wishlist> wishlistQuery = new LambdaQueryWrapper<>();
        wishlistQuery.eq(Wishlist::getUserId, userId);
        List<Wishlist> wishlists = this.list(wishlistQuery);
        
        // 2. 获取所有图片ID
        List<Long> imageIds = wishlists.stream()
                .map(Wishlist::getImageId)
                .collect(Collectors.toList());
                
        // 3. 如果没有图片，返回空分页
        if (imageIds.isEmpty()) {
            return new Page<Album>().setRecords(Collections.emptyList());
        }
        
        // 4. 构建图片查询条件
        LambdaQueryWrapper<Album> albumQuery = new LambdaQueryWrapper<>();
        albumQuery.in(Album::getImageId, imageIds);
        
        // 5. 执行分页查询
        return albumService.page(page, albumQuery);
    }
}
