package cn.edu.gench.zx_2220677.newyear_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security的密码加密
 *
 */
@Configuration
public class SecurityConfig {
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(14); // 默认强度为10, 自定义强度为 14
   }
}
