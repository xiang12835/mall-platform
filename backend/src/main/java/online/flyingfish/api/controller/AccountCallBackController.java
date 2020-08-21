package online.flyingfish.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;
import online.flyingfish.api.model.Msg;
import online.flyingfish.api.service.AccountDetailService;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.impl.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api2")
@Api("账户信息回调 Api")
public class AccountCallBackController {
    @Autowired
    private AccountDetailService accountDetailService;

    @Autowired
    private AccountService accountService;

    @ApiOperation("账户充值回调")
    @RequestMapping(value ="/account/add_balance2", method = RequestMethod.POST)
    public Msg AccountBalanceAdd(int user_id, BigDecimal acc_balance){
        if(user_id<=0){
            return ResultUtil.error(1009,"输入账号异常");
        }
        List<Account> list_a=accountService.getAccountAll();
        if(user_id>list_a.size()){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int acc_status=accountService.getAccountById(user_id).getAcc_status();
        if(acc_status==1){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int res=acc_balance.compareTo(new BigDecimal(0));
        if(res==-1){
            return ResultUtil.error(1011,"充值有误，充值金额不能为负数");
        }
        accountService.addAccountBalance(user_id,acc_balance);
        List<Account> acc_list=accountService.getAccount();
        BigDecimal now_balance=acc_list.get(user_id-1).getAcc_balance();  //这样写表示获得的user_id的下标需要与acc_id一致

        List<AccountDetail> list=accountDetailService.getAccountDetail();
        AccountDetail accountDetail=new AccountDetail();
        accountDetail.setChange_id(list.get(list.size()-1).getChange_id()+1);
        accountDetail.setUser_id(user_id);
        accountDetail.setOperate_type("充值");
        accountDetail.setOperate(0);
        accountDetail.setAmount(acc_balance);
        accountDetail.setBalance(now_balance);
        accountDetail.setOrder_id(null);
        accountDetail.setDetail_create_time(new Timestamp(new Date().getTime()));
        accountDetail.setDetail_create_user_id(2);
        accountDetailService.addAccountDetail(accountDetail);
        return ResultUtil.success();
    }

    @ApiOperation("账户余额修改回调")
    @RequestMapping(value ="/account/update_balance2", method = RequestMethod.PUT)
    public Msg AccountUpdate(int user_id, BigDecimal acc_balance){
        if(user_id<=0){
            return ResultUtil.error(1009,"输入账号异常");
        }
        List<Account> list_a=accountService.getAccountAll();
        if(user_id>list_a.size()){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int acc_status=accountService.getAccountById(user_id).getAcc_status();
        if(acc_status==1){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int res1=acc_balance.compareTo(new BigDecimal(0));
        if(res1==-1){
            return ResultUtil.error(1012,"修改有误，账户余额不能为负数");
        }
        List<Account> acc_list1=accountService.getAccount();
        BigDecimal balance1=acc_list1.get(user_id-1).getAcc_balance();
        accountService.updateAccountById(user_id,acc_balance);
        List<Account> acc_list2=accountService.getAccount();
        BigDecimal balance2=acc_list2.get(user_id-1).getAcc_balance();
        BigDecimal sub=balance2.subtract(balance1);
        int res=sub.compareTo(new BigDecimal("0"));
        int operate;
        if(res==1){
            operate=0;
        }else if(res==-1){
            operate=1;  //金额增加
        }else{
            operate=2;  //金额不变
        }

        List<AccountDetail> list=accountDetailService.getAccountDetail();
        AccountDetail accountDetail=new AccountDetail();
        accountDetail.setChange_id(list.get(list.size()-1).getChange_id()+1);
        accountDetail.setUser_id(user_id);
        accountDetail.setOperate_type("修改");
        accountDetail.setOperate(operate);
        accountDetail.setAmount(sub.abs());
        accountDetail.setBalance(acc_balance);
        accountDetail.setOrder_id(null);
        accountDetail.setDetail_create_time(new Timestamp(new Date().getTime()));
        accountDetail.setDetail_create_user_id(2);
        accountDetailService.addAccountDetail(accountDetail);
        return ResultUtil.success();
    }

    @ApiOperation("账户批量充值回调")
    @RequestMapping(value ="/account/add_balance2_batch", method = RequestMethod.POST)
    public Msg AccountBalanceAddBatch(int[] user_id, BigDecimal acc_balance){
        List<Account> list_a=accountService.getAccountAll();
        for(int i=0;i<user_id.length;i++){
            if(user_id[i]<=0){
                return ResultUtil.error(1009,"输入账号异常");
            }
            if(user_id[i]>list_a.size()){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
            int acc_status=accountService.getAccountById(user_id[i]).getAcc_status();
            if(acc_status==1){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
        }
        int res1=acc_balance.compareTo(new BigDecimal(0));
        if(res1==-1){
            return ResultUtil.error(1011,"充值有误，充值金额不能为负数");
        }
        accountService.addAccountBalanceBatch(user_id,acc_balance);
        List<Account> acc_list=accountService.getAccount();
        List<AccountDetail> list=accountDetailService.getAccountDetail();
        int len=user_id.length;
        for(int i=0;i<len;i++){
            BigDecimal now_balance=acc_list.get(user_id[i]-1).getAcc_balance();  //这样写表示获得的user_id的下标需要与acc_id一致
            AccountDetail accountDetail=new AccountDetail();
            accountDetail.setChange_id(list.get(list.size()-1).getChange_id()+(i+1));
            accountDetail.setUser_id(user_id[i]);
            accountDetail.setOperate_type("充值");
            accountDetail.setOperate(0);
            accountDetail.setAmount(acc_balance);
            accountDetail.setBalance(now_balance);
            accountDetail.setOrder_id(null);
            accountDetail.setDetail_create_time(new Timestamp(new Date().getTime()));
            accountDetail.setDetail_create_user_id(2);
            accountDetailService.addAccountDetail(accountDetail);
        }
        return ResultUtil.success();
    }

    @ApiOperation("账户余额批量修改回调")
    @RequestMapping(value ="/account/update_balance2_batch", method = RequestMethod.PUT)
    public Msg AccountUpdateBatch(int[] user_id, BigDecimal acc_balance){
        List<Account> list_a=accountService.getAccountAll();
        for(int i=0;i<user_id.length;i++){
            if(user_id[i]<=0){
                return ResultUtil.error(1009,"输入账号异常");
            }
            if(user_id[i]>list_a.size()){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
            int acc_status=accountService.getAccountById(user_id[i]).getAcc_status();
            if(acc_status==1){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
        }
        int res1=acc_balance.compareTo(new BigDecimal(0));
        if(res1==-1){
            return ResultUtil.error(1012,"修改有误，账户余额不能为负数");
        }

        List<Account> acc_list1=accountService.getAccount();
        int len=user_id.length;
        BigDecimal[] balance1=new BigDecimal[len];
        for(int i=0;i<len;i++){
            balance1[i]=acc_list1.get(user_id[i]-1).getAcc_balance();
        }
        accountService.updateAccountByIdBatch(user_id,acc_balance);
        List<Account> acc_list2=accountService.getAccount();
        BigDecimal[] balance2=new BigDecimal[len];
        BigDecimal[] sub=new BigDecimal[len];
        for(int j=0;j<len;j++){
            balance2[j]=acc_list2.get(user_id[j]-1).getAcc_balance();
            sub[j]=balance2[j].subtract(balance1[j]);

            int res=sub[j].compareTo(new BigDecimal("0"));
            int operate;
            if(res==1){
                operate=0;
            }else if(res==-1){
                operate=1;  //金额增加
            }else{
                operate=2;  //金额不变
            }

            List<AccountDetail> list=accountDetailService.getAccountDetail();
            AccountDetail accountDetail=new AccountDetail();
            accountDetail.setChange_id(list.get(list.size()-1).getChange_id()+1);
            accountDetail.setUser_id(user_id[j]);
            accountDetail.setOperate_type("修改");
            accountDetail.setOperate(operate);
            accountDetail.setAmount(sub[j].abs());
            accountDetail.setBalance(acc_balance);
            accountDetail.setOrder_id(null);
            accountDetail.setDetail_create_time(new Timestamp(new Date().getTime()));
            accountDetail.setDetail_create_user_id(2);
            accountDetailService.addAccountDetail(accountDetail);
        }
        return ResultUtil.success();
    }
}
