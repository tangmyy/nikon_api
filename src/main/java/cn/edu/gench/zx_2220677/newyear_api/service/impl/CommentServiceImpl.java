package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.CommentMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Comment;
import cn.edu.gench.zx_2220677.newyear_api.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void setBaseMapper(CommentMapper commentMapper) {
        this.baseMapper = commentMapper;
    }

    // 添加评论
    @Transactional
    @Override
    public boolean addComment(Long parentCommentId, Long imageId, Long userId, String commentText) {
        // 封装参数到 Comment 对象
        Comment comment = new Comment();
        comment.setParentCommentId(parentCommentId);
        comment.setImageId(imageId);
        comment.setUserId(userId);
        comment.setCommentText(commentText);

        // 调用 Mapper 方法
        int rows = baseMapper.insertComment(comment);

        // 确保主键已成功生成
        System.out.println("Generated Comment ID: " + comment.getCommentId());

        return rows > 0;
    }

    // 删除评论
    @Transactional
    @Override
    public boolean deleteComment(Long commentId, Long userId) {
        int rows = baseMapper.deleteComment(commentId, userId);
        return rows > 0;
    }

    // 根据图片 ID 获取评论列表
    @Override
    public List<Comment> getCommentsByImageId(Long imageId) {
        return baseMapper.getCommentsByImageId(imageId);
    }

    // 根据评论 ID 获取子评论
    @Override
    public List<Comment> getRepliesByCommentId(Long parentCommentId) {
        return baseMapper.getRepliesByCommentId(parentCommentId);
    }


}
