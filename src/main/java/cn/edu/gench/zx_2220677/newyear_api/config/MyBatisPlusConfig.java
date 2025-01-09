package cn.edu.gench.zx_2220677.newyear_api.config;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

@Configuration
public class MyBatisPlusConfig {
   @Bean
   public MybatisPlusInterceptor paginationInterceptor() {
      MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
      PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);
      interceptor.addInnerInterceptor(paginationInterceptor);
      return interceptor;
   }
}
