package online.flyingfish.api;

import online.flyingfish.api.controller.AccountController;
import online.flyingfish.api.controller.AccountDetailController;
import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.AccountDetail;
import online.flyingfish.api.service.AccountDetailService;
import online.flyingfish.api.service.AccountService;
import online.flyingfish.api.service.impl.AccountDetailServiceImpl;
import online.flyingfish.api.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDetailApiTest {
//    @Autowired
//    private AccountDetailController accountDetailController;
    @Autowired
    private AccountDetailService accountDetailService;

//    @Autowired
//    private AccountController accountController;

    @Autowired
    private AccountService accountService;


    @Before
    public void before() {
        System.out.println("=============Start Test=============");
    }

    @Test
    public void AccountDetailQueryTest() {
        System.out.println("账户信息查询");
        List<AccountDetail> list = accountDetailService.getAccountDetail();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            System.out.print(list.get(i).getChange_id() + " ");
            System.out.print(list.get(i).getUser_id() + " ");
            System.out.print(list.get(i).getOperate_type() + " ");
            System.out.print(list.get(i).getOperate() + " ");
            System.out.print(list.get(i).getAmount() + " ");
            System.out.print(list.get(i).getBalance() + " ");
            System.out.print(list.get(i).getOrder_id() + " ");
            System.out.print(list.get(i).getDetail_create_time() + " ");
            System.out.print(list.get(i).getDetail_create_user_id() + " ");
            System.out.println();
        }
    }

    @Test
    public void AccountDetailAddTest1(){
        AccountDetail accountDetail1=new AccountDetail();
        accountDetail1.setChange_id(6);
        accountDetail1.setUser_id(6);
        accountDetail1.setOperate_type("充值");
        accountDetail1.setOperate(0);
        accountDetail1.setAmount(new BigDecimal("100"));
        accountDetail1.setBalance(new BigDecimal("213"));
        accountDetail1.setOrder_id(null);
        String s1="2020-07-07 11:52:33.000000";
        accountDetail1.setDetail_create_time(Timestamp.valueOf(s1));
        accountDetail1.setUser_id(2);
        accountDetailService.addAccountDetail(accountDetail1);
    }

    @Test
    public void AccountDetailAddTest2(){
        int changeId;
        int userId;
        String operateType;
        int operate=0;
        BigDecimal amount=new BigDecimal("0");
        BigDecimal balance;
        String orderId=null;   //暂时不考虑订单问题，默认为空
        Timestamp detailCreateTime;
        int detailCreateUserId=2;  //暂时默认是2号管理员操作

        Account account2=new Account();
        List<Account> acc_list1=accountService.getAccount();
        //可以监听接口的状态，判断一下哪个接口被调用了，然后对应接口明细中就是相应的充值和修改操作
        System.out.println("请选择操作类型：充值 or 修改： ");
//        String flag=new Scanner(System.in).nextLine();
        String flag="充值";
        System.out.println(flag);
        Timestamp t1=new Timestamp(new Date().getTime());
        detailCreateTime=t1;

        System.out.println("请输入要操作的id： ");
//        int acc_id1=new Scanner(System.in).nextInt();
        int acc_id1=6;
        System.out.println(acc_id1);
        System.out.println("请输入要操作的数值：");
//        BigDecimal balance1=new Scanner(System.in).nextBigDecimal();
        BigDecimal balance1=new BigDecimal("100");
        System.out.println(balance1);
        userId=acc_id1;
        operateType=flag;
        BigDecimal former_balance=acc_list1.get(acc_id1-1).getAcc_balance();
        if(flag=="充值"){
            accountService.addAccountBalance(acc_id1,balance1);
        }else if(flag=="修改"){
            accountService.updateAccountById(acc_id1,balance1);
        }

        List<Account> acc_list2=accountService.getAccount();
        BigDecimal now_balance=acc_list2.get(acc_id1-1).getAcc_balance();
        BigDecimal sub=now_balance.subtract(former_balance);
        int res=sub.compareTo(new BigDecimal("0"));
        if(res==1){
            operate=0;
        }else if(res==-1){
            operate=1;  //金额增加
        }else if(res==0){
            operate=2;  //金额不变
        }

        if(operateType=="充值"){
            amount=balance1;
        }else if(operateType=="修改"){
            amount=sub.abs();
        }
        balance=now_balance;


        List<AccountDetail> list2 = accountDetailService.getAccountDetail();
        changeId=list2.get(list2.size()-1).getChange_id()+1;
        AccountDetail accountDetail=new AccountDetail();
        accountDetail.setChange_id(changeId);
        accountDetail.setUser_id(userId);
        accountDetail.setOperate_type(operateType);
        accountDetail.setOperate(operate);
        accountDetail.setAmount(amount);
        accountDetail.setBalance(balance);
        accountDetail.setDetail_create_time(detailCreateTime);
        accountDetail.setDetail_create_user_id(detailCreateUserId);
        accountDetailService.addAccountDetail(accountDetail);


    }
}
