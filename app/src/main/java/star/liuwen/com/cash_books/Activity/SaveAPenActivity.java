package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;

/**
 * Created by liuwen on 2017/2/15.
 */
public class SaveAPenActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout reSavePlatform, reMoney, reYield, reAccount, reStartTime, reTime, reRemark;
    private TextView txtSavePlatform, txtMoney, txtYield, txtAccount, txtStartTime, txtTime, txtRemark;

    @Override

    public int activityLayoutRes() {
        return R.layout.save_a_pen_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftText(getString(R.string.back));
        setTitle(getString(R.string.save_a_pen));
        setLeftImage(R.mipmap.fanhui_lan);

        reSavePlatform = (RelativeLayout) findViewById(R.id.re_save_a_pen_platform);
        reMoney = (RelativeLayout) findViewById(R.id.re_save_a_pen_money);
        reYield = (RelativeLayout) findViewById(R.id.re_save_a_pen_yield);
        reAccount = (RelativeLayout) findViewById(R.id.re_save_a_pen_account);
        reStartTime = (RelativeLayout) findViewById(R.id.re_save_a_pen_start_time);
        reTime = (RelativeLayout) findViewById(R.id.re_save_a_pen_time);
        reRemark = (RelativeLayout) findViewById(R.id.re_save_a_pen_remark);

        txtSavePlatform = (TextView) findViewById(R.id.txt_save_a_pen_platform);
        txtMoney = (TextView) findViewById(R.id.txt_save_a_pen_money);
        txtYield = (TextView) findViewById(R.id.txt_save_a_pen_yield);
        txtAccount = (TextView) findViewById(R.id.txt_save_a_pen_account);
        txtStartTime = (TextView) findViewById(R.id.txt_save_a_pen_start_time);
        txtTime = (TextView) findViewById(R.id.txt_save_a_pen_time);
        txtRemark = (TextView) findViewById(R.id.txt_save_a_pen_remark);

        reSavePlatform.setOnClickListener(this);
        reMoney.setOnClickListener(this);
        reYield.setOnClickListener(this);
        reAccount.setOnClickListener(this);
        reStartTime.setOnClickListener(this);
        reTime.setOnClickListener(this);
        reRemark.setOnClickListener(this);

        txtSavePlatform.setText(SharedPreferencesUtil.getStringPreferences(this, Config.SaveAPenPlatform, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(this, Config.SaveAPenPlatform, "'"));
        txtMoney.setText(SharedPreferencesUtil.getStringPreferences(this, Config.TxtMoney, "").isEmpty() ? getString(R.string.ling) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtMoney, "'"));
        txtYield.setText(SharedPreferencesUtil.getStringPreferences(this, Config.TxtPercent, "").isEmpty() ? getString(R.string.ling) + "%" : SharedPreferencesUtil.getStringPreferences(this, Config.TxtPercent, "'") + "%");
        txtRemark.setText(SharedPreferencesUtil.getStringPreferences(this, Config.TxtRemark, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(this, Config.TxtRemark, ""));

        initDate();
    }

    private void initDate() {
    }


    private static final int ReSavePlatForm = 101;
    private static final int ReMoney = 102;
    private static final int ReYield = 103;
    private static final int ReRemark = 104;


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SaveAPenActivity.this, UpdateCommonKeyBoardActivity.class);
        if (v == reSavePlatform) {
            startActivityForResult(new Intent(SaveAPenActivity.this, SavePlatformActivity.class), ReSavePlatForm);
        } else if (v == reMoney) {
            intent.putExtra(Config.SaveAPenPlatform, "reMoney");
            startActivityForResult(intent, ReMoney);
        } else if (v == reYield) {
            intent.putExtra(Config.SaveAPenPlatform, "reYield");
            startActivityForResult(intent, ReYield);
        } else if (v == reAccount) {

        } else if (v == reStartTime) {

        } else if (v == reTime) {

        } else if (v == reRemark) {
            intent.putExtra(Config.SaveAPenPlatform, "reRemark");
            startActivityForResult(intent, ReRemark);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case ReSavePlatForm:
                txtSavePlatform.setText(data.getExtras().getString("bank"));
                SharedPreferencesUtil.setStringPreferences(SaveAPenActivity.this, Config.SaveAPenPlatform, data.getExtras().getString("bank"));
                break;
            case ReMoney:
                txtMoney.setText(data.getExtras().getString(Config.TextInPut));
                break;

            case ReYield:
                txtYield.setText(data.getExtras().getString(Config.TextInPut) + "%");
                break;
            case ReRemark:
                txtRemark.setText(data.getExtras().getString(Config.TextInPut));
                break;

        }
    }
}
