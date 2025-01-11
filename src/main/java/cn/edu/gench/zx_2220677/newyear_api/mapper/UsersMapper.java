package cn.edu.gench.zx_2220677.newyear_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper extends BaseMapper<Users> {

   @Select("SELECT * FROM users WHERE username = #{username}")
   Users findByUsername(String username);

   @Select("SELECT * FROM users WHERE email = #{email}")
   Users findByEmail(String email);
}

