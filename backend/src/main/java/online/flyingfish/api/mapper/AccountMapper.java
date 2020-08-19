package online.flyingfish.api.mapper;

import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountUser;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface AccountMapper {

    @Select("select a.account_id, a.emp_id, e.emp_number, e.name, e.sex, e.position, e.level, e.role, a.balance, a.create_time, a.change_time from account a, employee e where a.emp_id = e.id and a.status = 0")
    List<AccountUser> getAccountList();

    @Select("select " +
            "sum(case extract(month from create_time) when extract(month from now()) then amount else 0 end) as monthTotal " +
            "from  account_change " +
            "where emp_id = #{empId} and operate = 1;")
    BigDecimal getMonthTotalConsumption(Integer empId);

    @Select("select " +
            "sum(case extract(year from create_time) when extract(year from now()) then amount else 0 end) as yearTotal\n" +
            "from  account_change " +
            "where emp_id = #{empId} and operate = 1;")
    BigDecimal getYearTotalConsumption(Integer empId);

    @Select("select * from account where emp_id = #{empId}")
    Account getAccount(int empId);

    @Select({
            "<script>" +
                    "select * from account where emp_id in " +
                    "<foreach item = 'item' index = 'index' collection = 'ids' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>"+
                    "order by emp_id asc"+
                    "</script>"})
    List<Account> getByEmpIds(@Param("ids") List<Integer> ids);


    @Update("update account set balance = #{balance}, change_time = #{changeTime} where emp_id = #{empId}")
    void updateAccount(BigDecimal balance, int empId, Timestamp changeTime);

    @Update({"<script>" +
            "<foreach collection = 'accounts' item ='item' index = 'index' separator=';'>" +
            "update account " +
            "set balance = #{item.balance}, change_time = #{item.changeTime} " +
            "where emp_id = #{item.empId} " +
            "</foreach>"+
            "</script>"})
    void batchUpdate(@Param("accounts") List<Account> accounts);

}
