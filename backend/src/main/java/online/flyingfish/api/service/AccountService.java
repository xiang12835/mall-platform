package online.flyingfish.api.service;

import online.flyingfish.api.model.Account;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AccountService {
    Account getAccountById(int user_id);   //查询单个账户信息  //可以查询包括非有效的账户信息

    List<Account> getAccountAll();   //查询所有账户信息  //能查询所有的账户信息（包括有效和无效）

    List<Account> getAccount();   //查询所有账户信息  //只能查询所有有效的账户信息

    void addAccount(Account account);  //添加账户信息

    void addAccountBalance(int user_id, BigDecimal acc_balance);  //账户充值

    void updateAccountById(int user_id, BigDecimal acc_balance);  //修改账户的余额

    void delAccountById(int user_id);  //删除的方式为标志段置1，即acc_status置1

    void addAccountBalanceBatch(int[] user_id, BigDecimal acc_balance);  //账户批量充值

    void updateAccountByIdBatch(int[] user_id, BigDecimal acc_balance);  //账户批量修改

    void delAccountByIdBatch(int[] user_id);  //账户批量删除


}
