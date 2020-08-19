package online.flyingfish.api.mapper;


import online.flyingfish.api.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(@Param("id") int id);
}
