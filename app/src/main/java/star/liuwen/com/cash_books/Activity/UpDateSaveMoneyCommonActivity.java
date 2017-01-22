package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * Created by liuwen on 2017/1/21.
 */
public class UpDateSaveMoneyCommonActivity extends BaseActivity {
    private EditText mEditText;
    private int position;

    @Override
    public int activityLayoutRes() {
        return R.layout.update_save_money_common_activity;
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

        String values = getIntent().getStringExtra("UpdateSaveMoney");
        if (values.equals("ReMoney")) {
            setTitle(getString(R.string.edit_money));
            mEditText.setHint(getString(R.string.edit_input_money));
            position = 1;
        } else if (values.equals("ReMark")) {
            setTitle(getString(R.string.edit_remark));
            mEditText.setHint(getString(R.string.edit_input_remark));
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            position = 2;
            //处理编辑个人信息结果 因为不用重复写修改界面
        } else if (values.equals("reNickName")) {
            setTitle(getString(R.string.edit_renickName));
            mEditText.setHint(getString(R.string.edit_input_renickName));
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            position = 3;
        } else if (values.equals("reSignature")) {
            setTitle(getString(R.string.edit_reSignature));
            mEditText.setHint(getString(R.string.edit_input_reSignature));
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            position = 4;
        }
    }

    private void onSure(int position) {
        String textInput = mEditText.getText().toString();
        if (TextUtils.isEmpty(textInput.trim())) {
            ToastUtils.showToast(this, "亲，输入的不能为空哟");
            return;
        }
        Intent intent = new Intent();
        switch (position) {
            case 1:
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;
            case 2:
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;
            case 3:
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;
            case 4:
                intent.putExtra("textInput", textInput);
                setResult(0, intent);
                finish();
                break;
        }
    }
}
