package online.flyingfish.api.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Account {
    //账户编号
    private int accountId;

    //员工编号
    private int empId;

    //账户余额
    private BigDecimal balance;

    //账户状态
    private int status;

    //账户创建时间
    private Timestamp createTime;

    //账户余额上次变动时间
    private Timestamp changeTime;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Timestamp changeTime) {
        this.changeTime = changeTime;
    }
}
