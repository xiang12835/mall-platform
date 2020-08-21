package online.flyingfish.api.service;

import online.flyingfish.api.model.AccountDetail;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDetailService {
    List<AccountDetail> getIncomeAccountDetailById(int user_id);  //获取单个账户收入明细信息

    List<AccountDetail> getExpendAccountDetailById(int user_id);  //获取单个账户支出明细信息

    List<AccountDetail> getAccountDetail();  //获取账户明细信息

    void addAccountDetail(AccountDetail accountDetail);  //添加账户明细信息

    void delAccountDetailById(int change_id);  //删除的方式为直接删除
}
