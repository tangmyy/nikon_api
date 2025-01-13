package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;
import cn.edu.gench.zx_2220677.newyear_api.service.LikeImageService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//public class LikeImageServiceImpl extends ServiceImpl<LikeImageMapper, LikeImage> implements LikeImageService {
public class LikeImageServiceImpl implements LikeImageService {

    @Autowired
    LikeImageMapper likeImageMapper;


    @Override
    public boolean likeAndScoreImage(LikeImage likeImage) {
        Long userId = likeImage.getUserId();
        Long imageId = likeImage.getImageId();

        // 检查用户是否点赞过图片
        boolean isLiked = checkIfLiked(userId, imageId);
        if (isLiked) {
            // 如果用户点赞过，则更新状态为 UNLIKED
            likeImage.setStatus(LikeImage.Status.UNLIKED);
        } else {
            // 如果用户没有点赞过，则插入新记录，状态为 LIKED
            likeImage.setStatus(LikeImage.Status.LIKED);
        }

        // 插入或更新点赞和评分记录
        int rowsAffected = likeImageMapper.insertOrUpdateLikeAndScoreRecord(likeImage);
        return rowsAffected > 0;
    }


    @Override
    public boolean checkIfLiked(Long userId, Long imageId) {
        return likeImageMapper.checkIfLiked(userId, imageId);
    }

    @Override
    public int countLikesForImage(Long imageId) {
        return likeImageMapper.countLikesForImage(imageId);
    }

    @Override
    public boolean updateScore(LikeImage likeImage) {
        int rowsAffected = likeImageMapper.updateScore(likeImage);
        return rowsAffected > 0;
    }
}