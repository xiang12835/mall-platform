package online.flyingfish.api.mapper;

import java.util.List;

import online.flyingfish.api.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert({"insert into usertable(id, name, email) values(#{id}, #{name}, #{email})"})
    void addUser(User user);

    @Delete("delete from usertable where id=#{id}")
    void delUserById(@Param("id") int id);

    @Update("update usertable set name = #{name}, email = #{email} where id = #{id}")
    void updateUserById(@Param("name") String name, @Param("email") String email, @Param("id") int id);

    @Select("select * from usertable")
    List<User> getUser();
}
