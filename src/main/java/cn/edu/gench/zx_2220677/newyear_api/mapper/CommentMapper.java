package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    // 插入评论
    @Insert("INSERT INTO comment (parent_comment_id, image_id, user_id, comment_text) VALUES (#{comment.parentCommentId}, #{comment.imageId}, #{comment.userId}, #{comment.commentText})")
    @Options(useGeneratedKeys = true, keyProperty = "comment.commentId", keyColumn = "comment_id")
    int insertComment(@Param("comment") Comment comment);

    // 删除评论（只能由评论作者删除）
    @Delete("DELETE FROM comment WHERE comment_id = #{commentId} AND user_id = #{userId}")
    int deleteComment(@Param("commentId") Long commentId, @Param("userId") Long userId);

    // 根据图片 ID 查询评论列表
    @Select("SELECT * FROM comment WHERE image_id = #{imageId} ORDER BY created_at DESC")
    List<Comment> getCommentsByImageId(@Param("imageId") Long imageId);

    // 查询某评论的子评论
    @Select("SELECT * FROM comment WHERE parent_comment_id = #{parentCommentId} ORDER BY created_at DESC")
    List<Comment> getRepliesByCommentId(@Param("parentCommentId") Long parentCommentId);

    // 检查评论是否存在
    @Select("SELECT COUNT(1) FROM comment WHERE comment_id = #{commentId}")
    boolean existsComment(@Param("commentId") Long commentId);


}

