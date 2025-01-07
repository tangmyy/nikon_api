package cn.edu.gench.zx_2220677.newyear_api.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
   Kaptcha
   图形验证码
 */

@Configuration  // 配置类声明注解
public class KaptchaConfig {

   @Bean
   public DefaultKaptcha kaptchaProducer() {

      Properties properties = new Properties();

      properties.put("kaptcha.image.width", "150");  // 设置生成验证码图像的宽度
      properties.put("kaptcha.image.height", "50");  // 设置生成验证码图像的高度
      properties.put("kaptcha.textproducer.font.size", "40");  // 设置验证码文本的字体大小
      properties.put("kaptcha.textproducer.char.length", "5");  // 设置验证码文本的字符长度（这里是5个字符）
      properties.put("kaptcha.textproducer.font.color", "black");  // 设置验证码文本的颜色
      properties.put("kaptcha.textproducer.char.space", "5");  // 设置验证码字符之间的间距

      // 使用上述属性来创建 Config 配置对象
      Config config = new Config(properties);

      // 创建 DefaultKaptcha 对象，并设置其配置
      DefaultKaptcha captcha = new DefaultKaptcha();
      captcha.setConfig(config);  // 将配置应用到 Kaptcha 生成器

      return captcha;
   }
}

