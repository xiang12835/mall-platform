package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.AccountMapper;
import online.flyingfish.api.mapper.AccountSortMapper;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.AccountSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountSortServiceImpl implements AccountSortService {
    @Autowired
    private AccountSortMapper accountSortMapper;


    @Override
    public List<Account> sortAccount(String sortStr, String sortOpt) {

        return accountSortMapper.sortAccount(sortStr, sortOpt);
    }

    @Override
    public List<AccountDetail> sortAccountDetail(String sortStr, String sortOpt) {

        return accountSortMapper.sortAccountDetail(sortStr, sortOpt);
    }
}
