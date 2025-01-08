package cn.edu.gench.zx_2220677.newyear_api;

import cn.edu.gench.zx_2220677.newyear_api.mapper.CommentMapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Comment;
import cn.edu.gench.zx_2220677.newyear_api.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentMapper commentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService.setBaseMapper(commentMapper); // 手动注入 baseMapper
    }


    @Test
    public void testAddComment_Success() {
        // 模拟传入参数
        Long parentCommentId = null;
        Long imageId = 1L;
        Long userId = 1L;
        String commentText = "This is a test comment";

        // 模拟返回的Comment对象ID
        Long generatedId = 10L;

        // 使用Answer接口来模拟insertComment行为
        when(commentMapper.insertComment(any(Comment.class))).thenAnswer(invocation -> {
            Comment comment = invocation.getArgument(0);
            // 设置模拟生成的ID
            comment.setCommentId(generatedId);
            return 1; // 返回插入成功标志
        });

        // 调用服务方法
        boolean result = commentService.addComment(parentCommentId, imageId, userId, commentText);

        // 验证结果
        assertTrue(result, "The comment should be added successfully");

        // 验证commentMapper是否调用了一次
        verify(commentMapper, times(1)).insertComment(any(Comment.class));
    }

//    @Test
//    public void testAddComment_Success() {
//        Long parentCommentId = null;
//        Long imageId = 1L;
//        Long userId = 1L;
//        String commentText = "This is a test comment";
//
//        when(commentMapper.insertComment(any(Comment.class))).thenReturn(1);
//
//        boolean result = commentService.addComment(parentCommentId, imageId, userId, commentText);
//
//        assertTrue(result, "The comment should be added successfully");
//        verify(commentMapper, times(1)).insertComment(any(Comment.class));
//    }

    // 测试删除评论失败的情况
    @Test
    public void testAddComment_Failure() {
        Long parentCommentId = null;
        Long imageId = 1L;
        Long userId = 1L;
        String commentText = "This is a test comment";

        when(commentMapper.insertComment(any(Comment.class))).thenReturn(0);

        boolean result = commentService.addComment(parentCommentId, imageId, userId, commentText);

        assertFalse(result, "The comment should not be added");
        verify(commentMapper, times(1)).insertComment(any(Comment.class));
    }

    // 测试删除评论成功
    @Test
    public void testDeleteComment_Success() {
        Long commentId = 1L;
        Long userId = 1L;

        when(commentMapper.deleteComment(commentId, userId)).thenReturn(1);

        boolean result = commentService.deleteComment(commentId, userId);

        assertTrue(result, "The comment should be deleted successfully");
        verify(commentMapper, times(1)).deleteComment(commentId, userId);
    }


    // 测试删除评论失败
    @Test
    public void testDeleteComment_Failure() {
        Long commentId = 1L;
        Long userId = 1L;

        when(commentMapper.deleteComment(commentId, userId)).thenReturn(0);

        boolean result = commentService.deleteComment(commentId, userId);

        assertFalse(result, "The comment should not be deleted");
        verify(commentMapper, times(1)).deleteComment(commentId, userId);
    }

    // 测试获取评论列表
    @Test
    public void testGetCommentsByImageId() {
        Long imageId = 1L;

        List<Comment> mockComments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setCommentId(1L);
        comment.setImageId(imageId);
        comment.setCommentText("Test comment");
        mockComments.add(comment);

        when(commentMapper.getCommentsByImageId(imageId)).thenReturn(mockComments);

        List<Comment> result = commentService.getCommentsByImageId(imageId);

        assertNotNull(result, "The comments list should not be null");
        assertEquals(1, result.size(), "The comments list size should match");
        assertEquals("Test comment", result.get(0).getCommentText(), "The comment text should match");
        verify(commentMapper, times(1)).getCommentsByImageId(imageId);
    }


    // 测试获取回复列表
    @Test
    public void testGetRepliesByCommentId() {
        Long parentCommentId = 1L;

        List<Comment> mockReplies = new ArrayList<>();
        Comment reply = new Comment();
        reply.setCommentId(2L);
        reply.setParentCommentId(parentCommentId);
        reply.setCommentText("Test reply");
        mockReplies.add(reply);

        when(commentMapper.getRepliesByCommentId(parentCommentId)).thenReturn(mockReplies);

        List<Comment> result = commentService.getRepliesByCommentId(parentCommentId);

        assertNotNull(result, "The replies list should not be null");
        assertEquals(1, result.size(), "The replies list size should match");
        assertEquals("Test reply", result.get(0).getCommentText(), "The reply text should match");
        verify(commentMapper, times(1)).getRepliesByCommentId(parentCommentId);
    }
}
