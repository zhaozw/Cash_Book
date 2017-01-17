package star.liuwen.com.cash_books.Activity;

import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;

/**
 * Created by liuwen on 2017/1/17.
 */
public class RemindActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImageView;
    private RelativeLayout rlRemindTime, rlRemindCycle;
    private TextView txtRemindTime, txtRemindCycle;
    private PopupWindow window;


    @Override
    public int activityLayoutRes() {
        return R.layout.remind_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftImage(R.mipmap.fanhui_lan);
        setTitle(getString(R.string.remind));
        setLeftText(getString(R.string.back));
        setRightText(getString(R.string.finish), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mImageView = (ImageView) findViewById(R.id.remind_push);
        rlRemindTime = (RelativeLayout) findViewById(R.id.remind_time);
        rlRemindCycle = (RelativeLayout) findViewById(R.id.remind_cycle);
        txtRemindCycle = (TextView) findViewById(R.id.remind_cycle_show);
        txtRemindTime = (TextView) findViewById(R.id.remind_time_show);

        mImageView.setImageResource(App.isRemindPush ? R.mipmap.more_push_on : R.mipmap.more_push_off);
        rlRemindCycle.setVisibility(App.isRemindPush ? View.VISIBLE : View.GONE);
        rlRemindTime.setVisibility(App.isRemindPush ? View.VISIBLE : View.GONE);

        rlRemindCycle.setOnClickListener(this);
        rlRemindTime.setOnClickListener(this);
    }


    public void toSwitchPush(View view) {
        mImageView.setImageResource(!App.isRemindPush ? R.mipmap.more_push_on : R.mipmap.more_push_off);
        rlRemindCycle.setVisibility(!App.isRemindPush ? View.VISIBLE : View.GONE);
        rlRemindTime.setVisibility(!App.isRemindPush ? View.VISIBLE : View.GONE);
        App.isRemindPush = !App.isRemindPush;
        SharedPreferencesUtil.setBooleanPreferences(this, Config.isRemindPush, App.isRemindPush);
    }

    @Override
    public void onClick(View v) {
        if (v == rlRemindCycle) {
            showPopWindowCycle();
        } else if (v == rlRemindTime) {

        }
    }

    private void showPopWindowCycle() {
        View popView = View.inflate(this, R.layout.pop_remind_cycle, null);
        RelativeLayout re = (RelativeLayout) popView.findViewById(R.id.layout_re);

        window = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(true);
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()) {
                    window.dismiss();
                }
            }
        });
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(rlRemindCycle, Gravity.BOTTOM, 0, 0);


    }
}
