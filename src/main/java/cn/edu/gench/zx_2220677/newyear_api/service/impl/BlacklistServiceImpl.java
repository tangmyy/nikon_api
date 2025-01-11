package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.mapper.BlacklistMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Blacklist;
import cn.edu.gench.zx_2220677.newyear_api.service.BlacklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlacklistServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements BlacklistService {

    @Autowired
    private BlacklistMapper blacklistMapper;

    @Override
    public boolean isUserInBlacklist(Long userId) {
        List<Blacklist> blacklist = blacklistMapper.findBlacklistByUserId(userId);
        return blacklist != null && !blacklist.isEmpty();
    }

    @Override
    public boolean removeUserFromBlacklist(Long userId) {
        return blacklistMapper.removeUserFromBlacklist(userId) > 0;
    }

    @Override
    public List<Blacklist> getAllBlacklistRecordsByUserId(Long userId) {
        return blacklistMapper.findAllBlacklistRecordsByUserId(userId);
    }

    @Override
    public List<Blacklist> getBlacklistByTimeRange(String startTime, String endTime) {
        return blacklistMapper.findBlacklistByTimeRange(startTime, endTime);
    }

    @Override
    public int countBlacklistRecordsByUserId(Long userId) {
        return blacklistMapper.countBlacklistRecordsByUserId(userId);
    }

    @Override
    public boolean addUserToBlacklist(Blacklist blacklist) {
        // 判断是否存在
        blacklist.setDeleted(false);
         blacklist.setStartTime(LocalDateTime.now());
         blacklist.setEndTime(null);

        return save(blacklist);
    }
}