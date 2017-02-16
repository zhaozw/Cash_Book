package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ApkInfoUtils;
import star.liuwen.com.cash_books.Utils.KeyboardUtil;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.CreditCardModel;

/**
 * Created by liuwen on 2017/1/10.
 * 修改账户余额 通用页面
 */
public class UpDateAccountCommonActivity extends BaseActivity {
    private EditText edMoney;
    private KeyboardUtil mKeyboardUtil;
    private int position;
    private boolean isShowInput;
    private KeyboardView mKeyBoardView;

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
        edMoney = (EditText) findViewById(R.id.update_common_money);
        mKeyBoardView = (KeyboardView) findViewById(R.id.keyboard_view);
        mKeyboardUtil = new KeyboardUtil(UpDateAccountCommonActivity.this, UpDateAccountCommonActivity.this, edMoney);

        String values = getIntent().getStringExtra(Config.SaveAccount);
        AccountValues = getIntent().getStringExtra("Account");
        if (values.equals("AccountName")) {
            setTitle(getString(R.string.edit_account_name));
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtAccountName, "").isEmpty() ? getString(R.string.edit_account_name) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtAccountName, ""));
            position = 1;
            isShowInput = false;
        } else if (values.equals("AccountMoney")) {
            setTitle(getString(R.string.edit_account_money));
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtAccountMoney, "").isEmpty() ? getString(R.string.ling) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtAccountMoney, ""));
            position = 2;
            isShowInput = true;
        } else if (values.equals("CreditLimit")) {
            setTitle(getString(R.string.edit_credit_limit));
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtCreditLimit, "").isEmpty() ? getString(R.string.ling) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtCreditLimit, ""));
            position = 3;
            isShowInput = true;
        } else if (values.equals("Debt")) {
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtDebt, "").isEmpty() ? getString(R.string.ling) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtDebt, ""));
            position = 4;
            isShowInput = true;

        }
        setListener(isShowInput);
    }


    private void setListener(boolean isShowInput) {
        if (isShowInput) {
            edMoney.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    edMoney.setInputType(InputType.TYPE_NULL);
                    mKeyboardUtil.showKeyboard();
                    edMoney.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    return true;
                }
            });
            edMoney.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            edMoney.setText(s);
                            edMoney.setSelection(s.length());
                        }
                    }
                    if (s.toString().trim().substring(0).equals(".")) {
                        s = "0" + s;
                        edMoney.setText(s);
                        edMoney.setSelection(2);
                    }

                    if (s.toString().startsWith("0")
                            && s.toString().trim().length() > 1) {
                        if (!s.toString().substring(1, 2).equals(".")) {
                            edMoney.setText(s.subSequence(0, 1));
                            edMoney.setSelection(1);
                            return;
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub

                }

            });
            mKeyboardUtil.setOnEnterListener(new KeyboardUtil.EnterListener() {
                @Override
                public void enter() {
                    onSure(position);
                }
            });
        } else {
            mKeyBoardView.setVisibility(View.GONE);
            edMoney.setInputType(InputType.TYPE_CLASS_TEXT);
            ApkInfoUtils.showSoftInput(this);

            setRightText(getString(R.string.finish), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSure(position);
                }
            });
        }

    }

    private void onSure(int position) {

        String textInput = edMoney.getText().toString();
        if (TextUtils.isEmpty(textInput)) {
            ToastUtils.showToast(this, "亲，输入的不能为空哟");
            return;
        }
        if (textInput.equals("0") || textInput.equals("0.00")) {
            ToastUtils.showToast(this, "亲，请输入大于0的数字");
            return;
        }
        Intent intent = new Intent();
        switch (position) {
            case 1:
                intent.putExtra(Config.TextInPut, textInput);
                setResult(0, intent);
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtAccountName, textInput);
                finish();
                break;
            case 2:
                intent.putExtra(Config.TextInPut, textInput);
                setResult(0, intent);
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtAccountMoney, textInput);
                finish();
                break;

            case 3:
                intent.putExtra(Config.TextInPut, textInput);
                setResult(0, intent);
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtCreditLimit, textInput);
                finish();
                break;
            case 4:
                intent.putExtra(Config.TextInPut, textInput);
                setResult(0, intent);
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtDebt, textInput);
                finish();
                break;
        }

    }

}
