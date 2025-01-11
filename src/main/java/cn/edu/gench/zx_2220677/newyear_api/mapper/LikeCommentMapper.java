package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LikeCommentMapper extends BaseMapper<Comment> {

    // 插入评论点赞记录
    int insertCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    // 删除评论点赞记录（取消点赞）
    int deleteCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    // 查询用户是否已点赞某评论
    boolean existsCommentLike(@Param("userId") Long userId, @Param("commentId") Long commentId);

    // 查询某条评论的点赞数
    int countCommentLikes(@Param("commentId") Long commentId);
}
