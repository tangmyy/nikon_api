package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Users;
import cn.edu.gench.zx_2220677.newyear_api.service.LikeImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import javax.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/likeimages")
public class LikeImageController {

    @Autowired
    private LikeImageService likeImageService;

    private static final Logger logger = LoggerFactory.getLogger(LikeImageController.class);



    @Operation(summary = "提交评分", description = "用户为图片提交评分")
    @PostMapping("/value")
    public ResponseEntity<String> submitRating(@RequestBody Map<String, Object> payload, HttpSession session) {

        Long imageId = Long.valueOf(payload.get("imageId").toString());
        Integer value = Integer.valueOf(payload.get("value").toString());

        // 获取当前用户
        Users currentUser = (Users) session.getAttribute("user");

        // 输出三个关键参数的值
        logger.info("当前用户ID: {}", currentUser.getUserId());
        logger.info("图片ID: {}", imageId);
        logger.info("评分值: {}", value);

        if (currentUser == null) {
            logger.info("用户未登录，无法提交评分");
            return ResponseEntity.status(401).body("用户未登录");
        }

        // 调用服务类保存评分
        try {
            boolean success = likeImageService.saveRating(currentUser.getUserId(), imageId, value);
            if (success) {
                logger.info("用户 {} 为图片 {} 提交评分 {}", currentUser.getUsername(), imageId, value);
                return ResponseEntity.ok("评分提交成功");
            } else {
                logger.error("评分提交失败，数据库操作错误");
                return ResponseEntity.status(500).body("评分提交失败");
            }
        } catch (Exception e) {
            logger.error("评分提交失败：{}", e.getMessage(), e);
            return ResponseEntity.status(500).body("服务器内部错误");
        }
    }





    // 用户是否点赞过某图片
    @GetMapping("/isLiked/{userId}/{imageId}")
    public ResponseEntity<Boolean> checkIfLiked(@PathVariable Long userId, @PathVariable Long imageId) {
        boolean isLiked = likeImageService.checkIfLiked(userId, imageId);
        return ResponseEntity.ok(isLiked);
    }


    /**
     * 用户点赞和评分图片
     * @param likeImage 点赞和评分记录实体
     * @return 操作结果
     */
    @PostMapping("/like-score")
    public ResponseEntity<String> likeAndScoreImage(@RequestBody LikeImage likeImage) {
        boolean isSuccess = likeImageService.likeAndScoreImage(likeImage);
        return isSuccess ? ResponseEntity.ok("点赞和评分成功") : ResponseEntity.badRequest().body("点赞和评分失败");
    }

    /**
     * 统计特定图片的点赞数量（status为LIKED）
     * @param imageId 图片ID
     * @return 点赞数量
     */
    @GetMapping("/count/{imageId}")
    public ResponseEntity<Integer> countLikesForImage(@PathVariable Long imageId) {
        int likesCount = likeImageService.countLikesForImage(imageId);
        return ResponseEntity.ok(likesCount);
    }

    /**
     * 更新评分
     * @param likeImage 点赞和评分记录实体
     * @return 操作结果
     */
    @PutMapping("/update-score")
    public ResponseEntity<String> updateScore(@RequestBody LikeImage likeImage) {
        boolean isSuccess = likeImageService.updateScore(likeImage);
        return isSuccess ? ResponseEntity.ok("评分更新成功") : ResponseEntity.badRequest().body("评分更新失败");
    }
}