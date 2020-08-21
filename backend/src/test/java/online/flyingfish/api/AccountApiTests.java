package online.flyingfish.api;

import online.flyingfish.api.model.Account;
import online.flyingfish.api.model.User;
import online.flyingfish.api.service.impl.AccountServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountApiTests {
    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Before
    public void before() {
        System.out.println("=============Start Test=============");
    }

    @Test
    public void AccountQueryTest() {
        System.out.println("账户信息查询");
        List<Account> list = accountServiceImpl.getAccount();
        int len = list.size();
        for (int i = 0; i < len; i++) {
            System.out.print(list.get(i).getAcc_id() + " ");
            System.out.print(list.get(i).getUser_id() + " ");
            System.out.print(list.get(i).getAcc_balance() + " ");
            System.out.print(list.get(i).getAcc_status() + " ");
            System.out.print(list.get(i).getAcc_create_time() + " ");
            System.out.print(list.get(i).getAcc_change_time() + " ");
            System.out.println();
        }
    }

    @Test
    public void AccountAddTest(){
        Account account1=new Account();
        account1.setAcc_id(6);
        account1.setUser_id(6);
        account1.setAcc_balance(new BigDecimal("213.00"));
        String s1="2020-06-07 10:10:10";
        account1.setAcc_create_time(Timestamp.valueOf(s1));
        String s2="2020-07-07 10:10:10";
        account1.setAcc_change_time(Timestamp.valueOf(s2));
//        account1.setAcc_change_time(new Timestamp(new java.util.Date().getTime()));
        accountServiceImpl.addAccount(account1);

    }


}
