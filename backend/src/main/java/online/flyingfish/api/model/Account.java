package online.flyingfish.api.model;

import javax.xml.crypto.Data;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Account {
    private int accId;   //对应数据库中字段表示为acc_id

    private int userId;

    private BigDecimal accBalance;

    private int accStatus;

    private Timestamp accCreateTime;

    private Timestamp accChangeTime;

    public int getAcc_id() {     //get与set方法与response中的字段值结果对应
        return accId;
    }

    public void setAcc_id(int acc_id) {
        this.accId = acc_id;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int user_id) {
        this.userId = user_id;
    }

    public BigDecimal getAcc_balance() {
        return accBalance;
    }

    public void setAcc_balance(BigDecimal acc_balance) {
        this.accBalance = acc_balance;
    }

    public int getAcc_status() {
        return accStatus;
    }

    public void setAcc_status(int acc_status) {
        this.accStatus = acc_status;
    }

    public Timestamp getAcc_create_time() {
        return accCreateTime;
    }

    public void setAcc_create_time(Timestamp acc_create_time) {
        this.accCreateTime = acc_create_time;
    }

    public Timestamp getAcc_change_time() {
        return accChangeTime;
    }

    public void setAcc_change_time(Timestamp acc_change_time) {
        this.accChangeTime = acc_change_time;
    }

    public Account() {
    }
}
