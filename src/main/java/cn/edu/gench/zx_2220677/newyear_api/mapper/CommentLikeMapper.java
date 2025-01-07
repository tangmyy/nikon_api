package cn.edu.gench.zx_2220677.newyear_api.mapper;


import cn.edu.gench.springboot.mirror.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CommentLikeMapper extends BaseMapper<Comment> {

    // 插入评论点赞记录
    @Insert("INSERT INTO comment_like (user_id, comment_id) VALUES (#{userId}, #{commentId})")
    @Options(useGeneratedKeys = true, keyProperty = "likeId", keyColumn = "like_id")
    int insertCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    // 删除评论点赞记录（取消点赞）
    @Delete("DELETE FROM comment_like WHERE user_id = #{userId} AND comment_id = #{commentId}")
    int deleteCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    // 查询用户是否已点赞某评论
    @Select("SELECT COUNT(1) FROM comment_like WHERE user_id = #{userId} AND comment_id = #{commentId}")
    boolean existsCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    // 查询某条评论的点赞数
    @Select("SELECT COUNT(*) FROM comment_like WHERE comment_id = #{commentId}")
    int countCommentLikes(@Param("commentId") Long commentId);
}
