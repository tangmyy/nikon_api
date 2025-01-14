package cn.edu.gench.zx_2220677.newyear_api;

import cn.edu.gench.zx_2220677.newyear_api.service.impl.BigModelServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.edu.gench.zx_2220677.newyear_api.mapper")
@SpringBootApplication
// @MapperScan("cn.edu.gench.zx_2220677.newyear_api.mapper")
public class NewYearApiApplication implements CommandLineRunner {

   @Autowired
   private BigModelServiceImpl bigModelServiceImpl;

   public static void main(String[] args) {
      SpringApplication.run(NewYearApiApplication.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      bigModelServiceImpl.start();
   }
}
