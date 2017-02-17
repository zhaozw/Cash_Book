package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.util.Calendar;
import java.util.Date;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.SnackBarUtil;
import star.liuwen.com.cash_books.Utils.StatusBarUtils;
import star.liuwen.com.cash_books.View.NumberAnimTextView;
import star.liuwen.com.cash_books.bean.ChoiceAccount;

/**
 * Created by liuwen on 2017/2/17.
 */
public class PayShowActivity extends BaseActivity {
    private NumberAnimTextView tvAccount;
    private TextView txtMonth, txtLiuChu, txtLiuRu;
    private RelativeLayout ryBg;
    private TimePickerView pvTime;


    @Override
    public int activityLayoutRes() {
        return R.layout.pay_show_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftText(getString(R.string.back));
        setLeftTextColor(this.getResources().getColor(R.color.white));
        setRightTxtColor(this.getResources().getColor(R.color.white));
        setTitlesColor(this.getResources().getColor(R.color.white));
        setRightTxtColor(this.getResources().getColor(R.color.white));
        setRightText(getString(R.string.setting), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tvAccount = (NumberAnimTextView) findViewById(R.id.pay_show_txt_account);
        txtMonth = (TextView) findViewById(R.id.pay_show_txt_month);
        txtLiuChu = (TextView) findViewById(R.id.pay_show_txt_liuchu);
        txtLiuRu = (TextView) findViewById(R.id.pay_show_txt_liuru);
        ryBg = (RelativeLayout) findViewById(R.id.pay_ry);

        ChoiceAccount model = (ChoiceAccount) getIntent().getExtras().getSerializable(Config.ModelWallet);
        if (model != null) {
            tvAccount.setText(model.getMoney() + "元");
            ryBg.setBackgroundResource(model.getColor());
            StatusBarUtils.setWindowStatusBarColor(this, model.getColor());
            setTitleBg(model.getColor());
            setTitle(model.getAccountName());
        }
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
                txtMonth.setText(DateTimeUtil.getTime(date));
            }
        });
        //显示
        pvTime.show();
    }


    public void toYuer(View view) {
        Intent intent = new Intent(PayShowActivity.this, UpdateCommonKeyBoardActivity.class);
        intent.putExtra(Config.SaveAPenPlatform, "YuER");
        startActivityForResult(intent, YuER);
    }

    private static final int YuER = 101;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case YuER:
                    tvAccount.setNumberString(data.getExtras().getString(Config.TextInPut));
                    break;
            }
        }
    }
}
