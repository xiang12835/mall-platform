package online.flyingfish.api;

import online.flyingfish.api.model.Account;
import online.flyingfish.api.service.impl.AccountSortServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountSortTest {
    @Autowired
    private AccountSortServiceImpl accountSortServiceImpl;

    @Before
    public void before() {
        System.out.println("=============Start Test=============");
    }

    @Test
    public void AccountSortTest(){
        System.out.println("账户信息排序");
        List<Account> list = accountSortServiceImpl.sortAccount("acc_change_time",null);

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
}
