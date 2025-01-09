package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    // 添加评论
    boolean addComment(Long parentCommentId, Long imageId, Long userId, String commentText);

    // 删除评论
    boolean deleteComment(Long commentId, Long userId);

    // 根据图片 ID 获取评论列表
    List<Comment> getCommentsByImageId(Long imageId);

    // 根据评论 ID 获取子评论
    List<Comment> getRepliesByCommentId(Long parentCommentId);
}
