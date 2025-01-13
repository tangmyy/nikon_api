package cn.edu.gench.zx_2220677.newyear_api.mapper;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Blacklist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface BlacklistMapper extends BaseMapper<Blacklist> {
    // 2. 查询某个用户是否在黑名单中
    @Select("SELECT * FROM blacklist WHERE user_id = #{userId} AND is_deleted = FALSE AND " +
            "(end_time IS NULL OR end_time >= CURRENT_TIMESTAMP)")
    List<Blacklist> findBlacklistByUserId(@Param("userId") Long userId);

    // 将用户移除出黑名单
    @Update("UPDATE blacklist SET is_deleted = TRUE WHERE user_id = #{userId} AND is_deleted = FALSE")
    int removeUserFromBlacklist(@Param("userId") Long userId);

    // 3. 查询某个用户的黑名单详情（包括历史记录）
    @Select("SELECT * FROM blacklist WHERE user_id = #{userId}")
    List<Blacklist> findAllBlacklistRecordsByUserId(@Param("userId") Long userId);

    // 4. 查询指定时间段内的黑名单用户
    @Select("SELECT * FROM blacklist WHERE is_deleted = FALSE AND " +
            "start_time >= #{startTime} AND (end_time IS NULL OR end_time <= #{endTime})")
    List<Blacklist> findBlacklistByTimeRange(@Param("startTime") String startTime,
                                             @Param("endTime") String endTime);

    // 6. 传入用户ID查询指定用户被拉到黑名单的次数
    @Select("SELECT COUNT(*) FROM blacklist WHERE user_id = #{userId}")
    int countBlacklistRecordsByUserId(@Param("userId") Long userId);
}