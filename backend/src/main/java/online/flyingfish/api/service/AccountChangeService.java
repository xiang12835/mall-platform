package online.flyingfish.api.service;

import online.flyingfish.api.model.AccountChange;

import java.sql.Timestamp;
import java.util.List;

public interface AccountChangeService {
    //获取某个账户的收支明细(可进行条件筛选)
    List<AccountChange> getInfo(int empId, String operateType, Integer operate, Timestamp startTime, Timestamp endTime);

    //增加一条明细记录
    void addChange(AccountChange accountChange);

    //批量增加明细
    void batchAdd(List<AccountChange> accountChanges);

}
