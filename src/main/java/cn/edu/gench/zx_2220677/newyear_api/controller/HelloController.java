package cn.edu.gench.zx_2220677.newyear_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Spring Boot提供了两种注解 @RestController和@Controller
// 来标识Controller负责接受和处理HTTP请求
// @RestController只请求数据会将注解返回的对象数据转换为JSON
// @Controller请求页面和数据


public class HelloController {
    // 本地的应该是http://localhost:8080/hello
    // 获取前端传入的数据使用http://localhost:8080/hello?nickname=zhangxing&phone=614

    // @GetMapping("/hello") 括号内为访问该方法时明确的链接地址
    // 等价于@RequestMapping(value = "/hello",method= RequestMethod.GET)
    // 网站链接例：https://www.xxx.com/s/xx
    //          ：协议//域名/路径

    @GetMapping("/hello")
    public String hello(String nickname, String phone){
        return "超级天才:" + nickname + "编号:" + phone;
    }
}