package online.flyingfish.api.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.AccountUser;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.EmployeeService;
import online.flyingfish.common.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/account")
@Api("账户api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("分页排序查询账户")
    @RequestMapping(value ="/list", method = RequestMethod.GET)
    public Result getAccountList(@RequestParam(value="pageNum") Integer pageNum,
                                 @RequestParam(value="pageSize") Integer pageSize,
                                 @RequestParam(value="orderBy") String orderBy,//字段名如emp_id
                                 @RequestParam(value="sort") String sort){//asc/desc

        //分页
        String order = orderBy + " " + sort;
        //String order = "account_id asc";
        Page page = PageHelper.startPage(pageNum, pageSize, order);
        List<AccountUser> accountUsers = accountService.getAccountList();

        List<Map<String, Object>> accountUsersDTO = new ArrayList<>();
        for(AccountUser accountUser: accountUsers) {
            Map<String, Object> map = new HashMap<>();

            map.put("accountId", accountUser.getAccountId());
            map.put("empNumber", accountUser.getEmpNumber());
            map.put("empName", accountUser.getName());

            if(accountUser.getSex() == 0){
                map.put("sex","男");
            }else{
                map.put("sex","女");
            }

            map.put("position", accountUser.getPosition());
            map.put("level", accountUser.getLevel());
            if(accountUser.getRole() == 1) {
                map.put("role", "综合管理部");
            } else if (accountUser.getRole() == 2){
                map.put("role", "普通员工");
            }

            map.put("balance", accountUser.getBalance());
            map.put("createTime", accountUser.getCreateTime().toString());
            map.put("changeTime", accountUser.getChangeTime().toString());

            if(accountService.getMonthTotalConsumption(accountUser.getEmpId()) != null) {
                map.put("monthTotalConsumption", accountService.getMonthTotalConsumption(accountUser.getEmpId()));
            } else {
                map.put("monthTotalConsumption", 0);
            }

            if(accountService.getYearTotalConsumption(accountUser.getEmpId()) != null) {
                map.put("yearTotalConsumption", accountService.getYearTotalConsumption(accountUser.getEmpId()));
            } else {
                map.put("yearTotalConsumption", 0);
            }

            accountUsersDTO.add(map);
        }

        PageInfo pageInfo = new PageInfo(accountUsersDTO);
        pageInfo.setTotal(page.getTotal());//总条数
        Map<String, Object> res = new HashMap<>();
        res.put("total", pageInfo.getTotal());
        res.put("list", pageInfo.getList());
        return Result.success(res);
    }

    @ApiOperation("获取单个账户余额")
    @ApiImplicitParam(name = "empId", value = "员工编号", required = true, dataType = "Integer")
    @RequestMapping(value ="/getOne", method = RequestMethod.GET)
    public Result getOneAccount(@RequestParam(value="empId") Integer empId){
        BigDecimal balance = accountService.getAccount(empId).getBalance();
        return Result.success(balance);
    }

    @ApiOperation("根据多个员工id获取账户信息")
    @ApiImplicitParam(name = "empIds", value = "员工编号", required = true, dataType = "String")
    @RequestMapping(value ="/getByEmpIds", method = RequestMethod.GET)
    public Result getAccountsByEmpIds(@RequestParam(value="empIdString") String empIdString){

        //获取需要查询的账户列表
        String[] empIds = empIdString.split(",");
        List<Integer> empIdList = new ArrayList<>();
        for(String str:empIds){
            empIdList.add(Integer.parseInt(str));
        }

        List<Account> accounts = accountService.getAccountsByEmpIds(empIdList);
        return Result.success(accounts);
    }

    @ApiOperation("修改单个账户信息")
    @RequestMapping(value ="/update", method = RequestMethod.PUT)
    public Result updateAccount(@RequestParam(value="empId") Integer empId,
                                @RequestParam(value="balance") BigDecimal balance){
        Account account = new Account();
        account.setEmpId(empId);
        account.setBalance(balance);
        account.setChangeTime(new Timestamp(System.currentTimeMillis()));
        accountService.updateAccount(account);
        return Result.success();
    }

    @ApiOperation("批量修改账户信息")
    @RequestMapping(value ="/batchUpdate", method = RequestMethod.PUT)
    public Result batchUpdateAccount(@RequestParam(value="empIdString") String empIdString,
                                     @RequestParam(value="balance") BigDecimal balance){
        //获取需要修改的账户列表
        String[] empIds = empIdString.split(",");
        List<Integer> empIdList = new ArrayList<>();
        for(String str:empIds){
            empIdList.add(Integer.parseInt(str));
        }

        List<Account> accounts = new ArrayList<>();
        for(Integer empId: empIdList) {
            Account account = new Account();
            account.setEmpId(empId);
            account.setBalance(balance);
            account.setChangeTime(new Timestamp(System.currentTimeMillis()));
            accounts.add(account);
        }

        accountService.batchUpdate(accounts);
        return Result.success();
    }
}
