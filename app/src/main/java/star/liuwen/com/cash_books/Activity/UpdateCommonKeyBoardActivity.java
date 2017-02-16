package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.GraphicLock.AppUtil;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ApkInfoUtils;
import star.liuwen.com.cash_books.Utils.KeyboardUtil;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.SnackBarUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * Created by liuwen on 2017/2/16.
 */
public class UpdateCommonKeyBoardActivity extends BaseActivity {
    private EditText edMoney;
    private KeyboardUtil mKeyboardUtil;
    private int position;
    private boolean isShowInput;
    private KeyboardView mKeyBoardView;


    @Override
    public int activityLayoutRes() {
        return R.layout.update_common_keyboard_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftImage(R.mipmap.fanhui_lan);
        setLeftText(getString(R.string.back));
        edMoney = (EditText) findViewById(R.id.update_common_money);
        mKeyBoardView = (KeyboardView) findViewById(R.id.keyboard_view);
        mKeyboardUtil = new KeyboardUtil(UpdateCommonKeyBoardActivity.this, UpdateCommonKeyBoardActivity.this, edMoney);

        String values = getIntent().getStringExtra(Config.SaveAPenPlatform);
        if (values.equals("reMoney")) {
            setTitle(getString(R.string.edit_money));
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtMoney, "").isEmpty() ? getString(R.string.ling) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtMoney, ""));
            position = 1;
            isShowInput = true;
        } else if (values.equals("reYield")) {
            setTitle(getString(R.string.edit_percent));
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtPercent, "").isEmpty() ? getString(R.string.ling) + "%" : SharedPreferencesUtil.getStringPreferences(this, Config.TxtPercent, ""));
            position = 2;
            isShowInput = true;
        } else if (values.equals("reRemark")) {
            setTitle(getString(R.string.edit_remark));
            edMoney.setHint(SharedPreferencesUtil.getStringPreferences(this, Config.TxtRemark, "").isEmpty() ? getString(R.string.edit_remark) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtRemark, ""));
            position = 3;
            isShowInput = false;
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
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtMoney, textInput);
                finish();
                break;
            case 2:
                intent.putExtra(Config.TextInPut, textInput);
                setResult(0, intent);
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtPercent, textInput);
                finish();
                break;
            case 3:
                intent.putExtra(Config.TextInPut, textInput);
                setResult(0, intent);
                SharedPreferencesUtil.setStringPreferences(this, Config.TxtRemark, textInput);
                finish();
                break;
        }

    }


}
