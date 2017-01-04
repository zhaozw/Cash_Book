package star.liuwen.com.cash_books.Activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.View.NumberAnimTextView;

/**
 * Created by liuwen on 2016/12/30.
 */
public class PaymentActivity extends BaseActivity {
    private RelativeLayout mRyBg, mRyNoData;
    private TimePickerView pvTime;
    private TextView tvYear, tvZhuangZhang, tvChu, tvRu;
    private NumberAnimTextView tvAccount;


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

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mRyBg = (RelativeLayout) findViewById(R.id.pay_ry);
        mRyNoData = (RelativeLayout) findViewById(R.id.no_pic);

        tvYear = (TextView) findViewById(R.id.pay_txt_year);
        tvAccount = (NumberAnimTextView) findViewById(R.id.pay_txt_account);
        tvZhuangZhang = (TextView) findViewById(R.id.pay_zhuanzhuang);
        tvChu = (TextView) findViewById(R.id.pay_chu);
        tvRu = (TextView) findViewById(R.id.pay_ru);


        String value = getIntent().getStringExtra("666");

        if (value.equals("zhifubao")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.zhifubao));
            setTitleBg(R.color.zhifubao);
        } else if (value.equals("xinyaka")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.xinyongka));
            setTitleBg(R.color.xinyongka);
        }
        setBackView();
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

    }
}
