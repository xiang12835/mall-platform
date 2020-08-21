package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.AccountDetailMapper;
import online.flyingfish.api.model.AccountDetail;
import online.flyingfish.api.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {
    @Autowired
    private AccountDetailMapper accountDetailMapper;

    @Override
    public List<AccountDetail> getIncomeAccountDetailById(int user_id) {
        return accountDetailMapper.getIncomeAccountDetailById(user_id);
    }

    @Override
    public List<AccountDetail> getExpendAccountDetailById(int user_id) {
        return accountDetailMapper.getExpendAccountDetailById(user_id);
    }

    @Override
    public List<AccountDetail> getAccountDetail() {
        return accountDetailMapper.getAccountDetail();
    }

    @Override
    public void addAccountDetail(AccountDetail accountDetail) {
        accountDetailMapper.addAccountDetail(accountDetail);
    }

    @Override
    public void delAccountDetailById(int change_id) {
        accountDetailMapper.delAccountDetailById(change_id);
    }

}
