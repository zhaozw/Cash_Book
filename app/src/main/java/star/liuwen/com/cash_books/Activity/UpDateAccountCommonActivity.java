package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.CreditCardModel;

/**
 * Created by liuwen on 2017/1/10.
 * 修改账户余额 通用页面
 */
public class UpDateAccountCommonActivity extends BaseActivity {
    private EditText mEditText;
    private int position;
    private String AccountValues;


    @Override
    public int activityLayoutRes() {
        return R.layout.update_account_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftText(getString(R.string.pay_setting));
        setLeftImage(R.mipmap.fanhui_lan);
        mEditText = (EditText) findViewById(R.id.activity_update_account_money);
        setRightText(getString(R.string.save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSure(position);
            }
        });

        String values = getIntent().getStringExtra("888");
        AccountValues = getIntent().getStringExtra("Account");
        if (values.equals("AccountName")) {
            setTitle(getString(R.string.edit_account_name));
            mEditText.setHint("请输入修改名称");
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            position = 1;
        } else if (values.equals("AccountMoney")) {
            setTitle(getString(R.string.edit_account_money));
            position = 2;
        } else if (values.equals("CreditLimit")) {
            setTitle(getString(R.string.edit_credit_limit));
            mEditText.setHint("请输入信用卡额度");
            position = 3;
        } else if (values.equals("Debt")) {
            setTitle(getString(R.string.edit_debt));
            mEditText.setHint("请输入信用卡欠款");
            position = 4;
        }
    }


    private void onSure(int position) {

        String textInput = mEditText.getText().toString();
        if (TextUtils.isEmpty(textInput.trim())) {
            ToastUtils.showToast(this, "亲，输入的不能为空哟");
            return;
        }
        App.cardModel = new CreditCardModel();
        Intent intent = new Intent();
        switch (position) {
            case 1:
                App.cardModel.setAccountName(textInput);
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;

            case 2:
                App.cardModel.setMoney(textInput);
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;

            case 3:
                App.cardModel.setCardLimit(textInput);
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;

            case 4:
                App.cardModel.setDept(textInput);
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;
        }

    }

}
