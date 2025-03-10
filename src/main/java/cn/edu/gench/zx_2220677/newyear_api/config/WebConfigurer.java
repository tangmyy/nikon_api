package cn.edu.gench.zx_2220677.newyear_api.config;

import cn.edu.gench.zx_2220677.newyear_api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/test/**");
   }
}

