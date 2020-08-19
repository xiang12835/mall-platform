package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.AccountChangeMapper;
import online.flyingfish.api.model.AccountChange;
import online.flyingfish.api.service.AccountChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AccountChangeServiceImpl implements AccountChangeService {

    @Autowired
    private AccountChangeMapper accountChangeMapper;

    @Override
    public List<AccountChange> getInfo(int empId, String operateType, Integer operate, Timestamp startTime, Timestamp endTime) {
        return accountChangeMapper.getInfo(empId, operateType, operate, startTime, endTime);
    }

    @Override
    public void addChange(AccountChange accountChange) {
        accountChangeMapper.addChange(accountChange);
    }

    @Override
    public void batchAdd(List<AccountChange> accountChanges) {
        accountChangeMapper.batchAdd(accountChanges);
    }
}
