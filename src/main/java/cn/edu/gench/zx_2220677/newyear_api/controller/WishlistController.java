package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Album;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Wishlist;
import cn.edu.gench.zx_2220677.newyear_api.service.AlbumService;
import cn.edu.gench.zx_2220677.newyear_api.service.WishlistService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final HttpSession httpSession;
    private final WishlistService wishlistService;
    private final AlbumService albumService;

    @Autowired
    public WishlistController(
            HttpSession httpSession,
            WishlistService wishlistService,
            AlbumService albumService) {
        this.httpSession = httpSession;
        this.wishlistService = wishlistService;
        this.albumService = albumService;
    }

    /**
     * 查询用户已加入愿望单的图片数据
     */
    @GetMapping("/allimages")
    public ResponseEntity<?> getWishlistImages() {
        try {
            Object userIdObj = httpSession.getAttribute("userId");
            if (userIdObj == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请先登录");
            }
            Long userId = (Long) userIdObj;

            // 默认第1页，每页5条
            Page<Album> page = new Page<>(1, 5);
            IPage<Album> albums = wishlistService.getWishlistImagesPage(userId, page);
            return ResponseEntity.ok(albums);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("查询失败：" + e.getMessage());
        }
    }

    /**
     * 添加图片到愿望单
     *
     * @param session HttpSession 对象，用于获取登录的用户 ID
     * @param imageId 图片 ID
     * @return 响应结果
     */
    @PostMapping("/add/{imageId}")
    public ResponseEntity<String> addToWishlist(
            HttpSession session,
            @PathVariable Long imageId) {
        try {
            log.info("添加图片到愿望单，imageId: {}", imageId);
            // 1. 从 HttpSession 获取用户 ID
            Object userIdObj = session.getAttribute("userId");
            if (userIdObj == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请先登录");
            }
            Long userId = (Long) userIdObj;

            // 2. 校验图片 ID
            if (imageId == null) {
                return ResponseEntity.badRequest().body("图片ID不能为空");
            }

            // 3. 检查是否重复添加
            boolean exists = wishlistService.isWishlistItemExists(userId, imageId);
            if (exists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("该图片已存在于愿望单中");
            }

            // 4. 检查图片是否存在
            Album album = albumService.getById(imageId);
            if (album == null || album.isDeleted()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("图片不存在或已被删除");
            }

            // 5. 添加到愿望单
            Wishlist wishlist = new Wishlist();
            wishlist.setUserId(userId);
            wishlist.setImageId(imageId);
            wishlist.setAddTime(LocalDateTime.now());
            
            boolean isAdded = wishlistService.save(wishlist);
            if (isAdded) {
                return ResponseEntity.ok("图片已成功添加到愿望单");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加图片失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("系统错误：" + e.getMessage());
        }
    }

    /**
     * 从愿望单中批量移除图片
     */
    @DeleteMapping("/batch")
    public ResponseEntity<String> batchDeleteWishlist(
            HttpSession session,
            @RequestBody List<Long> imageIds) {
        try {
            // 1. 获取用户ID
            Object userIdObj = session.getAttribute("userId");
            if (userIdObj == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请先登录");
            }
            Long userId = (Long) userIdObj;

            // 2. 参数校验
            if (imageIds == null || imageIds.isEmpty()) {
                return ResponseEntity.badRequest().body("请选择要删除的图片");
            }

            // 3. 删除操作
            boolean isDeleted = wishlistService.removeImagesFromWishlist(userId, imageIds);
            if (isDeleted) {
                return ResponseEntity.ok("批量删除成功");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("没有找到需要删除的记录");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败：" + e.getMessage());
        }
    }

    /**
     * 需求：
     * 1. 检查图片是否在愿望单中
     * 2. 如果在愿望单中，则可以调用对应的方法将图片移出愿望单
     * 根据ID删除愿望单记录
     */
    @DeleteMapping("/remove/{imageId}")
    public ResponseEntity<String> deleteWishlist(@PathVariable Long imageId) {
        try {
            // 1. 获取用户ID
            Object userIdObj = httpSession.getAttribute("userId");
            if (userIdObj == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("请先登录");
            }
            Long userId = (Long) userIdObj;

            // 2. 检查图片是否在用户的愿望单中
            boolean exists = wishlistService.isWishlistItemExists(userId, imageId);
            if (!exists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("图片不在愿望单中");
            }

            // 3. 删除操作
            boolean removed = wishlistService.removeImagesFromWishlist(userId, Collections.singletonList(imageId));
            if (removed) {
                return ResponseEntity.ok("删除成功");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除失败");
            }
        } catch (Exception e) {
            log.error("删除愿望单图片失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系统错误：" + e.getMessage());
        }
    }

    /**
     * 计算选中图片的总价
     */
    @PostMapping("/selected/totalPrice")
    public ResponseEntity<BigDecimal> calculateTotalPriceOfSelectedImages(
            @RequestParam Long userId,
            @RequestBody List<Long> imageIds) {

        // 1. 参数校验
        if (userId == null || imageIds == null || imageIds.isEmpty()) {
            // 返回错误提示并置总价为 0
            return ResponseEntity.badRequest()
                    .body(BigDecimal.ZERO);
        }

        // 2. 查询选中的图片是否存在于该用户的愿望单
        LambdaQueryWrapper<Wishlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Wishlist::getUserId, userId).in(Wishlist::getImageId, imageIds);
        List<Wishlist> selectedItems = wishlistService.list(queryWrapper);

        // 如果没有匹配记录，总价为 0
        if (selectedItems.isEmpty()) {
            return ResponseEntity.ok(BigDecimal.ZERO);
        }

        // 3. 获取所有选中图片的 ID
        Set<Long> selectedImageIds = selectedItems.stream()
                .map(Wishlist::getImageId)
                .collect(Collectors.toSet());

        // 4. 根据图片 ID 查询图片价格
        QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
        albumQueryWrapper.in("image_id", selectedImageIds);
        List<Album> albums = albumService.list(albumQueryWrapper);

        // 5. 创建一个映射关系，以便快速查找图片价格
        Map<Long, BigDecimal> imagePriceMap = albums.stream()
                .collect(Collectors.toMap(Album::getImageId, Album::getPrice));

        // 6. 计算总价格
        // 确保图片存在
        BigDecimal totalPrice = selectedItems.stream()
                .filter(item -> imagePriceMap.containsKey(item.getImageId()))
                .map(item -> imagePriceMap.get(item.getImageId()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 返回计算结果
        return ResponseEntity.ok(totalPrice);
    }

    /**
     * 计算单张图片的价格
     */
    @GetMapping("/price/{imageId}")
    public ResponseEntity<BigDecimal> getPriceByImageId(@PathVariable Long imageId) {

        // 1. 参数校验
        if (imageId == null || imageId <= 0) {
            return ResponseEntity.badRequest().body(BigDecimal.ZERO);
        }

        // 2. 查询图片是否存在，并获取图片价格
        Album album = albumService.getOne(
                new QueryWrapper<Album>().eq("image_id", imageId)
        );

        // 如果没有找到，返回404
        if (album == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BigDecimal.ZERO);
        }

        // 3. 创建一个映射关系，以便快速查找图片价格
        Map<Long, BigDecimal> imagePriceMap = Collections.singletonMap(imageId, album.getPrice());

        // 4. 计算总价格（对于单张图片而言，总价就是它本身的价格）
        BigDecimal totalPrice = imagePriceMap.containsKey(imageId)
                ? imagePriceMap.get(imageId)
                : BigDecimal.ZERO;

        // 5. 返回计算结果
        return ResponseEntity.ok(totalPrice);
    }

    /**
     * 计算愿望单中所有图片的总价
     */
    /**
     * 计算愿望单中所有图片的总价
     */
    @GetMapping("/totalPrice")
    public ResponseEntity<BigDecimal> calculateTotalPriceOfWishlist(@RequestParam Long userId) {

        // 1. 参数校验，返回错误提示
        if (userId == null || userId <= 0) {
            return ResponseEntity.badRequest().body(BigDecimal.ZERO);
        }

        // 2. 查询用户的愿望单
        LambdaQueryWrapper<Wishlist> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Wishlist::getUserId, userId);
        List<Wishlist> wishlistItems = wishlistService.list(queryWrapper);

        // 如果愿望单为空，总价为 0
        if (wishlistItems.isEmpty()) {
            return ResponseEntity.ok(BigDecimal.ZERO);
        }

        // 3. 获取愿望单中所有图片的 ID
        Set<Long> imageIds = wishlistItems.stream()
                .map(Wishlist::getImageId)
                .collect(Collectors.toSet());

        // 4. 根据图片 ID 查询图片价格
        QueryWrapper<Album> albumQueryWrapper = new QueryWrapper<>();
        albumQueryWrapper.in("image_id", imageIds);
        List<Album> albums = albumService.list(albumQueryWrapper);

        // 5. 创建一个映射关系，以便快速查找图片价格
        Map<Long, BigDecimal> imagePriceMap = albums.stream()
                .collect(Collectors.toMap(Album::getImageId, Album::getPrice));

        // 6. 计算总价格
        BigDecimal totalPrice = wishlistItems.stream()
                .filter(item -> imagePriceMap.containsKey(item.getImageId()))
                .map(item -> imagePriceMap.getOrDefault(item.getImageId(), BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 7. 返回计算结果
        return ResponseEntity.ok(totalPrice);
    }

    /**
     * 1. 添加批量选择/取消选择功能
     */
    @PostMapping("/select")
    public ResponseEntity<String> selectWishlistItems(
            @RequestParam Long userId,
            @RequestBody List<Long> imageIds,
            @RequestParam boolean selected) {
        try {
            // 实现选择/取消选择逻辑
            return ResponseEntity.ok("操作成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("操作失败");
        }
    }

    /**
     * 查询愿望单图片（分页）
     */
    @GetMapping("/page")
    public ResponseEntity<IPage<Album>> getWishlistPage(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "10") int size,
            HttpSession session) {
        try {
            Object userIdObj = session.getAttribute("userId");
            if (userIdObj == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
            Long userId = (Long) userIdObj;
            
            Page<Album> page = new Page<>(current, size);
            IPage<Album> result = wishlistService.getWishlistImagesPage(userId, page);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
