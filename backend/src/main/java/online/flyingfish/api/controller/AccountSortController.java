package online.flyingfish.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.PageObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.mapper.AccountMapper;
import online.flyingfish.api.model.*;
import online.flyingfish.api.service.AccountDetailService;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.AccountSortService;
import online.flyingfish.api.service.EmployeeAccountService;
import online.flyingfish.api.service.impl.ResultUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api2")
@Api("账户信息排序 Api")
public class AccountSortController {
    @Autowired
    private AccountSortService accountSortService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountDetailService accountDetailService;
    @Autowired
    private EmployeeAccountService employeeAccountService;
    @Autowired
    private EmployeeAccountController employeeAccountController;

    @ApiOperation("账户排序")
    @RequestMapping(value ="/account/sort_account", method = RequestMethod.GET)
    public Msg AccountSort(String sortStr, String sortOpt){
        if(!(sortStr.equals("acc_id") || sortStr.equals("user_id") || sortStr.equals("acc_balance")
                || sortStr.equals("acc_create_time")
                || sortStr.equals("acc_change_time") || sortStr.length()==0)){
            return  ResultUtil.error(1015,"输入字段异常，该字段非数据库表字段");
        }else if(!(sortOpt.equals("asc") || sortOpt.equals("desc") || sortOpt.length()==0)){
            return  ResultUtil.error(1016,"输入字段异常，该字段非数据库表排序关键字");
        }else if(sortStr.length()==0){
            return ResultUtil.success(accountService.getAccount());
        }else{
            return ResultUtil.success(accountSortService.sortAccount(sortStr, sortOpt));
        }
    }

    @ApiOperation("账户明细排序")
    @RequestMapping(value ="/account/sort_accountDetail", method = RequestMethod.GET)
    public Msg AccountDetailSort(String sortStr, String sortOpt){
        if(!(sortStr.equals("change_id") || sortStr.equals("user_id") || sortStr.equals("operate_type")
                || sortStr.equals("operate") || sortStr.equals("amount") || sortStr.equals("balance")
                || sortStr.equals("order_id") || sortStr.equals("detail_create_time")
                || sortStr.equals("detail_create_user_id") || sortStr.length()==0)){
            return  ResultUtil.error(1015,"输入字段异常，该字段非数据库表字段");
        }else if(!(sortOpt.equals("asc") || sortOpt.equals("desc") || sortOpt.length()==0)){
            return  ResultUtil.error(1016,"输入字段异常，该字段非数据库表排序关键字");
        }else if(sortStr.length()==0){
            return ResultUtil.success(accountDetailService.getAccountDetail());
        }else{
            return ResultUtil.success(accountSortService.sortAccountDetail(sortStr, sortOpt));
        }
    }

    @ApiOperation("账户信息分页排序")
    @GetMapping("/account/sort_account_page")
    public Msg AccountList(String sortStr, String sortOpt, String pageNum) {
        CommonMethod c1=new CommonMethod();
        boolean flag=c1.isInt(pageNum);
        if(flag==true){
            PageHelper.startPage(Integer.valueOf(pageNum),10);
        }else{
            PageHelper.startPage(1,10);
        }
        List<Account> account;
        if(!(sortStr.equals("acc_id") || sortStr.equals("user_id") || sortStr.equals("acc_balance")
                || sortStr.equals("acc_create_time")
                || sortStr.equals("acc_change_time") || sortStr.length()==0)){
            return  ResultUtil.error(1015,"输入字段异常，该字段非数据库表字段");
        }else if(!(sortOpt.equals("asc") || sortOpt.equals("desc") || sortOpt.length()==0)){
            return  ResultUtil.error(1016,"输入字段异常，该字段非数据库表排序关键字");
        }else if(sortStr.length()==0){
            account=accountService.getAccount();
        }else{
            account=accountSortService.sortAccount(sortStr, sortOpt);
        }

        PageInfo<Account> pageInfo = new PageInfo<Account>(account);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation("账户明细信息分页排序")
    @GetMapping("/account/sort_accountDetail_page")
    public Msg AccountDetailList(String sortStr, String sortOpt, String pageNum) {
        CommonMethod c2=new CommonMethod();
        boolean flag=c2.isInt(pageNum);
        if(flag==true){
            PageHelper.startPage(Integer.valueOf(pageNum),10);
        }else{
            PageHelper.startPage(1,10);
        }
        List<AccountDetail> accountDetail;
        if(!(sortStr.equals("change_id") || sortStr.equals("user_id") || sortStr.equals("operate_type")
                || sortStr.equals("operate") || sortStr.equals("amount") || sortStr.equals("balance")
                || sortStr.equals("order_id") || sortStr.equals("detail_create_time")
                || sortStr.equals("detail_create_user_id") || sortStr.length()==0)){
            return  ResultUtil.error(1015,"输入字段异常，该字段非数据库表字段");
        }else if(!(sortOpt.equals("asc") || sortOpt.equals("desc") || sortOpt.length()==0)){
            return  ResultUtil.error(1016,"输入字段异常，该字段非数据库表排序关键字");
        }else if(sortStr.length()==0){
            accountDetail=accountDetailService.getAccountDetail();
        }else{
            accountDetail=accountSortService.sortAccountDetail(sortStr, sortOpt);
        }
        PageInfo<AccountDetail> pageInfo = new PageInfo<AccountDetail>(accountDetail);
        return ResultUtil.success(pageInfo);
    }

    @ApiOperation("员工账户基础信息分页排序")
    @RequestMapping(value ="/account/sort_query_employeeAccount_Base_page", method = RequestMethod.GET)
    public Msg EmployeeAccountBaseQryList(String sortStr, String sortOpt, String pageNum){
        CommonMethod c2=new CommonMethod();
        boolean flag=c2.isInt(pageNum);
        if(flag==true){
            PageHelper.startPage(Integer.valueOf(pageNum),10);
        }else{
            PageHelper.startPage(1,10);
        }
        List<EmployeeAccountBase> employeeAccountBase;
        if(!(sortStr.equals("user_id") || sortStr.equals("emp_no")
                || sortStr.equals("comp_id") || sortStr.equals("dept_id") || sortStr.equals("user_sex")
                || sortStr.equals("user_position") || sortStr.equals("user_level")
                || sortStr.equals("user_role") || sortStr.length()==0)){
            return  ResultUtil.error(1015,"输入字段异常，该字段非数据库表字段");
        }else if(!(sortOpt.equals("asc") || sortOpt.equals("desc") || sortOpt.length()==0)){
            return  ResultUtil.error(1016,"输入字段异常，该字段非数据库表排序关键字");
        }else if(sortStr.length()==0){
            employeeAccountBase=employeeAccountService.getEmployeeAccountBase();
        }else{
            employeeAccountBase=employeeAccountService.sortEmployeeAccountBase(sortStr,sortOpt);
        }
        PageInfo<EmployeeAccountBase> pageInfo = new PageInfo<EmployeeAccountBase>(employeeAccountBase);
        return ResultUtil.success(pageInfo);
    }


//    @ApiOperation("账户信息分页排序")
//    @GetMapping("/account/sort_account_page")
//    public Msg AccountList(String sortStr, String sortOpt, String pageNum) {
//        CommonMethod c1=new CommonMethod();
//        boolean flag=c1.isInt(pageNum);
//        if(flag==true){
//            PageHelper.startPage(Integer.valueOf(pageNum),10);
//        }else{
//            PageHelper.startPage(1,10);
//        }
//        List<Account> account;
//        if(sortStr==null){
//            account=accountService.getAccount();
//        }else{
//            account=accountSortService.sortAccount(sortStr, sortOpt);
//        }
//        PageInfo<Account> pageInfo = new PageInfo<Account>(account);
//        return ResultUtil.success(pageInfo);
//    }
//
//    @ApiOperation("账户明细信息分页排序")
//    @GetMapping("/account/sort_accountDetail_page")
//    public Msg AccountDetailList(String sortStr, String sortOpt, String pageNum) {
//        CommonMethod c2=new CommonMethod();
//        boolean flag=c2.isInt(pageNum);
//        if(flag==true){
//            PageHelper.startPage(Integer.valueOf(pageNum),10);
//        }else{
//            PageHelper.startPage(1,10);
//        }
//        List<AccountDetail> accountDetail;
//        if(sortStr==null){
//            accountDetail=accountDetailService.getAccountDetail();
//        }else{
//            accountDetail=accountSortService.sortAccountDetail(sortStr, sortOpt);
//        }
//        PageInfo<AccountDetail> pageInfo = new PageInfo<AccountDetail>(accountDetail);
//        return ResultUtil.success(pageInfo);
//    }


//    @ApiOperation("账户信息分页展示")
//    @GetMapping("/account/sort_account_page/{pageNum}")
//    public PageInfo userList(@PathVariable("pageNum") int pageNum) {
//        PageHelper.startPage(pageNum,10);
//        List<Account> account=accountService.getAccount();
//        PageInfo<Account> pageInfo = new PageInfo<Account>(account);
//        return pageInfo;
//    }


}
