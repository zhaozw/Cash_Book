package star.liuwen.com.cash_books.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.StatusBarUtils;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.View.NumberAnimTextView;

/**
 * Created by liuwen on 2016/12/30.
 */
public class PaymentActivity extends BaseActivity {
    private RelativeLayout mRyBg;
    private TimePickerView pvTime;
    private TextView tvYear, tvZhuangZhang, tvChu, tvRu;
    private NumberAnimTextView tvAccount;
    private String AccountSetting;
    private ViewStub mViewStub;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    ToastUtils.showToast(PaymentActivity.this, "发消息了啊");
                    break;
            }
        }
    };

    @Override
    public int activityLayoutRes() {
        return R.layout.payment_activity;
    }

    @Override
    public void initView() {
        mRyBg = (RelativeLayout) findViewById(R.id.pay_ry);
        mViewStub = (ViewStub) findViewById(R.id.view_stub);
        mViewStub.inflate();

        tvYear = (TextView) findViewById(R.id.pay_txt_year);
        tvAccount = (NumberAnimTextView) findViewById(R.id.pay_txt_account);
        tvZhuangZhang = (TextView) findViewById(R.id.pay_zhuanzhuang);
        tvChu = (TextView) findViewById(R.id.pay_chu);
        tvRu = (TextView) findViewById(R.id.pay_ru);


        String value = getIntent().getStringExtra("paySetting");

        if (value.equals("zhifubao")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.zhifubao));
            setTitleBg(R.color.zhifubao);
            StatusBarUtils.setWindowStatusBarColor(PaymentActivity.this, R.color.zhifubao);
            setTitle("支付宝");
            AccountSetting = "zfb";
            tvAccount.setText(SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.ZfbS, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.ZfbS, ""));
            setTexTSizeAndColor();
        } else if (value.equals("xinyaka")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.xinyongka));
            setTitleBg(R.color.xinyongka);
            StatusBarUtils.setWindowStatusBarColor(PaymentActivity.this, R.color.xinyongka);
            setTitle("信用卡");
            AccountSetting = "xyk";
            tvAccount.setText(SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.XykS, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.XykS, ""));
            setTexTSizeAndColor();
        } else if (value.equals("cash")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.xianjian));
            setTitleBg(R.color.xianjian);
            StatusBarUtils.setWindowStatusBarColor(PaymentActivity.this, R.color.xianjian);
            setTitle("现金");
            AccountSetting = "cash";
            tvAccount.setText(SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.CashS, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.CashS, ""));
            setTexTSizeAndColor();
        } else if (value.equals("chuxuka")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.chuxuka));
            StatusBarUtils.setWindowStatusBarColor(PaymentActivity.this, R.color.chuxuka);
            setTitleBg(R.color.chuxuka);
            setTitle("储蓄卡");
            AccountSetting = "cxk";
            tvAccount.setText(SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.CxkS, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(PaymentActivity.this, Config.CxkS, ""));
            setTexTSizeAndColor();
        }
        setBackView();
    }

    public void setTexTSizeAndColor() {
        setLeftText("钱包");
        setLeftTextColor(this.getResources().getColor(R.color.white));
        setRightTxtColor(this.getResources().getColor(R.color.white));
        setTitlesColor(this.getResources().getColor(R.color.white));
    }

    public void toData(View view) {
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        //设置标题
        pvTime.setTitle("选择日期");
        //控制时间范围
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR) + 30);
        pvTime.setTime(new Date());
        //设置是否循环
        pvTime.setCyclic(false);
        //设置是否可以关闭
        pvTime.setCancelable(true);
        //设置选择监听
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                handler.sendEmptyMessage(0);
                tvYear.setText(DateTimeUtil.getTime(date));
            }
        });
        //显示
        pvTime.show();
    }

    public void toZhuangZhang(View view) {
        tvAccount.setPrefixString("¥");
        tvAccount.setNumberString("85260");
    }

    public void toYuer(View view) {
        Intent intent = new Intent(PaymentActivity.this, UpDateSaveMoneyCommonActivity.class);
        intent.putExtra("UpdateSaveMoney", "YuER");
        intent.putExtra(Config.AccountSetting, AccountSetting);
        startActivityForResult(intent, YuER);
    }

    private static final int YuER = 101;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (resultCode) {
                case Config.Cash:
                    tvAccount.setPrefixString("¥");
                    tvAccount.setNumberString(data.getExtras().getString("textInput"));
                    break;
                case Config.Zfb:
                    tvAccount.setPrefixString("¥");
                    tvAccount.setNumberString(data.getExtras().getString("textInput"));
                    break;
                case Config.Xyk:
                    tvAccount.setPrefixString("¥");
                    tvAccount.setNumberString(data.getExtras().getString("textInput"));
                    break;
                case Config.Cxk:
                    tvAccount.setPrefixString("¥");
                    tvAccount.setNumberString(data.getExtras().getString("textInput"));
                    break;
            }
        }
    }
}
