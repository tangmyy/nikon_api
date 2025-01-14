package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;


public interface LikeImageService {

    boolean saveRating(Long userId, Long imageId, Integer value);

    /**
     * 点赞和评分图片
     * @param likeImage 点赞和评分记录实体
     * @return 操作结果，true表示成功，false表示失败
     */
    boolean likeAndScoreImage(LikeImage likeImage);

    /**
     * 检查用户是否点赞过某图片
     * @param userId 用户ID
     * @param imageId 图片ID
     * @return 如果用户点赞过返回true，否则返回false
     */
    boolean checkIfLiked(Long userId, Long imageId);

    /**
     * 统计特定图片的点赞数量（status为LIKED）
     * @param imageId 图片ID
     * @return 点赞数量
     */
    int countLikesForImage(Long imageId);

    /**
     * 更新评分
     * @param likeImage 点赞和评分记录实体
     * @return 操作结果，true表示成功，false表示失败
     */
    boolean updateScore(LikeImage likeImage);
}