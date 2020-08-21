package online.flyingfish.api.service;

import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;

import java.util.List;

public interface AccountSortService {

    List<Account> sortAccount(String sortStr, String sortOpt);  //可选择字段进行排序，同时可选择升序或者降序排序

    List<AccountDetail> sortAccountDetail(String sortStr, String sortOpt);  //可选择字段进行排序，同时可选择升序或者降序排序
}
