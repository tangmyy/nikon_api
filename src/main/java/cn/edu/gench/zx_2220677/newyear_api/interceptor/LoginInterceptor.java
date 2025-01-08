package cn.edu.gench.zx_2220677.newyear_api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
   /**
    * 在请求处理之前进行调用（Controller方法调用之前）
    */
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      // if (true) {
      //    System.out.println("通过");
      //    return true;
      // }else{
      //    System.out.println("不通过");
      //    return false;
      // }
      System.out.println("LoginInterceptor");
      return true;
   }
}