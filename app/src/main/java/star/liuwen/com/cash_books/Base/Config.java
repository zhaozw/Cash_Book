package star.liuwen.com.cash_books.Base;

/**
 * Created by liuwen on 2017/1/10.
 */
public class Config {

    public static final String SHARED_PREFERENCES_FILE_NAME = "Jzb";

    public enum Account {
        Cash, Cxk, Xyk, Zfb, Jc, Jr
    }

    public static Account mAccount = null;

    public final static String isBgCash = "isBgCash";
    public final static String isBgCXK = "isBgCXK";
    public final static String isBgXYK = "isBgXYK";
    public final static String isBgZFB = "isBgZFB";
    public final static String isBgJC = "isBgJC";
    public final static String isBgJR = "isBgJR";

    public final static String isRemindPush = "isRemindPush";
    public final static String ChangeBg = "ChangeBg";

    public enum SaveMoneyPlan {
        ly, ych, game, rom, car, firstTen
    }

    public static SaveMoneyPlan mSaveMoneyPlan = null;

    public final static String RemindDate = "RemindDate";
    public final static String RemindCycle = "RemindCycle";

}
