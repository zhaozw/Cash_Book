package star.liuwen.com.cash_books.Base;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import star.liuwen.com.cash_books.bean.AccountModel;
import star.liuwen.com.cash_books.bean.CreditCardModel;

/**
 * Created by liuwen on 2017/1/6.
 */
public class App extends Application {

    public static int position;//账户position;
    public static List<Map<String, AccountModel>> accountMaps;
    public static boolean isBgCash = true;
    public static boolean isBgCXK = true;
    public static boolean isBgXYK = true;
    public static boolean isBgZFB = true;
    public static boolean isBgJC = true;
    public static boolean isBgJR = true;

    public static CreditCardModel cardModel;
    public static HashMap<String, CreditCardModel> maps;

    public static boolean isRemindPush = false;
    public static boolean isOpenCodedLock = false;
    public static String cycleData = "";
    public static String cycleTime = "";

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化内存检测工具
        mRefWatcher = LeakCanary.install(this);
    }

    // 在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {

        App application = (App) context
                .getApplicationContext();
        return application.mRefWatcher;
    }
}
