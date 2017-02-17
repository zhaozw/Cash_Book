package star.liuwen.com.cash_books.bean;

import java.io.Serializable;

import star.liuwen.com.cash_books.Base.Config;

/**
 * Created by liuwen on 2017/1/18.
 */
public class ChoiceAccount implements Serializable {

    private Long id;
    private int url;//选择账户的url
    private int WalletUrl;
    private String accountName;//账户名
    private Double money;//金额(其他账户的余额)
    private Double CreditLimit;//信用卡余额 //废弃的字段
    private Double Debt;//欠款
    private String DebtDate;//欠款日
    private String AccountType;//账户类型
    private String IssuingBank;//发卡行
    //颜色的字段
    private int color;
    public AccountType mAccountType; //账户类型
    private Double liuChu; //账户流出
    private Double liuRu; //账户流入


    public ChoiceAccount(int url, String accountName, Double money, Double creditLimit, Double debt, ChoiceAccount.AccountType account, int color) {
        this.url = url;
        this.accountName = accountName;
        this.money = money;
        CreditLimit = creditLimit;
        Debt = debt;
        mAccountType = account;
        this.color = color;
    }

    public ChoiceAccount() {
    }


    public enum AccountType {
        Cash, Cxk, Xyk, Zfb, Jc, Jr, other
    }


    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public int getWalletUrl() {
        return WalletUrl;
    }

    public void setWalletUrl(int walletUrl) {
        WalletUrl = walletUrl;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getCreditLimit() {
        return CreditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        CreditLimit = creditLimit;
    }

    public double getDebt() {
        return Debt;
    }

    public void setDebt(double debt) {
        Debt = debt;
    }

    public String getDebtDate() {
        return DebtDate;
    }

    public void setDebtDate(String debtDate) {
        DebtDate = debtDate;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getIssuingBank() {
        return IssuingBank;
    }

    public void setIssuingBank(String issuingBank) {
        IssuingBank = issuingBank;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
