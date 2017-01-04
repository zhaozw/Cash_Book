package star.liuwen.com.cash_books.View;

/**
 * 饼图自定义view
 * Created by liuwen on 2017/1/4.
 *
 */
public class MagnificentChartItem {

// #MARK - Constants

    public int color;
    public int value;
    public String title;

// #MARK - Constructors

    public MagnificentChartItem(String title, int value, int color){
        this.color = color;
        this.value = value;
        this.title = title;
    }

}
