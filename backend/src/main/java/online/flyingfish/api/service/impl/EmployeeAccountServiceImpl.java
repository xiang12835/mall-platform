package online.flyingfish.api.service.impl;

import online.flyingfish.api.mapper.AccountDetailMapper;
import online.flyingfish.api.mapper.EmployeeAccountMapper;
import online.flyingfish.api.model.*;
import online.flyingfish.api.service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {
    @Autowired
    private EmployeeAccountMapper employeeAccountMapper;

    //查询用户基本信息
    @Override
    public List<EmployeeAccountBase> getEmployeeAccountBase(){
        return employeeAccountMapper.getEmployeeAccountBase();
    }

    //查询用户月消费金额
    @Override
    public List<BigDecimal> getEmployeeAccountCountMonth(int user_id){
        return employeeAccountMapper.getEmployeeAccountCountMonth(user_id);
    }

    //查询用户年消费金额
    @Override
    public BigDecimal getEmployeeAccountCountYear(int user_id){
        return employeeAccountMapper.getEmployeeAccountCountYear(user_id);
    }

    //用户信息查询排序
    @Override
    public List<EmployeeAccountBase> sortEmployeeAccountBase(String sortStr, String sortOpt){
        return employeeAccountMapper.sortEmployeeAccountBase(sortStr,sortOpt);
    }



//    @Override
//    public List<EmployeeAccountCountMonth> getEmployeeAccountCountMonth(){
//        return employeeAccountMapper.getEmployeeAccountCountMonth();
//    }
//
//    @Override
//    public List<EmployeeAccountCountYear> getEmployeeAccountCountYear(){
//        return employeeAccountMapper.getEmployeeAccountCountYear();
//    }

}
