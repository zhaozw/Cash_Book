package star.liuwen.com.cash_books.bean;

import java.io.Serializable;

/**
 * Created by liuwen on 2017/2/21.
 */
public class SaveMoneyPlanModel implements Serializable {
    private long id;
    private String platForm;//存款平台
    private double saveMoney;//计划存款多少
    private double yield;//收益率
    private String startTime;//起息时间
    private String endTime;//结束时间
    private String remark;//备注
    private String accountType;//账户类型

    public SaveMoneyPlanModel() {
    }

    public SaveMoneyPlanModel(String platForm, double saveMoney, double yield, String startTime, String endTime, String remark, String accountType) {
        this.platForm = platForm;
        this.saveMoney = saveMoney;
        this.yield = yield;
        this.startTime = startTime;
        this.endTime = endTime;
        this.remark = remark;
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public double getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(double saveMoney) {
        this.saveMoney = saveMoney;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
