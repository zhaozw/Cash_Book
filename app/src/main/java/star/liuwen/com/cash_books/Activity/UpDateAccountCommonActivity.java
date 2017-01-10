package star.liuwen.com.cash_books.Activity;

import android.view.View;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;

/**
 * Created by liuwen on 2017/1/10.
 * 修改账户余额
 */
public class UpDateAccountCommonActivity extends BaseActivity {
    @Override
    public int activityLayoutRes() {
        return R.layout.update_account_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftText(getString(R.string.pay_setting));
        setRightText(getString(R.string.save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String values = getIntent().getStringExtra("888");
        if (values.equals("AccountName")) {
            setTitle(getString(R.string.edit_account_name));
        } else if (values.equals("AccountMoney")) {
            setTitle(getString(R.string.edit_account_money));
        } else if (values.equals("CreditLimit")) {
            setTitle(getString(R.string.edit_credit_limit));
        } else if (values.equals("Debt")) {
            setTitle(getString(R.string.edit_debt));
        }
    }
}
