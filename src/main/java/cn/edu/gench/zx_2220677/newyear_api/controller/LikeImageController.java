package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;
import cn.edu.gench.zx_2220677.newyear_api.service.LikeImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like-image")
public class LikeImageController {

    @Autowired
    private LikeImageService likeImageService;

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
     * 检查用户是否点赞过某图片
     * @param userId 用户ID
     * @param imageId 图片ID
     * @return 用户是否点赞过
     */
    @GetMapping("/check/{userId}/{imageId}")
    public ResponseEntity<Boolean> checkIfLiked(@PathVariable Long userId, @PathVariable Long imageId) {
        boolean isLiked = likeImageService.checkIfLiked(userId, imageId);
        return ResponseEntity.ok(isLiked);
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