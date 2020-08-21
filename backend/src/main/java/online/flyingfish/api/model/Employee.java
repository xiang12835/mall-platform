package online.flyingfish.api.model;

import javax.xml.crypto.Data;
import java.security.PrivateKey;

public class Employee {
    private int userId;
    private String userName;
    private String userSex;
    private String empNo;
    private int compId;
    private int deptId;
    private String userBirthplace;
    private Data userHiredate;
    private String userPosition;
    private int userLevel;
    private String userEmail;
    private String userPhone;
    private int userRole;
    private int userStatus;

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

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getUserBirthplace() {
        return userBirthplace;
    }

    public void setUserBirthplace(String userBirthplace) {
        this.userBirthplace = userBirthplace;
    }

    public Data getUserHiredate() {
        return userHiredate;
    }

    public void setUserHiredate(Data userHiredate) {
        this.userHiredate = userHiredate;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}
