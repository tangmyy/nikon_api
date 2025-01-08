package cn.edu.gench.zx_2220677.newyear_api.controller;

import cn.edu.gench.zx_2220677.newyear_api.pojo.User;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
public class UserController {

    @Operation(summary = "根据ID获取用户信息", description = "此接口用于根据ID获取用户信息")
    @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "成功返回基本信息", content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "服务器错误")
    })
    @Parameter(description = "/user/{id}", required = true, example = "getUserById()")
    // 获取基本信息的接口
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable int id){
        System.out.println(id);
        return "根据ID获取用户信息";
    }

    @PostMapping("/user")
    public String save(User user) { return "添加用户"; }

    @PutMapping("/user")
    public String update(User user) { return "更新用户"; }

    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable int id){
        System.out.println(id);
        return "根据ID删除用户";
    }
}
