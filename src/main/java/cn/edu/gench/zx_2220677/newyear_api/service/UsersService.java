// src/main/java/com/example/mirror/service/UsersService.java
package cn.edu.gench.zx_2220677.newyear_api.service;

import cn.edu.gench.zx_2220677.newyear_api.pojo.User ;

public interface UsersService {
    boolean register(User user);
    User login(String username, String password);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
}
