package online.flyingfish.api.service;

import online.flyingfish.api.model.AccountUser;
import online.flyingfish.api.model.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AccountService {

    List<AccountUser> getAccountList();

    BigDecimal getMonthTotalConsumption(Integer empId);

    BigDecimal getYearTotalConsumption(Integer empId);

    Account getAccount(int empId);

    List<Account> getAccountsByEmpIds(List<Integer> ids);

    void updateAccount(Account account);

    void batchUpdate(List<Account> accounts);
}
