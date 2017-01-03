package star.liuwen.com.cash_books.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.KeyboardUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * Created by liuwen on 2016/12/29.
 */
public class IncomeAndCostActivity extends BaseActivity {
    private EditText edMoney;
    private TextView txtType, txtAccount, txtData, txtNote;
    private LinearLayout lyShow, lySelect;


    @Override
    public int activityLayoutRes() {
        return R.layout.income_and_cost_activity;
    }

    @Override
    public void initView() {
        txtType = (TextView) findViewById(R.id.calendar_txt_type);
        txtAccount = (TextView) findViewById(R.id.calendar_txt_account);
        txtData = (TextView) findViewById(R.id.calendar_txt_date);
        txtNote = (TextView) findViewById(R.id.calendar_txt_beizhu);
        lyShow = (LinearLayout) findViewById(R.id.ll_price);
        lySelect = (LinearLayout) findViewById(R.id.ll_price_select);
        final KeyboardUtil keyboardUtil = new KeyboardUtil(IncomeAndCostActivity.this, true);
        KeyboardUtil.closeInputMethod(IncomeAndCostActivity.this);
        edMoney = (EditText) findViewById(R.id.calendar_ed_money);
        edMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyboardUtil.attachTo(edMoney);
            }
        });

        keyboardUtil.setOnOkClick(new KeyboardUtil.OnOkClick() {
            @Override
            public void onOkClick() {
                if (validate()) {
                    lySelect.setVisibility(View.GONE);
                }
            }
        });


        keyboardUtil.setOnCancelClick(new KeyboardUtil.onCancelClick() {
            @Override
            public void onCancellClick() {
                lySelect.setVisibility(View.GONE);
            }
        });

        lyShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lySelect.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (lySelect.getVisibility() == View.VISIBLE) {
            lySelect.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }


    public boolean validate() {
        if (!(txtAccount.getText().toString().equals("支付宝") || txtAccount.getText().toString().equals("信用卡") || txtAccount.getText().toString().equals("储蓄卡") || txtAccount.getText().toString().equals("现金"))) {
            ToastUtils.showToast(IncomeAndCostActivity.this, "请选择账户类型");
            return false;
        }
        return true;
    }

}
