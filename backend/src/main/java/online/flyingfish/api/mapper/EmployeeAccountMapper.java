package online.flyingfish.api.mapper;

import online.flyingfish.api.model.EmployeeAccountBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface EmployeeAccountMapper {
    @Select({"select emp.user_id,emp.user_name,emp.user_sex,emp.emp_no," +
            "(select comp.comp_name from company comp where emp.comp_id=comp.comp_id)," +
            "(select dept.dept_name from department dept where emp.dept_id=dept.dept_id )," +
            "emp.user_position,emp.user_level,acc.acc_balance from employee emp,acctable acc " +
            "where emp.user_id=acc.user_id order by emp.user_id"})
    List<EmployeeAccountBase> getEmployeeAccountBase();

    @Select({"select sum(accd.amount) from acc_change_detail accd" +
            " where accd.detail_create_time between '2020-01-01 00:00:00' and '2020-12-30 00:00:00'" +
            " and user_id = #{user_id} and accd.operate=1" +
            " group by accd.user_id,to_char(accd.detail_create_time, 'YYYY-MM')" +
            " order by to_char(accd.detail_create_time, 'YYYY-MM') desc"})
    List<BigDecimal> getEmployeeAccountCountMonth(int user_id);

    @Select({"select sum(accd.amount) from acc_change_detail accd " +
            "where  accd.detail_create_time between  '2020-01-01 00:00:00'  and  '2020-12-30 00:00:00' " +
            "and user_id = #{user_id} and accd.operate=1"})
    BigDecimal getEmployeeAccountCountYear(int user_id);

    @Select({"select emp.user_id,emp.user_name,emp.user_sex,emp.emp_no," +
            "(select comp.comp_name from company comp where emp.comp_id=comp.comp_id)," +
            "(select dept.dept_name from department dept where emp.dept_id=dept.dept_id )," +
            "emp.user_position,emp.user_level,acc.acc_balance from employee emp,acctable acc " +
            "where emp.user_id=acc.user_id order by emp.${sortStr} ${sortOpt}"})
    List<EmployeeAccountBase> sortEmployeeAccountBase(@Param("sortStr") String sortStr, @Param("sortOpt")String sortOpt);

}
