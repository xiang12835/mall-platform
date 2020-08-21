package online.flyingfish.api.mapper;

import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountSortMapper {

    @Select("select * from acctable where acc_status = 0 order by ${sortStr} ${sortOpt}")
    List<Account> sortAccount(@Param("sortStr")String sortStr, @Param("sortOpt")String sortOpt);

    @Select("select * from acc_change_detail order by ${sortStr} ${sortOpt}")
    List<AccountDetail> sortAccountDetail(@Param("sortStr")String sortStr, @Param("sortOpt")String sortOpt);

}
