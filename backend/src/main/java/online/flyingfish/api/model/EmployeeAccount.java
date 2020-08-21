package online.flyingfish.api.model;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

public class EmployeeAccount {
    //需要展现的员工信息
    private int userId;
    private String userName;
    private String userSex;
    private String empNo;
    private String  compName;
    private String deptName;
    private String userPosition;
    private int userLevel;
    //需要展现的账户信息
    private BigDecimal accBalance;
    private BigDecimal accBalanceMonth;
    private BigDecimal accBalanceYear;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public BigDecimal getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(BigDecimal accBalance) {
        this.accBalance = accBalance;
    }

    public BigDecimal getAccBalanceMonth() {
        return accBalanceMonth;
    }

    public void setAccBalanceMonth(BigDecimal accBalanceMonth) {
        this.accBalanceMonth = accBalanceMonth;
    }

    public BigDecimal getAccBalanceYear() {
        return accBalanceYear;
    }

    public void setAccBalanceYear(BigDecimal accBalanceYear) {
        this.accBalanceYear = accBalanceYear;
    }
}
