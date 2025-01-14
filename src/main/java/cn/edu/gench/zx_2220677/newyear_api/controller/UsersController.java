package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Users;
import cn.edu.gench.zx_2220677.newyear_api.service.UsersService;
import cn.edu.gench.zx_2220677.newyear_api.mapper.UsersMapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @Operation(summary = "获取用户详情", description = "通过会话获取当前登录用户的详情")
    @GetMapping("/info")
    public ResponseEntity<Users> getUserInfo(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            logger.info("用户未登录");
            return ResponseEntity.status(401).build();
        }
        logger.info("获取用户详情: {}", user);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "更新用户信息", description = "允许用户更新自己的信息")
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Users updatedUser, HttpSession session) {

        logger.info("从前端接收到的用户数据: {}", updatedUser);
        Users currentUser = (Users) session.getAttribute("user");
        if (currentUser == null) {
            logger.info("用户未登录，无法更新信息");
            return ResponseEntity.status(401).body("用户未登录");
        }

        // 确保当前用户只能修改自己的信息
        if (!currentUser.getUserId().equals(updatedUser.getUserId())) {
            logger.info("用户尝试更新其他用户的信息，操作被拒绝");
            return ResponseEntity.status(403).body("禁止修改其他用户的信息");
        }
        // 更新用户信息
        boolean updated = usersService.updateUser(updatedUser);
        if (updated) {
            // 更新成功后，同步会话中的用户信息
            session.setAttribute("user", updatedUser);
            logger.info("用户信息更新成功: {}", updatedUser);
            return ResponseEntity.ok("用户信息更新成功");
        } else {
            logger.info("用户信息更新失败: {}", updatedUser);
            return ResponseEntity.status(500).body("用户信息更新失败");
        }
    }


    @Operation(summary = "注册接口", description = "此接口用于用户注册接口")
    // @ApiResponses(value = {
    //       @ApiResponse(responseCode = "200", description = "成功注册", content = @Content(mediaType = "application/json")),
    //       @ApiResponse(responseCode = "500", description = "服务器错误")
    // })
    // @Parameter(description = "/register", required = true, example = "usersService.register(user)")
    // 注册接口
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        boolean success = usersService.register(user);
        if (success) {
            return ResponseEntity.ok("注册成功");
        } else {
            return ResponseEntity.status(500).body("注册失败");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user, HttpSession session, HttpServletResponse response) {
        Users loggedInUser = usersService.login(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            // 将用户信息存储到 session 中
            session.setAttribute("user", loggedInUser);

            // 手动设置新的 Cookie
            Cookie sessionCookie = new Cookie("JSESSIONID", session.getId());
            sessionCookie.setPath("/");
            sessionCookie.setHttpOnly(false);
            sessionCookie.setDomain("localhost"); // 设置为主机名即可
            sessionCookie.setMaxAge(24 * 60 * 60); // 设置Cookie过期时间，单位为秒
            response.addCookie(sessionCookie);

            // 打印刚刚设置的 Cookie 信息到控制台
            System.out.println("Cookie Name: " + sessionCookie.getName());
            System.out.println("Cookie Value: " + sessionCookie.getValue());
            System.out.println("Cookie Path: " + sessionCookie.getPath());
            System.out.println("Cookie HttpOnly: " + sessionCookie.isHttpOnly());

            return ResponseEntity.ok("登录成功");
        } else {
            logger.info("用户 {} 登录失败，用户名或密码错误", user.getUsername());
            return ResponseEntity.status(401).body("登录失败，用户名或密码错误");
        }
    }

    @GetMapping("/session")
    public ResponseEntity<String> getSessionInfo(HttpSession session) {
        // 获取用户信息
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            logger.info("用户未登录");
            return ResponseEntity.status(401).body("用户未登录");
        } else {
            // 输出会话信息到控制台
            logger.info("会话 ID: {}", session.getId());
            logger.info("会话创建时间: {}", session.getCreationTime());
            logger.info("会话最后访问时间: {}", session.getLastAccessedTime());
            logger.info("会话最大不活动间隔时间: {}", session.getMaxInactiveInterval());

            // 输出会话中所有属性的名称
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                logger.info("会话属性: {} = {}", attributeName, session.getAttribute(attributeName));
            }
            logger.info("用户信息: {}", user);
            return ResponseEntity.ok("用户已登录\n会话信息已输出到控制台");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<String> getStatus(HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            return ResponseEntity.ok("用户已登录");
        } else {
            return ResponseEntity.status(401).body("用户未登录");
        }
    }

    @Autowired
    private UsersMapper usersMapper;

    // 查询所有用户：http://localhost:8088/api/users/findAllIpage
    @GetMapping("findAllIpage")
    public IPage findAllIpage(){
        //设置起始值及每页条数
        Page<Users> page = new Page<>(0,2);
        IPage iPage = usersMapper.selectPage(page,null);
        return iPage;
    }

    // 查询所有用户：http://localhost:8088/api/users/findAll
    @GetMapping("/findAll")
    public ResponseEntity<List<Users>> selectList() {
        // 查询所有用户，传入 null 表示没有条件限制
        List<Users> users = usersMapper.selectList(null);
        // 返回查询结果，HTTP 状态码 200 OK
        return ResponseEntity.ok(users);
    }





}
