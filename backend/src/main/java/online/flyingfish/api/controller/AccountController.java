package online.flyingfish.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.CommonMethod;
import online.flyingfish.api.model.Msg;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.impl.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api2")
@Api("账户信息 Api")
public class AccountController {
    @Autowired
    private AccountService accountService;

//    @ApiOperation("搜索某账户信息")
//    @RequestMapping(value ="/account/search_account", method = RequestMethod.GET)
//    @ResponseBody
//    public Account AccountSearch(int user_id) {
//        return accountService.getAccountSearch(user_id);
//    }

    //只能查询所有有效的账户信息
    @ApiOperation("账户信息查询")
    @RequestMapping(value ="/account/query_account", method = RequestMethod.GET)
    @ResponseBody
    public Msg AccountQry() {
        return ResultUtil.success(accountService.getAccount());
    }

    @ApiOperation("添加账户信息")
    @RequestMapping(value ="/account/add_account", method = RequestMethod.POST)
    public void AccountAdd(Account account){
        accountService.addAccount(account);
    }

    @ApiOperation("账户充值")
    @RequestMapping(value ="/account/add_balance", method = RequestMethod.POST)
    public Msg AccountBalanceAdd(int user_id, BigDecimal acc_balance){
        if(user_id<=0){
            return ResultUtil.error(1009,"输入账号异常");
        }
        List<Account> list=accountService.getAccountAll();
        if(user_id>list.size()){
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
        return ResultUtil.success();
    }

    @ApiOperation("账户余额修改")
    @RequestMapping(value ="/account/update_balance", method = RequestMethod.PUT)
    public Msg AccountUpdate(int user_id, BigDecimal acc_balance){
        if(user_id<=0){
            return ResultUtil.error(1009,"输入账号异常");
        }
        List<Account> list=accountService.getAccountAll();
        if(user_id>list.size()){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int acc_status=accountService.getAccountById(user_id).getAcc_status();
        if(acc_status==1){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int res=acc_balance.compareTo(new BigDecimal(0));
        if(res==-1){
            return ResultUtil.error(1012,"修改有误，账户余额不能为负数");
        }
        accountService.updateAccountById(user_id,acc_balance);
        return ResultUtil.success();
    }

    @ApiOperation("账户删除")
    @RequestMapping(value ="/account/delete_account", method = RequestMethod.DELETE)
    public Msg AccountDel(@RequestParam(value="user_id") int user_id){
        if(user_id<=0){
            return ResultUtil.error(1009,"输入账号异常");
        }
        List<Account> list=accountService.getAccountAll();
        if(user_id>list.size()){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        int acc_status=accountService.getAccountById(user_id).getAcc_status();
        if(acc_status==1){
            return ResultUtil.error(1010,"用户不存在，操作失败");
        }
        accountService.delAccountById(user_id);
        return ResultUtil.success();
    }

    @ApiOperation("账户批量充值")
    @RequestMapping(value ="/account/add_balance_batch", method = RequestMethod.POST)
    public Msg AccountBalanceAddBatch(int[] user_id, BigDecimal acc_balance){
        List<Account> list=accountService.getAccountAll();
        for(int i=0;i<user_id.length;i++){
            if(user_id[i]<=0){
                return ResultUtil.error(1009,"输入账号异常");
            }
            if(user_id[i]>list.size()){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
            int acc_status=accountService.getAccountById(user_id[i]).getAcc_status();
            if(acc_status==1){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
        }
        int res=acc_balance.compareTo(new BigDecimal(0));
        if(res==-1){
            return ResultUtil.error(1011,"充值有误，充值金额不能为负数");
        }
        accountService.addAccountBalanceBatch(user_id,acc_balance);
        return ResultUtil.success();
    }

    @ApiOperation("账户批量修改")
    @RequestMapping(value ="/account/update_balance_batch", method = RequestMethod.PUT)
    public Msg AccountUpdateBatch(int[] user_id, BigDecimal acc_balance){
        List<Account> list=accountService.getAccountAll();
        for(int i=0;i<user_id.length;i++){
            if(user_id[i]<=0){
                return ResultUtil.error(1009,"输入账号异常");
            }
            if(user_id[i]>list.size()){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
            int acc_status=accountService.getAccountById(user_id[i]).getAcc_status();
            if(acc_status==1){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
        }
        int res=acc_balance.compareTo(new BigDecimal(0));
        if(res==-1){
            return ResultUtil.error(1012,"修改有误，账户余额不能为负数");
        }
        accountService.updateAccountByIdBatch(user_id,acc_balance);
        return ResultUtil.success();
    }

    @ApiOperation("账户批量删除")
    @RequestMapping(value ="/account/delete_account_batch", method = RequestMethod.DELETE)
    public Msg AccountDelBatch(@RequestParam(value="user_id") int[] user_id){
        List<Account> list=accountService.getAccountAll();
        for(int i=0;i<user_id.length;i++){
            if(user_id[i]<=0){
                return ResultUtil.error(1009,"输入账号异常");
            }
            if(user_id[i]>list.size()){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
            int acc_status=accountService.getAccountById(user_id[i]).getAcc_status();
            if(acc_status==1){
                return ResultUtil.error(1013,"包含无效用户，操作失败");
            }
        }
        accountService.delAccountByIdBatch(user_id);
        return ResultUtil.success();
    }


}
