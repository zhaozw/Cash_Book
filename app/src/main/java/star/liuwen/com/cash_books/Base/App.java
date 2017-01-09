package star.liuwen.com.cash_books.Base;

import android.app.Application;

import java.util.List;
import java.util.Map;

import star.liuwen.com.cash_books.bean.AccountModel;

/**
 * Created by liuwen on 2017/1/6.
 */
public class App extends Application {

    public static int position;//账户position;
    public static List<Map<String, AccountModel>> accountMaps;


    @Override
    public void onCreate() {
        super.onCreate();
    }
}
