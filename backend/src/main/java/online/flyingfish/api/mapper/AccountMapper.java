package online.flyingfish.api.mapper;

import online.flyingfish.api.model.Account;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Mapper
public interface AccountMapper {

    @Select({"select * from acctable where user_id = #{user_id}"})
    Account getAccountById(@Param("user_id")int user_id);

    @Select({"select * from acctable order by acc_id"})
    List<Account> getAccountAll();

    @Select({"select * from acctable where acc_status=0 order by acc_id"})
    List<Account> getAccount();

    @Insert({"insert into acctable(acc_id, user_id, acc_balance, acc_status, acc_create_time, acc_change_time) values(#{acc_id}, #{user_id}, #{acc_balance}, #{acc_status}, #{acc_create_time}, #{acc_change_time})"})
    void addAccount(Account account);

    @Update("update acctable set acc_balance = (acc_balance + #{acc_balance}) where user_id = #{user_id} and acc_status=0")
    void addAccountBalance(@Param("acc_balance") BigDecimal acc_balance, @Param("user_id") int user_id);

    @Update("update acctable set acc_balance = #{acc_balance} where user_id = #{user_id} and acc_status=0")
    void updateAccountById(@Param("acc_balance") BigDecimal acc_balance, @Param("user_id") int user_id);


    @Delete("update acctable set acc_status=1 where user_id = #{user_id}")
    void delAccountById(@Param("user_id") int user_id);

    @Select("<script>" + "update acctable set acc_balance = (acc_balance + #{acc_balance}) " +
            "where acc_status=0 and acc_id in" +
            " <foreach collection='user_id' open='(' item='id' separator=',' close=')'> #{id} </foreach> "+
            " </script>" )
    void addAccountBalanceBatch(@Param("acc_balance") BigDecimal acc_balance, @Param("user_id") int[] user_id);

    @Update("<script>" + "update acctable set acc_balance = #{acc_balance} where acc_status=0 and acc_id in"+
            " <foreach collection='user_id' open='(' item='id' separator=',' close=')'> #{id} </foreach> "+
            " </script>" )
    void updateAccountByIdBatch(@Param("acc_balance") BigDecimal acc_balance, @Param("user_id") int[] user_id);

    @Delete("<script>" + "update acctable set acc_status=1 where acc_status=0 and user_id in"+
            " <foreach collection='user_id' open='(' item='id' separator=',' close=')'> #{id} </foreach> "+
            " </script>")
    void delAccountByIdBatch(@Param("user_id") int[] user_id);
}
