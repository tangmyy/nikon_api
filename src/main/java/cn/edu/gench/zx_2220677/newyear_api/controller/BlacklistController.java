package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Blacklist;
import cn.edu.gench.zx_2220677.newyear_api.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/blacklist")
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    @PostMapping("/add")
    public ResponseEntity<String> addBlacklist(@RequestBody Blacklist blacklist) {
        boolean isAdded = blacklistService.addUserToBlacklist(blacklist);
        return ResponseEntity.ok(isAdded ? "添加成功" : "添加失败");
    }

    // 查询某个用户是否在黑名单中
    @GetMapping("/check/{userId}")
    public ResponseEntity<Boolean> isUserInBlacklist(@PathVariable Long userId) {
        boolean isInBlacklist = blacklistService.isUserInBlacklist(userId);
        return ResponseEntity.ok(isInBlacklist);
    }


    // 将用户移除出黑名单
    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<Boolean> removeUserFromBlacklist(@PathVariable Long userId) {
        boolean isRemoved = blacklistService.removeUserFromBlacklist(userId);
        return ResponseEntity.ok(isRemoved);
    }

    // 查询某个用户的黑名单详情（包括历史记录）
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Blacklist>> getAllBlacklistRecordsByUserId(@PathVariable Long userId) {
        List<Blacklist> blacklistRecords = blacklistService.getAllBlacklistRecordsByUserId(userId);
        return ResponseEntity.ok(blacklistRecords);
    }

    // 查询指定时间段内的黑名单用户
    @GetMapping("/time-range")
    public ResponseEntity<List<Blacklist>> getBlacklistByTimeRange(
            @RequestParam String startTime,
            @RequestParam String endTime) {
        List<Blacklist> blacklistRecords = blacklistService.getBlacklistByTimeRange(startTime, endTime);
        return ResponseEntity.ok(blacklistRecords);
    }

    // 传入用户ID查询指定用户被拉到黑名单的次数
    @GetMapping("/count/{userId}")
    public ResponseEntity<Integer> countBlacklistRecordsByUserId(@PathVariable Long userId) {
        int count = blacklistService.countBlacklistRecordsByUserId(userId);
        return ResponseEntity.ok(count);
    }
}