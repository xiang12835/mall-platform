package online.flyingfish.api;


import online.flyingfish.api.controller.AccountChangeController;
import online.flyingfish.api.model.AccountChange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountChangeTest {
    @Autowired
    private AccountChangeController accountChangeController;

    @Test
    public void recharge() {
        AccountChange accountChange = new AccountChange();
        accountChange.setChangeId(10);
        accountChange.setEmpId(6);
        accountChange.setOperateType("buy");
        accountChange.setOperate(1);


    }

}
