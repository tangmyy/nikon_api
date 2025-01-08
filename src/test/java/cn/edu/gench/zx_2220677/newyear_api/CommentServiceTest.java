package cn.edu.gench.zx_2220677.newyear_api;

import cn.edu.gench.zx_2220677.newyear_api.mapper.CommentMapper;
import cn.edu.gench.zx_2220677.newyear_api.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentServiceImpl commentService;

    @Mock
    private CommentMapper commentMapper;

    @Test
    public void testAddComment() {
        // 准备测试数据
        Long parentCommentId = (Long) 1L;
        Long imageId = (Long) 101L;
        Long userId = (Long) 1001L;
        String commentText = "Test Comment";

        // 模拟 commentMapper 的 insertComment 方法返回 1
        when(commentMapper.insertComment(parentCommentId, imageId, userId, commentText)).thenReturn(Integer.valueOf(1));

        // 调用测试方法
        boolean result = commentService.addComment(parentCommentId, imageId, userId, commentText);

        // 验证结果
        assertTrue(result);

        // 验证 Mock 方法是否被调用
        verify(commentMapper, times(1)).insertComment(parentCommentId, imageId, userId, commentText);
    }

   }

