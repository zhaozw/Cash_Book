package star.liuwen.com.cash_books.Activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.ZhiChuModel;

/**
 * Created by liuwen on 2017/1/17.
 */
public class RemindActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mImageView;
    private RelativeLayout rlRemindTime, rlRemindCycle;
    private TextView txtRemindTime, txtRemindCycle;
    private PopupWindow window;
    private ListView mListView;
    private RemindAdapter mAdapter;



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
            if (window.isShowing()) {
                window.dismiss();
            } else {
                window.showAtLocation(rlRemindCycle, Gravity.BOTTOM, 0, 0);
                backgroundAlpha(0.5f);
            }
        } else if (v == rlRemindTime) {
            showPopWindowTime();
        }
    }

    private void showPopWindowTime() {

    }

    private void showPopWindowCycle() {
        View popView = View.inflate(this, R.layout.pop_remind_cycle, null);
        mListView = (ListView) popView.findViewById(R.id.lv_popup_list);
        window = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setFocusable(true);
        window.setAnimationStyle(R.style.mypopwindow_anim_style);

        mAdapter = new RemindAdapter(this, R.layout.item_popwindow_remind);
        mAdapter.setData(DataEnige.getRemindData());
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(RemindActivity.this, mAdapter.getItem(position).getName());
            }
        });

        window.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.pop_bg));
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

    public class RemindAdapter extends BGAAdapterViewAdapter<ZhiChuModel> {

        public RemindAdapter(Context context, int itemLayoutId) {
            super(context, itemLayoutId);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, ZhiChuModel model) {
            helper.setText(R.id.tv_pop_week, model.getName()).setImageResource(R.id.image_pop, model.getUrl());
        }
    }
}
