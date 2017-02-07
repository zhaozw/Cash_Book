package star.liuwen.com.cash_books.Activity;

import android.view.View;
import android.widget.RelativeLayout;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;

/**
 * Created by liuwen on 2017/2/7.
 */
public class CodedLockActivity extends BaseActivity {
    private RelativeLayout reSettingCodeClock;

    @Override
    public int activityLayoutRes() {
        return R.layout.coded_lock_activity;
    }

    @Override
    public void initView() {
        setLeftImage(R.mipmap.fanhui_lan);
        setTitle(getString(R.string.setting_save_account));
        setLeftText(getString(R.string.back));
        setBackView();
        reSettingCodeClock = (RelativeLayout) findViewById(R.id.reSetting_code_clock);
    }


    public void toSwitchPush(View view) {

    }
}
