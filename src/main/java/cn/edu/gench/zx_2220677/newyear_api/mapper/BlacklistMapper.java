package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Blacklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlacklistMapper extends BaseMapper<Blacklist> {

    // 1. 查询所有黑名单用户
    List<Blacklist> findAllActiveBlacklist();

    // 2. 查询某个用户是否在黑名单中
    Blacklist findBlacklistByUserId(Long userId);

    // 3. 查询某个用户的黑名单详情（包括历史记录）
    List<Blacklist> findAllBlacklistRecordsByUserId(Long userId);

    // 4. 查询指定时间段内的黑名单用户
    List<Blacklist> findBlacklistByTimeRange(String startTime, String endTime);

    // 5. 分页查询黑名单用户列表
    List<Blacklist> findBlacklistWithPagination(int offset, int limit);
}