package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.BitMapUtils;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * Created by liuwen on 2017/2/7.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout reSaveAccount, reHuoBi, reCleanHunCun, reShare, reUpdate, reCleanAllData, rePopQq, rePopWeXin, rePopFriend, rePopSina, reCancel;
    private DrawerLayout mDrawerLayout;
    private PopupWindow window;

    @Override
    public int activityLayoutRes() {
        return R.layout.setting_activity;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.setting));
        setBackView();
        initDate();
        reSaveAccount = (RelativeLayout) findViewById(R.id.re_setting_save_account);
        reHuoBi = (RelativeLayout) findViewById(R.id.re_setting_huobi);
        reCleanHunCun = (RelativeLayout) findViewById(R.id.re_setting_hucun);
        reShare = (RelativeLayout) findViewById(R.id.re_setting_share);
        reUpdate = (RelativeLayout) findViewById(R.id.re_setting_update);
        reCleanAllData = (RelativeLayout) findViewById(R.id.re_setting_clean_all_data);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        reSaveAccount.setOnClickListener(this);
        reHuoBi.setOnClickListener(this);
        reCleanHunCun.setOnClickListener(this);
        reShare.setOnClickListener(this);
        reUpdate.setOnClickListener(this);
        reCleanAllData.setOnClickListener(this);

        if (SharedPreferencesUtil.getStringPreferences(this, Config.ChangeBg, null) != null) {
            Bitmap bitmap = BitMapUtils.getBitmapByPath(this, SharedPreferencesUtil.getStringPreferences(this, Config.ChangeBg, null), false);
            mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }

    }

    private void initDate() {

    }

    @Override
    public void onClick(View v) {
        if (v == reSaveAccount) {
            startActivity(new Intent(SettingActivity.this, CodedLockActivity.class));
        } else if (v == reHuoBi) {
            startActivity(new Intent(SettingActivity.this, HuoBiActivity.class));
        } else if (v == reCleanHunCun) {
            ToastUtils.showToast(this, "缓存已经清除");
        } else if (v == reShare) {
            showPopShare();
            if (window.isShowing()) {
                window.dismiss();
            } else {
                window.showAtLocation(reShare, Gravity.BOTTOM, 0, 0);
                backgroundAlpha(0.5f);
            }
        } else if (v == reUpdate) {
            ToastUtils.showToast(this, "该版本已经是最新版本");
        } else if (v == reCleanAllData) {

        } else if (v == rePopQq) {

        } else if (v == rePopFriend) {

        } else if (v == rePopSina) {

        } else if (v == rePopWeXin) {

        } else if (v == reCancel) {
            window.dismiss();
        }
    }

    private void showPopShare() {
        View popView = View.inflate(this, R.layout.pop_share, null);
        rePopQq = (RelativeLayout) popView.findViewById(R.id.re_pop_qq);
        rePopSina = (RelativeLayout) popView.findViewById(R.id.re_pop_sina);
        rePopWeXin = (RelativeLayout) popView.findViewById(R.id.re_pop_weixin);
        rePopFriend = (RelativeLayout) popView.findViewById(R.id.re_pop_weixin_friend);
        reCancel = (RelativeLayout) popView.findViewById(R.id.re_pop_share_cancel);

        rePopQq.setOnClickListener(this);
        rePopSina.setOnClickListener(this);
        rePopFriend.setOnClickListener(this);
        rePopWeXin.setOnClickListener(this);
        reCancel.setOnClickListener(this);

        window = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setAnimationStyle(R.style.AnimBottom);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });


    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

}
