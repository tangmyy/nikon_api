package cn.edu.gench.zx_2220677.newyear_api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.edu.gench.zx_2220677.newyear_api.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UsersMapper extends BaseMapper<User> {

   @Select("SELECT * FROM users")
   List<User> selectAllUserAndOrders();
   //查询所有用户
   @Select("SELECT * FROM users")
   List<User> find();

   @Insert("INSERT INTO users (user_id,username, password, email,registration_time) VALUES (#{user_id},#{username}, #{password}, #{email},#{registration_time})")
   int inster(User user);

   @Delete("DELETE FROM users WHERE user_id = #{user_id}")
   int delete(int id);

   @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email}, registration_time = #{registration_time} WHERE user_id = #{user_id}")
   int updata(User user);
}

