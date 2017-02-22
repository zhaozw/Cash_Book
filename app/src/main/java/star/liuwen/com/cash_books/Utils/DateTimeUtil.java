package star.liuwen.com.cash_books.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by liuwen on 2016/12/15.
 * 日期转化类
 */
public class DateTimeUtil {

    //获取相应的年和月 分割的
    public static String[] getShowCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String[] msdf = sdf.format(new Date()).split("-");
        return msdf;
    }

    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日");
        return sdf.format(new Date());
    }

    public static String getCurrentTimeMinSec() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String getCurrentTimeHourMIn(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime_Today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return sdf.format(new Date());
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09:00"）
     *
     * @param time
     * @return
     */
    public static String timedate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16:09"）
     *
     * @param time
     * @return
     */
    public static String timet(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    public static String getYearMonthDay_(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getYearMonthDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }


    public static long LoadDay(String a1, String b1) throws ParseException {

        Date A1 = new SimpleDateFormat("yyyy-MM-dd").parse(a1);
        Date B1 = new SimpleDateFormat("yyyy-MM-dd").parse(b1);
        return (B1.getTime() - A1.getTime()) / (24 * 60 * 60 * 1000);
    }


}

