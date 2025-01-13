package cn.edu.gench.zx_2220677.newyear_api.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.management.relation.Role;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// mian!

//@TableName("users") 为mybatis-plus 中的 BaesMapper<~~~>提供于类不同的表名若类名和表名相同则不需要注解

// @TableId(type = IdType.ASSIGN_ID)
// @TableId(type = IdType.ASSIGN_UUID)
// 分布式 ID（雪花算法）：MyBatis Plus 自动生成全局唯一的主键，适用于分布式系统。
// UUID：MyBatis Plus 自动生成 UUID 作为主键。
// @TableField

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    public static Object Status;
    @TableId(type = IdType.AUTO)

    private Long userId;          // 用户 ID
    private String username;      // 用户名
    private String password;      // 密码
    private String phone;         // 手机号
    private String email;         // 邮箱
    private BigDecimal balance;   // 点数余额
    private Role role;                      // 用户角色：USER普通用户，ADMIN管理员
    private String status;                  // 用户状态（"ACTIVE" 或 "BANNED"）
    private boolean isDeleted;              // 是否已删除，默认为 false
    private LocalDateTime registrationTime; // 注册时间

    // 用户角色枚举
    public enum Role {
        USER,
        ADMIN
    }

    // 判断管理员身份方法
    public boolean isAdmin() {
        return "ADMIN".equals(this.role.toString());
    }

    // 判断用户状态是否为激活状态
    public boolean isActive() {
        return "ACTIVE".equals(this.status);
    }

    // 判断用户是否已经删除
    public boolean isDeleted() {
        return this.isDeleted;
    }

    //用户状态枚举
    public enum Status {
        ACTIVE,
        BANNED
    }
}
