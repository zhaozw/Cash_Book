package star.liuwen.com.cash_books.bean;

/**
 * Created by liuwen on 2017/1/18.
 */
public class ChoiceAccount {

    private Long id;
    private int url;
    private String accountName;
    private String money;

    public ChoiceAccount() {
    }

    public ChoiceAccount(int url, String accountName, String money) {
        this.url = url;
        this.accountName = accountName;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
