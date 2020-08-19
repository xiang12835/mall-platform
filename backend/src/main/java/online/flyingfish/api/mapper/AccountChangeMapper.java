package online.flyingfish.api.mapper;


import online.flyingfish.api.model.AccountChange;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AccountChangeMapper {

    //@Select("select change_id, emp_id, operate_type, operate, amount, balance, order_id, create_time, create_user from account_change where emp_id = #{empId}")
    @Select({"<script> " +
            "select change_id, emp_id, operate_type, operate, amount, balance, order_id, create_time, create_user from account_change " +
            "where  1=1 " +
            "<if test='empId!=null'> and emp_id = #{empId}</if> " +
            "<if test='operateType!=null'> and operate_type = #{operateType}</if> " +
            "<if test='operate!=null'> and operate = #{operate}</if> " +
            "<if test='startTime!=null'> and create_time &gt; #{startTime}</if> " +
            "<if test='endTime!=null'> and create_time &lt; #{endTime}</if> " +
            "order by create_time asc" +
            "</script>"
    })
    List<AccountChange> getInfo(int empId, String operateType, Integer operate, Timestamp startTime, Timestamp endTime);

    @Insert("insert into account_change(emp_id, operate_type, operate, amount, balance, order_id, create_time, create_user) values(#{empId}, #{operateType}, #{operate}, #{amount}, #{balance}, #{orderId}, #{createTime}, #{createUser})")
    void addChange(AccountChange accountChange);

    @Insert({
            "<script>",
            "insert into account_change(emp_id, operate_type, operate, amount, balance, order_id, create_time, create_user) values ",
            "<foreach collection='accountChanges' item='item' index='index' separator=','>",
            "(#{item.empId}, #{item.operateType}, #{item.operate}, #{item.amount}, #{item.balance}, #{item.orderId}, #{item.createTime}, #{item.createUser})",
            "</foreach>",
            "</script>"
    })
    void batchAdd(@Param("accountChanges") List<AccountChange> accountChanges);
}
