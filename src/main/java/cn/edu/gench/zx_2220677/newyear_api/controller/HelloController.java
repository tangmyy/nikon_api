package cn.edu.gench.zx_2220677.newyear_api.controller;

import org.springframework.web.bind.annotation.*;

import cn.edu.gench.zx_2220677.newyear_api.pojo.User;


@RestController
public class HelloController {
    // 网站链接例：https://www.xxx.com/s/xx
    //          ：协议//域名/路径

    // 本地的应该是http://localhost:8080/hello
    // 获取前端传入的数据使用http://localhost:8080/hello?nickname=zhangxing&phone=614

    // @GetMapping("/hello")
    // @RequestMapping(value = "/hello",method= RequestMethod.GET)
    // @PostMapping("/postTest1")
    // @RequestMapping(value = "/postTest1",method = RequestMethod.POST)

    @GetMapping("/hello")
    public String hello(String nickname, String phone){
        return "张行是天才:" + nickname + "编号:" + phone;
    }

    @GetMapping("/hello2")
    // @RequestParam 将nickname映射到name
    public String hello2(@RequestParam(value = "nickname", required = false) String name, String phone){
        return "超级天才:" + name + "编号:" + phone;
    }

    @RequestMapping(value = "/postTest1",method = RequestMethod.POST)
    public String postTest1(){
        return "POST请求1";
    }

    @RequestMapping(value = "/postTest2",method = RequestMethod.POST)
    public String postTest2(String username,String password){
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        return "POST请求2";
    }

    @RequestMapping(value = "/postTest3",method = RequestMethod.POST)
    public String postTest3(User user){
        System.out.println(user);
        return "POST请求3";
    }

    // postTest1-3都是Raw格式 postTest4是json格式
    @RequestMapping(value = "/postTest4",method = RequestMethod.POST)
    public String postTest4(@RequestBody User user){
        System.out.println(user);
        return "POST请求4";
    }

    // /*为一级 /**为任意
    @GetMapping("/test/*")
    public String test(){
        return "通配符请求";
    }

}