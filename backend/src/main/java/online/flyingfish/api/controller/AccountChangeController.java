package online.flyingfish.api.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountChange;
import online.flyingfish.api.service.AccountChangeService;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.EmployeeService;
import online.flyingfish.common.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/accountChange")
public class AccountChangeController {

    @Autowired
    private AccountChangeService accountChangeService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private AccountService accountService;

    @ApiOperation("分页排序获取单个账户收支明细,可选的条件限制：操作类型，收入/支出，时间范围")
    @RequestMapping(value ="/getOne", method = RequestMethod.GET)
    @ResponseBody
    public Result getOneAccount(@RequestParam(value="empId") Integer empId,
                                @RequestParam(value="timeVary") String timeVary,
                                @RequestParam(value="pageNum") Integer pageNum,
                                @RequestParam(value="pageSize") Integer pageSize,
                                @RequestParam(value="orderBy") String orderBy,//字段名如emp_id
                                @RequestParam(value="sort") String sort,//asc/desc
                                @RequestParam(value="operateType ",  required = false) String operateType,
                                @RequestParam(value="operate",  required = false) Integer operate){


        //timeVary:"2020-04-09 - 2020-04-10" 可以默认六个月
        String[] dates = timeVary.split(" ");
        Timestamp startTime = Timestamp.valueOf(dates[0]+" 00:00:00");
        Timestamp endTime = Timestamp.valueOf(dates[2]+" 00:00:00");

        //分页
        String order = orderBy + " " + sort;
        //String order = "account_id asc";
        Page page = PageHelper.startPage(pageNum, pageSize, order);
        List<AccountChange> list = accountChangeService.getInfo(empId, operateType, operate, startTime, endTime);

        List<Map<String, Object>> accountChangeDTO = new LinkedList<>();
        for(AccountChange accountChange: list) {

            Map<String, Object> map = new HashMap<>();

            map.put("changeId", accountChange.getChangeId());
            map.put("empId", accountChange.getEmpId());
            map.put("operateType", accountChange.getOperateType());

            if(accountChange.getOperate() == 0) map.put("operate", "收入");
            else map.put("operate", "支出");

            map.put("amount", accountChange.getAmount());
            map.put("balance", accountChange.getBalance());
            map.put("createTime", accountChange.getCreateTime().toString());
            map.put("createUser", employeeService.getEmployeeById(accountChange.getCreateUser()).getName());//获取实际操作者的姓名

            accountChangeDTO.add(map);
        }

        PageInfo pageInfo = new PageInfo(accountChangeDTO);
        pageInfo.setTotal(page.getTotal());//总条数
        Map<String, Object> res = new HashMap<>();
        res.put("total", pageInfo.getTotal());
        res.put("list", pageInfo.getList());
        return Result.success(res);
    }

    @ApiOperation("单个账户充值")
    @RequestMapping(value ="/recharge", method = RequestMethod.POST)
    public Result recharge(@RequestParam(value="empId") Integer empId,
                           @RequestParam(value="amount") BigDecimal amount){

        rechargeGenerator(empId,amount);
        return Result.success();
    }

    @ApiOperation("批量账户充值")
    @RequestMapping(value ="/batchRecharge", method = RequestMethod.POST)
    public Result batchRecharge(@RequestParam(value = "empIdString") String empIdString,
                                @RequestParam(value = "amount") BigDecimal amount){

        //获取需要充值的账户列表
        String[] empIds = empIdString.split(",");
        List<Integer> empIdList = new ArrayList<>();
        for(String str:empIds){
            empIdList.add(Integer.parseInt(str));
        }

        //批量获取所给员工账户原来信息，并对余额执行加法操作以获取新余额
        List<Account> getAccounts = accountService.getAccountsByEmpIds(empIdList);
        Map<Integer, BigDecimal> balanceMap = new HashMap<>();
        for(Account account: getAccounts) {
            balanceMap.put(account.getEmpId(), account.getBalance().add(amount));
        }

        //批量更新员工账户信息
        List<Account> accounts = new ArrayList<>();
        for (Map.Entry<Integer, BigDecimal> entry : balanceMap.entrySet()) {
            Integer empId = entry.getKey();
            BigDecimal newBalance = entry.getValue();

            Account account = new Account();
            account.setEmpId(empId);
            account.setBalance(newBalance);
            account.setChangeTime(new Timestamp(System.currentTimeMillis()));
            accounts.add(account);

        }
        accountService.batchUpdate(accounts);

        //批量增加收支明细
        List<AccountChange> accountChanges = new ArrayList<>();
        for (Map.Entry<Integer, BigDecimal> entry : balanceMap.entrySet()) {

            Integer empId = entry.getKey();
            BigDecimal newBalance = entry.getValue();

            AccountChange accountChange = new AccountChange();
            accountChange.setEmpId(empId);
            accountChange.setOperateType("recharge");
            accountChange.setOperate(0);
            accountChange.setAmount(amount);
            accountChange.setBalance(newBalance);
            accountChange.setCreateTime(new Timestamp(System.currentTimeMillis()));
            accountChange.setCreateUser(2);//这里应该是当前登陆用户

            accountChanges.add(accountChange);
        }
        accountChangeService.batchAdd(accountChanges);


//        for(int empId: list) {
//            rechargeGenerator(empId,amount);
//
//        }

        return Result.success();
    }



    private void rechargeGenerator(int empId, BigDecimal amount) {

        //1、增加收支条目
        AccountChange accountChange = new AccountChange();
        accountChange.setEmpId(empId);
        accountChange.setOperateType("recharge");
        accountChange.setOperate(0);
        accountChange.setAmount(amount);

        BigDecimal oldBalance = accountService.getAccount(empId).getBalance();
        BigDecimal newBalance = oldBalance.add(amount);
        accountChange.setBalance(newBalance);

        accountChange.setCreateTime(new Timestamp(System.currentTimeMillis()));
        accountChange.setCreateUser(2);//这里应该是当前登陆用户

        accountChangeService.addChange(accountChange);

        //2、修改对应账户表的余额
        Account account = new Account();
        account.setEmpId(empId);
        account.setBalance(newBalance);
        account.setChangeTime(new Timestamp(System.currentTimeMillis()));

        accountService.updateAccount(account);

    }

}
