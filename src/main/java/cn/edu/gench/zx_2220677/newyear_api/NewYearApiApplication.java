package cn.edu.gench.zx_2220677.newyear_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.edu.gench.zx_2220677.newyear_api.mapper")
@SpringBootApplication
public class NewYearApiApplication {

   public static void main(String[] args) {
      SpringApplication.run(NewYearApiApplication.class, args);
   }

}
