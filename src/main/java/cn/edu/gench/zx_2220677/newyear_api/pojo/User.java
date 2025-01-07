package cn.edu.gench.zx_2220677.newyear_api.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;


//@TableName("mingming") 为mybatis-plus 中的 BaesMapper<~~~>提供于类不同的表名若类名和表名相同则不需要注解
@TableName("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId;          // 用户 ID
    private String username;      // 用户名
    private String password;      // 密码
    private String phone;         // 手机号
    private String email;         // 邮箱
    private BigDecimal balance;   // 点数余额
    private String status;        // 用户状态（"ACTIVE" 或 "BANNED"）
    private LocalDateTime registrationTime; // 注册时间



}
