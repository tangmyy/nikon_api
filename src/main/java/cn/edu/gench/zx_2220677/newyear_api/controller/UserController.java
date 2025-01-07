// package cn.edu.gench.zx_2220677.newyear_api.controller;
//
// import cn.edu.gench.zx_2220677.newyear_api.pojo.User;
// import cn.edu.gench.zx_2220677.newyear_api.mapper.UserMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
//
// import java.util.List;
//
// @CrossOrigin
// @RestController
// public class UserController {
//
//     @Autowired
//     private UserMapper userMapper;
//
//     @GetMapping("/user")
//     public String query(){
//         List<User> list = userMapper.selectList(null);
//         System.out.println(list);
//        return "查询用户";
//     }
//     @GetMapping("/user/Get")
//     public List<User> findAll(){
//         return userMapper.selectAllUserAndOrders();
//     }
//
//     @PostMapping ("/user")
//     public String save(User user){
//
//         int i = userMapper.insert(user);
//         System.out.println(i);
//         if (i > 0){
//             return "插入成功";
//         }else{
//             return "插入失败";
//         }
//     }
//
//     @DeleteMapping("/user")
//     public String delete(int id){
//         int i = userMapper.deleteById(id);
//         System.out.println(i);
//         if(i > 0){
//             return "删除成功";
//         }else {
//             return "删除失败";
//         }
//     }
//
//     @PutMapping("/user")
//     public String update(User user){
//         int i = userMapper.updateById(user);
//         System.out.println(i);
//         if(i > 0){
//             return "更新成功";
//         }else {
//             return "更新失败";
//         }
//     }
//
// }
