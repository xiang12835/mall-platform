package online.flyingfish.api.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountDetail {
    private int changeId;   //对应数据库中字段表示为acc_id

    private int userId;

    private String operateType;

    private int operate;

    private BigDecimal amount;

    private BigDecimal balance;

    private String orderId;

    private Timestamp detailCreateTime;

    private int detailCreateUserId;

    public int getChange_id() {
        return changeId;
    }

    public void setChange_id(int changeId) {
        this.changeId = changeId;
    }

    public int getUser_id() {
        return userId;
    }

    public void setUser_id(int userId) {
        this.userId = userId;
    }

    public String getOperate_type() {
        return operateType;
    }

    public void setOperate_type(String operateType) {
        this.operateType = operateType;
    }

    public int getOperate() {
        return operate;
    }

    public void setOperate(int operate) {
        this.operate = operate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getOrder_id() {
        return orderId;
    }

    public void setOrder_id(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getDetail_create_time() {
        return detailCreateTime;
    }

    public void setDetail_create_time(Timestamp detailCreateTime) {
        this.detailCreateTime = detailCreateTime;
    }

    public int getDetail_create_user_id() {
        return detailCreateUserId;
    }

    public void setDetail_create_user_id(int detailCreateUserId) {
        this.detailCreateUserId = detailCreateUserId;
    }
}
