//package cn.edu.gench.zx_2220677.newyear_api;
//
//import cn.edu.gench.zx_2220677.newyear_api.mapper.BlacklistMapper;
//import cn.edu.gench.zx_2220677.newyear_api.pojo.Blacklist;
//import cn.edu.gench.zx_2220677.newyear_api.service.impl.BlacklistServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class BlacklistServiceTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(BlacklistServiceTest.class);
//
//    @Mock
//    private BlacklistMapper blacklistMapper;
//
//    @InjectMocks
//    private BlacklistServiceImpl blacklistService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testIsUserInBlacklist() {
//        Long userId = 1L;
//        Blacklist mockBlacklist = new Blacklist(userId, "Test Reason", null, null, false, null);
//        when(blacklistMapper.findBlacklistByUserId(userId)).thenReturn(List.of(mockBlacklist));
//
//        boolean result = blacklistService.isUserInBlacklist(userId);
//
//        assertTrue(result);
//        verify(blacklistMapper, times(1)).findBlacklistByUserId(userId);
//        logger.info("testIsUserInBlacklist: User is in blacklist.");
//    }
//
//    @Test
//    public void testRemoveUserFromBlacklist() {
//        Long userId = 1L;
//        when(blacklistMapper.removeUserFromBlacklist(userId)).thenReturn(1);
//
//        boolean result = blacklistService.removeUserFromBlacklist(userId);
//
//        assertTrue(result);
//        verify(blacklistMapper, times(1)).removeUserFromBlacklist(userId);
//        logger.info("testRemoveUserFromBlacklist: User removed from blacklist.");
//    }
//
//    @Test
//    public void testGetAllBlacklistRecordsByUserId() {
//        Long userId = 1L;
//        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//        LocalDateTime startTime = LocalDateTime.parse("2021-01-01T12:00:00", formatter);
//        LocalDateTime endTime = LocalDateTime.parse("2021-12-31T12:00:00", formatter);
//        Blacklist mockBlacklist = new Blacklist(userId, "Test Reason", startTime, endTime, false, startTime);
//        when(blacklistMapper.findAllBlacklistRecordsByUserId(userId)).thenReturn(List.of(mockBlacklist));
//
//        List<Blacklist> result = blacklistService.getAllBlacklistRecordsByUserId(userId);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals("Test Reason", result.get(0).getReason());
//        verify(blacklistMapper, times(1)).findAllBlacklistRecordsByUserId(userId);
//        logger.info("testGetAllBlacklistRecordsByUserId: Retrieved blacklist records for user.");
//    }
//
//    @Test
//    public void testCountBlacklistRecordsByUserId() {
//        Long userId = 1L;
//        when(blacklistMapper.countBlacklistRecordsByUserId(userId)).thenReturn(1);
//
//        int result = blacklistService.countBlacklistRecordsByUserId(userId);
//
//        assertEquals(1, result);
//        verify(blacklistMapper, times(1)).countBlacklistRecordsByUserId(userId);
//        logger.info("testCountBlacklistRecordsByUserId: Counted blacklist records for user.");
//    }
//}