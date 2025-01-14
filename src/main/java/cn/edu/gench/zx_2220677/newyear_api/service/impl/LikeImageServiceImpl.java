package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.LikeImageMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.LikeImage;
import cn.edu.gench.zx_2220677.newyear_api.service.LikeImageService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
//public class LikeImageServiceImpl extends ServiceImpl<LikeImageMapper, LikeImage> implements LikeImageService {
public class LikeImageServiceImpl implements LikeImageService {

    @Autowired
    LikeImageMapper likeImageMapper;

    @Override
    public boolean saveRating(Long userId, Long imageId, Integer value) {
        try {
            // 查询是否已存在评分记录
            LikeImage existingRecord = likeImageMapper.findByUserIdAndImageId(userId, imageId);
            if (existingRecord != null) {
                // 如果存在记录，则更新评分
                existingRecord.setValue(value);
                int rowsAffected = likeImageMapper.updateScore(existingRecord);
                return rowsAffected > 0;
            } else {
                // 如果不存在记录，则插入新记录
                LikeImage newRecord = new LikeImage();
                newRecord.setUserId(userId);
                newRecord.setImageId(imageId);
                newRecord.setValue(value);
                newRecord.setStatus(LikeImage.Status.LIKED); // 假设初始状态为 LIKED
                newRecord.setImageLikeTime(LocalDateTime.now());
                int rowsInserted = likeImageMapper.insert(newRecord);
                return rowsInserted > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }




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