package star.liuwen.com.cash_books.Base;

import android.os.Environment;

/**
 * Created by liuwen on 2017/1/10.
 */
public class Config {

    public static final String RootPath = getRootPath() + "/Cash_Books/"; //上传经过压缩好的相册
    public static final String SHARED_PREFERENCES_FILE_NAME = "Jzb";

    public static final String PhotoPath = getRootPath() + "/cashPhoto"; //存放拍好的相册

    private static String getRootPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

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

    public final static String userUrl = "userUrl";
    public final static String userNickName = "userNickName";
    public final static String userSignature = "userSignature";
    public final static String userSex = "userSix";
    public final static String tarGet = "tarGet";
    public final static String tarRemarks = "tarRemarks";
    public final static String tarYear = "tarYear";
    public final static String YuEr = "YuEr";


    public final static String HuoBICh = "HuoBICh";
    public final static String HuoBIEn = "HuoBIEn";

    public final static String AccountSetting = "AccountSetting";

    public static final String SHARE_LOGO = RootPath + "/share/logo/";


    public static final int Xyk = 101;
    public static final int Cxk = 102;
    public static final int Zfb = 103;
    public static final int Cash = 104;

    public static final String XykS = "Xyk";
    public static final String CxkS = "Cxk";
    public static final String ZfbS = "Zfb";
    public static final String CashS = "Cash";


}
