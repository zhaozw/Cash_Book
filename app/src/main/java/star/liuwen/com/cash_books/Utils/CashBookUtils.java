package star.liuwen.com.cash_books.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.bean.SaveMoneyPlanModel;

/**
 * Created by liuwen on 2017/2/21.
 */
public class CashBookUtils {
    // 选项存储 *********************************************
    private static final String PREFS_NAME = "yiyao_settings";
    private static final int PREFS_MODE = Context.MODE_PRIVATE;

    /**
     * 在首选项中保存用户信息
     *
     * @param context
     * @param user
     */
    public static void saveMoneyPlan(Context context, SaveMoneyPlanModel model) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Config.TxtMoney, model.getSaveMoney() + "");
        editor.putString(Config.TxtEndTime, model.getEndTime());
        editor.putString(Config.TxtRemark, model.getRemark());
        editor.putString(Config.TxtStartTime, model.getStartTime());
        editor.putString(Config.TxtPercent, model.getYield() + "");
        editor.putString(Config.SaveAPenPlatform, model.getPlatForm());
        editor.commit();
    }



    /**
     * 获取首选项存储中的用户信息
     *
     * @param context
     * @return
     */
    public static SaveMoneyPlanModel getMoneyPlan(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
        String txtMoney = preferences.getString(Config.TxtMoney, "");
        String txtEndTime = preferences.getString(Config.TxtEndTime, "");
        String txtRemark = preferences.getString(Config.TxtRemark, "");
        String txtStartTime = preferences.getString(Config.TxtStartTime, "");
        String txtPercent = preferences.getString(Config.TxtPercent, "");
        String saveAPenPlatform = preferences.getString(Config.SaveAPenPlatform, "");

        SaveMoneyPlanModel model = new SaveMoneyPlanModel();


        return model;
    }


}
