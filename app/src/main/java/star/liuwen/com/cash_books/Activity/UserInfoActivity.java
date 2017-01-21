package star.liuwen.com.cash_books.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Adapter.PopWindowAdapter;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;

/**
 * Created by liuwen on 2017/1/21.
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout reNickName, reSignature, reSex, reLocation, reOut, rePhoto, rePhotoChoice, rePhotoCancel, reMan, reWoman, reBaomi;
    private TextView txtNickName, txtSignature, txtSex, txtLocation;
    private ImageView url;
    private PopupWindow window;

    @Override
    public int activityLayoutRes() {
        return R.layout.user_info_activity;
    }

    @Override
    public void initView() {

        setBackView();
        setLeftImage(R.mipmap.fanhui_lan);
        setTitle(getString(R.string.user_ino));
        setLeftText(getString(R.string.back));

        reNickName = (RelativeLayout) findViewById(R.id.re_user_info_nickName);
        reSignature = (RelativeLayout) findViewById(R.id.re_user_info_signature);
        reSex = (RelativeLayout) findViewById(R.id.re_user_info_sex);
        reLocation = (RelativeLayout) findViewById(R.id.re_user_info_location);
        reOut = (RelativeLayout) findViewById(R.id.re_user_info_out);

        txtNickName = (TextView) findViewById(R.id.user_info_nickName);
        txtSignature = (TextView) findViewById(R.id.user_info_signature);
        txtSex = (TextView) findViewById(R.id.user_info_sex);
        txtLocation = (TextView) findViewById(R.id.user_info_location);

        url = (ImageView) findViewById(R.id.user_info_url);

        reNickName.setOnClickListener(this);
        reSex.setOnClickListener(this);
        reSignature.setOnClickListener(this);
        reLocation.setOnClickListener(this);
        reOut.setOnClickListener(this);
        url.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == reNickName) {

        } else if (v == reSignature) {

        } else if (v == reSex) {
            showPopWindowSex();
            if (window.isShowing()) {
                window.dismiss();
            } else {
                window.showAtLocation(reSex, Gravity.BOTTOM, 0, 0);
                backgroundAlpha(0.5f);
            }
        } else if (v == reLocation) {

        } else if (v == reOut) {

        } else if (v == rePhoto) {

        } else if (v == rePhotoChoice) {

        } else if (v == rePhotoCancel) {

        } else if (v == reMan) {
            txtSex.setText("男");
            window.dismiss();
        } else if (v == reWoman) {
            txtSex.setText("女");
            window.dismiss();
        } else if (v == reBaomi) {
            txtSex.setText("保密");
            window.dismiss();
        } else if (v == url) {
            showPopWindowPhoto();
            if (window.isShowing()) {
                window.dismiss();
            } else {
                window.showAtLocation(url, Gravity.BOTTOM, 0, 0);
                backgroundAlpha(0.5f);
            }
        }
    }

    private void showPopWindowSex() {
        View popView = View.inflate(this, R.layout.pop_user_sex, null);
        reMan = (RelativeLayout) popView.findViewById(R.id.re_pop_sex_man);
        reWoman = (RelativeLayout) popView.findViewById(R.id.re_pop_sex_woman);
        reBaomi = (RelativeLayout) popView.findViewById(R.id.re_pop_sex_baomi);

        reMan.setOnClickListener(this);
        reWoman.setOnClickListener(this);
        reBaomi.setOnClickListener(this);

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

    private void showPopWindowPhoto() {
        View popView = View.inflate(this, R.layout.pop_user_photo, null);
        rePhoto = (RelativeLayout) popView.findViewById(R.id.re_pop_photo);
        rePhotoChoice = (RelativeLayout) popView.findViewById(R.id.re_pop_photo_choice);
        rePhotoCancel = (RelativeLayout) popView.findViewById(R.id.re_pop_photo_cancel);

        rePhoto.setOnClickListener(this);
        rePhotoCancel.setOnClickListener(this);
        rePhotoChoice.setOnClickListener(this);

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
