package cn.edu.gench.zx_2220677.newyear_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper extends BaseMapper<User> {

   @Select("SELECT * FROM users WHERE userId = #{userId}")
   User findByUserId(Long id);

   @Select("SELECT * FROM users WHERE username = #{username}")
   User findByUsername(String username);

   @Select("SELECT * FROM users WHERE email = #{email}")
   User findByEmail(String email);
}

