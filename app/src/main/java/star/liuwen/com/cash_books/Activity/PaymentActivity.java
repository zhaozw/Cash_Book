package star.liuwen.com.cash_books.Activity;

import android.view.WindowManager;
import android.widget.RelativeLayout;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;

/**
 * Created by liuwen on 2016/12/30.
 */
public class PaymentActivity extends BaseActivity {
    private RelativeLayout mRyBg;

    @Override
    public int activityLayoutRes() {
        return R.layout.payment_activity;
    }

    @Override
    public void initView() {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String value = getIntent().getStringExtra("666");
        mRyBg = (RelativeLayout) findViewById(R.id.pay_ry);
        if (value.equals("zhifubao")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.zhifubao));
            setTitleBg(R.color.zhifubao);
        } else if (value.equals("xinyaka")) {
            mRyBg.setBackgroundColor(this.getResources().getColor(R.color.xinyongka));
            setTitleBg(R.color.xinyongka);
        }


        setBackView();
    }
}
