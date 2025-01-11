package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Blacklist;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface BlacklistService extends IService<Blacklist> {
    // 查询某个用户是否在黑名单中
    boolean isUserInBlacklist(Long userId);

    // 将用户移除出黑名单
    boolean removeUserFromBlacklist(Long userId);

    // 查询某个用户的黑名单详情（包括历史记录）
    List<Blacklist> getAllBlacklistRecordsByUserId(Long userId);

    // 查询指定时间段内的黑名单用户
    List<Blacklist> getBlacklistByTimeRange(String startTime, String endTime);

    // 传入用户ID查询指定用户被拉到黑名单的次数
    int countBlacklistRecordsByUserId(Long userId);

    // 添加黑名单用户
    boolean addUserToBlacklist(Blacklist blacklist);
}