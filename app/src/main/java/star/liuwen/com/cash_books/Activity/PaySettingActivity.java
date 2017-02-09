package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * Created by liuwen on 2017/1/10.
 */
public class PaySettingActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout reBank, reAccount, reType, reMoney, reCreditLimit, reDebt, reDebtData;
    private TextView txtBank, txtAccount, txtType, txtMoney, txtCreditLimit, txtDebt, txtDebtData;
    private String AccountValues;


    @Override
    public int activityLayoutRes() {
        return R.layout.pay_setting_activity;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.pay_setting));
        setBackView();
        setLeftText(getString(R.string.back));
        setLeftImage(R.mipmap.fanhui_lan);

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

        AccountValues = getIntent().getStringExtra(Config.AccountSetting);
        if (AccountValues.equals("cash")) {
            reBank.setVisibility(View.GONE);
            setAccountVisible();
            txtType.setText(getString(R.string.qianbao_cash));
        } else if (AccountValues.equals("zfb")) {
            reBank.setVisibility(View.GONE);
            setAccountVisible();
            txtType.setText(getString(R.string.qianbao_zhifb));
        } else if (AccountValues.equals("xyk")) {
            reBank.setVisibility(View.VISIBLE);
            reCreditLimit.setVisibility(View.VISIBLE);
            reDebt.setVisibility(View.VISIBLE);
            reDebt.setVisibility(View.VISIBLE);
            txtType.setText(getString(R.string.qianbao_xinyka));
        } else if (AccountValues.equals("cxk")) {
            setAccountVisible();
            txtType.setText(getString(R.string.qianbao_chuxuka));
        }

        if (App.cardModel != null) {
            txtAccount.setText(App.cardModel.getAccountName());
            txtCreditLimit.setText(App.cardModel.getCardLimit());
            txtMoney.setText(App.cardModel.getMoney());
            txtDebt.setText(App.cardModel.getDept());
        } else {
            txtAccount.setText(getString(R.string.no_setting));
            txtCreditLimit.setText(getString(R.string.no_setting));
            txtMoney.setText(getString(R.string.no_setting));
            txtDebt.setText(getString(R.string.no_setting));
        }

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(PaySettingActivity.this, UpDateAccountCommonActivity.class);
        intent.putExtra("Account", AccountValues);
        if (v == reBank) {
            startActivityForResult(new Intent(PaySettingActivity.this, ChoiceIssuingBankActivity.class), ChoiceIssuingBank);
        } else if (v == reAccount) {
            intent.putExtra("888", "AccountName");
            startActivityForResult(intent, ACCOUNT);
        } else if (v == reMoney) {
            intent.putExtra("888", "AccountMoney");
            startActivityForResult(intent, MONEY);
        } else if (v == reCreditLimit) {
            intent.putExtra("888", "CreditLimit");
            startActivityForResult(intent, CreditLimit);
        } else if (v == reDebt) {
            intent.putExtra("888", "Debt");
            startActivityForResult(intent, Debt);
        } else if (v == reDebtData) {

        }
    }

    public void setAccountVisible() {
        reCreditLimit.setVisibility(View.GONE);
        reDebt.setVisibility(View.GONE);
        reDebt.setVisibility(View.GONE);
    }

    private static final int ACCOUNT = 101;
    private static final int MONEY = 102;
    private static final int CreditLimit = 103;
    private static final int Debt = 104;
    private static final int ChoiceIssuingBank = 105;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case ACCOUNT:
                txtAccount.setText(data.getExtras().getString("textInput"));
                break;
            case MONEY:
                txtMoney.setText(data.getExtras().getString("textInput"));
                break;
            case CreditLimit:
                txtCreditLimit.setText(data.getExtras().getString("textInput"));
                break;
            case Debt:
                txtDebt.setText(data.getExtras().getString("textInput"));
                break;
            case ChoiceIssuingBank:
                txtBank.setText(data.getExtras().getString("bank"));
            default:
                break;
        }

    }
}
