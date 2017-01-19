package star.liuwen.com.cash_books.Base;

/**
 * Created by liuwen on 2017/1/10.
 */
public class Config {

    public static final String SHARED_PREFERENCES_FILE_NAME = "Jzb";

    public enum Account {
        Cash, Cxk, Xyk, Zfb, Jc, Jr
    }

    public final static String isBgCash = "isBgCash";
    public final static String isBgCXK = "isBgCXK";
    public final static String isBgXYK = "isBgXYK";
    public final static String isBgZFB = "isBgZFB";
    public final static String isBgJC = "isBgJC";
    public final static String isBgJR = "isBgJR";

    public final static String isRemindPush = "isRemindPush";

    public final static String isOneDay = "isOneDay";
    public final static String isTwoDay = "isTwoDay";
    public final static String isThreeDay = "isThreeDay";
    public final static String isFourDay = "isFourDay";
    public final static String isFiveDay = "isFiveDay";
    public final static String isSixDay = "isSixDay";
    public final static String isSevenDay = "isSevenDay";

}
