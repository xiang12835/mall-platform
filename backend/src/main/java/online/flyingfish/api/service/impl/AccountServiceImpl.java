package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.AccountMapper;
import online.flyingfish.api.model.AccountUser;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public List<AccountUser> getAccountList() {
        return accountMapper.getAccountList();
    }

    @Override
    public BigDecimal getMonthTotalConsumption(Integer empId) {
        return accountMapper.getMonthTotalConsumption(empId);
    }

    @Override
    public BigDecimal getYearTotalConsumption(Integer empId) {
        return accountMapper.getYearTotalConsumption(empId);
    }

    @Override
    public Account getAccount(int empId){
        return accountMapper.getAccount(empId);
    }

    @Override
    public List<Account> getAccountsByEmpIds(List<Integer> ids) {
        return accountMapper.getByEmpIds(ids);
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account.getBalance(),account.getEmpId(), account.getChangeTime());
    }

    @Override
    public void batchUpdate(List<Account> accounts) {
        accountMapper.batchUpdate(accounts);
    }
}
