package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.AccountMapper;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccountById(int user_id) {
        return accountMapper.getAccountById(user_id);
    }

    @Override
    public List<Account> getAccountAll() {
        return accountMapper.getAccountAll();
    }

    @Override
    public List<Account> getAccount() {
        return accountMapper.getAccount();
    }

    @Override
    public void addAccount(Account account) {
        accountMapper.addAccount(account);
    }

    @Override
    public void addAccountBalance(int user_id, BigDecimal acc_balance) {
        accountMapper.addAccountBalance(acc_balance, user_id);
    }

    @Override
    public void updateAccountById(int user_id, BigDecimal acc_balance) {
        accountMapper.updateAccountById(acc_balance, user_id);
    }

    @Override
    public void delAccountById(int user_id) {
        accountMapper.delAccountById(user_id);
    }

    @Override
    public void addAccountBalanceBatch(int[] user_id, BigDecimal acc_balance) {
        accountMapper.addAccountBalanceBatch(acc_balance, user_id);
    }

    @Override
    public void updateAccountByIdBatch(int[] user_id, BigDecimal acc_balance) {
        accountMapper.updateAccountByIdBatch(acc_balance, user_id);
    }

    @Override
    public void delAccountByIdBatch(int[] user_id) {
        accountMapper.delAccountByIdBatch(user_id);
    }
}
