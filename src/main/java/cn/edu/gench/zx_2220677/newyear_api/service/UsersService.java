// src/main/java/com/example/mirror/service/UsersService.java
package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.Users ;

public interface UsersService {
    boolean register(Users user);
    Users login(String username, String password);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    Users getCurrentUser(Long userId);
}
