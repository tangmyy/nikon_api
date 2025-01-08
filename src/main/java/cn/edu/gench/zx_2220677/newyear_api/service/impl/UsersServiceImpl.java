package cn.edu.gench.zx_2220677.newyear_api.service.impl;

import cn.edu.gench.zx_2220677.newyear_api.pojo.User;
import cn.edu.gench.zx_2220677.newyear_api.mapper.UsersMapper;
import cn.edu.gench.zx_2220677.newyear_api.service.UsersService;
import cn.edu.gench.zx_2220677.newyear_api.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersServiceImpl implements UsersService {

    private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean register(User user) {
        if (isUsernameTaken(user.getUsername())) {
            logger.info("用户名 {} 已被占用", user.getUsername());
            throw new IllegalArgumentException("用户名已被占用");
        }
        if (isEmailTaken(user.getEmail())) {
            logger.info("邮箱 {} 已被占用", user.getEmail());
            throw new IllegalArgumentException("邮箱已被占用");
        }
        user.setRegistrationTime(LocalDateTime.now());
        user.setPassword(PasswordUtil.encodePassword(user.getPassword()));
        int result = usersMapper.insert(user);
        if (result > 0) {
            logger.info("用户 {} 注册成功", user.getUsername());
        } else {
            logger.info("用户 {} 注册失败", user.getUsername());
        }
        return result > 0;
    }

    @Override
    public User login(String username, String password) {
        User user = usersMapper.findByUsername(username);
        if (user != null && PasswordUtil.matches(password, user.getPassword())) {
            logger.info("用户 {} 登录成功", username);
            return user;
        }
        logger.info("用户 {} 登录失败", username);
        return null;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        boolean isTaken = usersMapper.findByUsername(username) != null;
        logger.info("用户名 {} 检查结果: {}", username, isTaken ? "已被占用" : "可用");
        return isTaken;
    }

    @Override
    public boolean isEmailTaken(String email) {
        boolean isTaken = usersMapper.findByEmail(email) != null;
        logger.info("邮箱 {} 检查结果: {}", email, isTaken ? "已被占用" : "可用");
        return isTaken;
    }
}
