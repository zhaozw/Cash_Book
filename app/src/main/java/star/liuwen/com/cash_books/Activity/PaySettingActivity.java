package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;

/**
 * Created by liuwen on 2017/1/10.
 */
public class PaySettingActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout reBank, reAccount, reType, reMoney, reCreditLimit, reDebt, reDebtData;
    private TextView txtBank, txtAccount, txtType, txtMoney, txtCreditLimit, txtDebt, txtDebtData;


    @Override
    public int activityLayoutRes() {
        return R.layout.pay_setting_activity;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.pay_setting));
        setBackView();
        setLeftText(getString(R.string.back));

        reBank = (RelativeLayout) findViewById(R.id.re_setting_bank);
        reAccount = (RelativeLayout) findViewById(R.id.re_setting_account);
        reType = (RelativeLayout) findViewById(R.id.re_setting_tyoe);
        reMoney = (RelativeLayout) findViewById(R.id.re_setting_money);
        reCreditLimit = (RelativeLayout) findViewById(R.id.re_setting_Credit_limit);
        reDebt = (RelativeLayout) findViewById(R.id.re_setting_debt);
        reDebtData = (RelativeLayout) findViewById(R.id.re_setting_debt_date);

        txtBank = (TextView) findViewById(R.id.setting_txt_bank);
        txtAccount = (TextView) findViewById(R.id.setting_txt_account);
        txtType = (TextView) findViewById(R.id.setting_txt_type);
        txtMoney = (TextView) findViewById(R.id.setting_txt_money);
        txtCreditLimit = (TextView) findViewById(R.id.setting_txt_Credit_limit);
        txtDebt = (TextView) findViewById(R.id.setting_txt_debt);
        txtDebtData = (TextView) findViewById(R.id.setting_txt_debt_date);

        reBank.setOnClickListener(this);
        reAccount.setOnClickListener(this);
        reType.setOnClickListener(this);
        reMoney.setOnClickListener(this);
        reCreditLimit.setOnClickListener(this);
        reDebt.setOnClickListener(this);
        reDebtData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(PaySettingActivity.this, UpDateAccountCommonActivity.class);
        if (v == reBank) {

        } else if (v == reAccount) {
            intent.putExtra("888", "AccountName");
            startActivity(intent);
        } else if (v == reMoney) {
            intent.putExtra("888", "AccountMoney");
            startActivity(intent);
        } else if (v == reCreditLimit) {
            intent.putExtra("888", "CreditLimit");
            startActivity(intent);
        } else if (v == reDebt) {
            intent.putExtra("888", "Debt");
            startActivity(intent);
        } else if (v == reDebtData) {

        }
    }
}
