package star.liuwen.com.cash_books.bean;

import java.io.Serializable;

/**
 * Created by liuwen on 2017/2/7.
 */
public class ReportsDetailModel implements Serializable {

    private long id;
    private String consumeName;
    private int url;
    private String consumeMoney;
    private String consumePercent;


    public ReportsDetailModel() {
    }

    public ReportsDetailModel(long id, String consumeName, int url, String consumeMoney, String consumePercent) {
        this.id = id;
        this.consumeName = consumeName;
        this.url = url;
        this.consumeMoney = consumeMoney;
        this.consumePercent = consumePercent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getConsumeName() {
        return consumeName;
    }

    public void setConsumeName(String consumeName) {
        this.consumeName = consumeName;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getConsumeMoney() {
        return consumeMoney;
    }

    public void setConsumeMoney(String consumeMoney) {
        this.consumeMoney = consumeMoney;
    }

    public String getConsumePercent() {
        return consumePercent;
    }

    public void setConsumePercent(String consumePercent) {
        this.consumePercent = consumePercent;
    }
}
