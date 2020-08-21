package online.flyingfish.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.flyingfish.api.model.*;
import online.flyingfish.api.service.AccountDetailService;
import online.flyingfish.api.service.EmployeeAccountService;
import online.flyingfish.api.service.impl.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api2")
@Api("员工账户信息查看 Api")
public class EmployeeAccountController {
    @Autowired
    private EmployeeAccountService employeeAccountService;

    @Autowired
    private AccountDetailService accountDetailService;

    @ApiOperation("员工账户基础信息查询")
    @RequestMapping(value ="/account/query_employeeAccount_Base", method = RequestMethod.GET)
    @ResponseBody
    public Msg EmployeeAccountBaseQry() {
        return ResultUtil.success(employeeAccountService.getEmployeeAccountBase());
    }

    @ApiOperation("员工账户信息查询")
    @RequestMapping(value ="/account/query_employeeAccount", method = RequestMethod.GET)
    @ResponseBody
    public Msg EmployeeAccountCountQry() {
        List<EmployeeAccountBase> list1=employeeAccountService.getEmployeeAccountBase();
        int len=list1.size();
        List<EmployeeAccount> list2=new LinkedList<EmployeeAccount>();
        for(int i=0;i<len;i++){
            EmployeeAccount e1=new EmployeeAccount();
            EmployeeAccountBase  b1=list1.get(i);
            e1.setUserId(b1.getUserId());
            e1.setUserName(b1.getUserName());
            e1.setUserSex(b1.getUserSex());
            e1.setEmpNo(b1.getEmpNo());
            e1.setCompName(b1.getCompName());
            e1.setDeptName(b1.getDeptName());
            e1.setUserPosition(b1.getUserPosition());
            e1.setUserLevel(b1.getUserLevel());
            e1.setAccBalance(b1.getAccBalance());
            BigDecimal balance1=EmployeeAccountCountMonthQry_fix(b1.getUserId());
            e1.setAccBalanceMonth(balance1);
            BigDecimal y1=employeeAccountService.getEmployeeAccountCountYear(b1.getUserId());
            if(y1==null){
                e1.setAccBalanceYear(new BigDecimal(0));
            }else{
                e1.setAccBalanceYear(y1);
            }
            list2.add(e1);
        }
//        return list2;
        return ResultUtil.success(list2);
    }


    @ApiOperation("员工账户月统计信息查询")
    @RequestMapping(value ="/account/query_employeeAccount_count_month_fix", method = RequestMethod.GET)
    @ResponseBody
    public BigDecimal EmployeeAccountCountMonthQry_fix(int user_id) {
        List<AccountDetail> list1= accountDetailService.getExpendAccountDetailById(user_id);
        int MM1=list1.get(0).getDetail_create_time().getMonth()+1;
        Timestamp t1=new Timestamp(new Date().getTime());
        int MM2=t1.getMonth()+1;
        List<BigDecimal> list=employeeAccountService.getEmployeeAccountCountMonth(user_id);
        if(list.size()==0 || list==null)
        {
            return new BigDecimal(0);
        }else if (MM1!=MM2){
            return new BigDecimal(0);
        }else {
            return list.get(0);
        }
    }

//    @ApiOperation("员工账户月统计信息查询")
//    @RequestMapping(value ="/account/query_employeeAccount_count_month", method = RequestMethod.GET)
//    @ResponseBody
//    public List<BigDecimal> EmployeeAccountCountMonthQry(int user_id) {
//        List<BigDecimal> list=employeeAccountService.getEmployeeAccountCountMonth(user_id);
//        if(list.size()==0 || list==null)
//        {
//            List<BigDecimal> list_n=new ArrayList<BigDecimal>();
//            list_n.add(new BigDecimal(0));
//            return list_n;
//        }else {
//            return list;
//        }
//    }

    @ApiOperation("员工账户年统计信息查询")
    @RequestMapping(value ="/account/query_employeeAccount_count_year", method = RequestMethod.GET)
    @ResponseBody
    public BigDecimal EmployeeAccountCountYearQry(int user_id) {
        return employeeAccountService.getEmployeeAccountCountYear(user_id);
    }

}






























