package star.liuwen.com.cash_books.bean;

/**
 * Created by liuwen on 2017/1/3.
 */
public class HomeModel {

    private float zhichu;
    private float shouru;

    public HomeModel(float zhichu, float shouru) {
        this.zhichu = zhichu;
        this.shouru = shouru;
    }

    public float getZhichu() {
        return zhichu;
    }

    public void setZhichu(float zhichu) {
        this.zhichu = zhichu;
    }

    public float getShouru() {
        return shouru;
    }

    public void setShouru(float shouru) {
        this.shouru = shouru;
    }
}
