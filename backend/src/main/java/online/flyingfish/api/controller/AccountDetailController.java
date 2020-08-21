package online.flyingfish.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;
import online.flyingfish.api.model.Msg;
import online.flyingfish.api.service.AccountDetailService;
import online.flyingfish.api.service.impl.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api2")
@Api("账户明细信息 Api")
public class AccountDetailController {
    @Autowired
    private AccountDetailService accountDetailService;

    @ApiOperation("单个账户收入明细查询")
    @RequestMapping(value ="/accountDetail/query_accountDetailById_Income", method = RequestMethod.GET)
    @ResponseBody
    public Msg IncomeAccountDetailQryById(int user_id) {
        return ResultUtil.success(accountDetailService.getIncomeAccountDetailById(user_id));
    }

    @ApiOperation("单个账户支出明细查询")
    @RequestMapping(value ="/accountDetail/query_accountDetailById_Expend", method = RequestMethod.GET)
    @ResponseBody
    public Msg ExpendAccountDetailQryById(int user_id) {
        return ResultUtil.success(accountDetailService.getExpendAccountDetailById(user_id));
    }

    @ApiOperation("账户明细查询")
    @RequestMapping(value ="/accountDetail/query_accountDetail", method = RequestMethod.GET)
    @ResponseBody
    public Msg AccountDetailQry() {
        return ResultUtil.success(accountDetailService.getAccountDetail());
    }

    @ApiOperation("添加账户明细记录")
    @RequestMapping(value ="/accountDetail/add_accountDetail", method = RequestMethod.POST)
    public void AccountDetailAdd(AccountDetail accountDetail){
        accountDetailService.addAccountDetail(accountDetail);
    }

    @ApiOperation("账户明细记录删除")
    @RequestMapping(value ="/accountDetail/delete_accountDetail", method = RequestMethod.DELETE)
    public Msg AccountDetailDel(@RequestParam(value="change_id") int change_id){
        if(change_id<=0){
            return ResultUtil.error(1009,"输入账号异常");
        }
        List<AccountDetail> list=accountDetailService.getAccountDetail();
        if(change_id>list.size()){
            return ResultUtil.error(1014,"操作记录异常，记录账号不存在");
        }
        accountDetailService.delAccountDetailById(change_id);
        return ResultUtil.success();
    }

}
