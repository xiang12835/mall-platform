package online.flyingfish.api.mapper;

import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface AccountDetailMapper {
    @Select({"select * from acc_change_detail where user_id = #{user_id} and operate=0 order by detail_create_time desc"})
    List<AccountDetail> getIncomeAccountDetailById(int user_id);

    @Select({"select * from acc_change_detail where user_id = #{user_id} and operate=1 order by detail_create_time desc"})
    List<AccountDetail> getExpendAccountDetailById(int user_id);

    @Select({"select * from acc_change_detail order by change_id"})
    List<AccountDetail> getAccountDetail();

    @Insert({"insert into acc_change_detail(change_id, user_id, operate_type, operate, amount, balance, order_id, detail_create_time, detail_create_user_id) values(#{change_id}, #{user_id}, #{operate_type}, #{operate}, #{amount}, #{balance}, #{order_id}, #{detail_create_time}, #{detail_create_user_id})"})
    void addAccountDetail(AccountDetail accountDetail);

    @Delete("delete from acc_change_detail where change_id = #{change_id}")
    void delAccountDetailById(@Param("change_id") int change_id);

}
