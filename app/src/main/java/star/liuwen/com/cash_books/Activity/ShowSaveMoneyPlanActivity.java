package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.MainActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.View.NumberAnimTextView;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;
import star.liuwen.com.cash_books.bean.SaveMoneyPlanModel;

/**
 * Created by liuwen on 2017/2/15.
 */
public class ShowSaveMoneyPlanActivity extends BaseActivity implements BGAOnRVItemClickListener {
    private TextView txtName, txtTime, txtMoney, txtFinishPercent;
    private ImageView url;
    private RecyclerView mRecyclerView;
    private SeekBar mSeekBar;
    private SaveMoneyPlanAdapter mAdapter;
    private List<SaveMoneyPlanModel> mList;
    private NumberAnimTextView txtPercent;

    @Override
    public int activityLayoutRes() {
        return R.layout.show_save_money_plan_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setTitle(getString(R.string.save_money_plan));
        setLeftImage(R.mipmap.fanhui_lan);
        setLeftText(getString(R.string.back));

        txtName = (TextView) findViewById(R.id.show_money_txt_name);
        txtTime = (TextView) findViewById(R.id.show_money_txt_time);
        txtMoney = (TextView) findViewById(R.id.show_money_plan);
        txtPercent = (NumberAnimTextView) findViewById(R.id.show_money_precent);
        txtFinishPercent = (TextView) findViewById(R.id.show_money_finish_percent);

        url = (ImageView) findViewById(R.id.show_money_image_url);
        mSeekBar = (SeekBar) findViewById(R.id.id_seekbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.show_money_recyclerView);

        mList = new ArrayList<>();
        initData();

        PlanSaveMoneyModel model = (PlanSaveMoneyModel) getIntent().getExtras().getSerializable(Config.PlanSaveMoneyModel);
        if (model != null) {
            txtName.setText(model.getPlanName());
            url.setImageResource(model.getUrl());
        }

        mSeekBar.setProgressDrawable(ContextCompat.getDrawable(this, R.drawable.seek_bar_progress_bg));
        mSeekBar.setEnabled(false);
        mSeekBar.setClickable(false);
        mSeekBar.setProgress(30);


        mAdapter = new SaveMoneyPlanAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnRVItemClickListener(this);


        if (App.saveMoneyLists != null) {
            mList = App.saveMoneyLists;
            mAdapter.addMoreData(mList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setData(mList);
            mRecyclerView.setAdapter(mAdapter);
        }

        Bitmap bt = BitmapFactory.decodeFile(Config.RootPath + "TargetBg.jpg");
        if (bt != null) {
            url.setImageBitmap(bt);
        }
        txtTime.setText(SharedPreferencesUtil.getStringPreferences(this, Config.PlanFinishTime, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(this, Config.PlanFinishTime, ""));
        txtMoney.setText(SharedPreferencesUtil.getStringPreferences(this, Config.PlanMoney, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(this, Config.PlanMoney, ""));

    }

    private void initData() {
        RxBus.getInstance().toObserverableOnMainThread(Config.ModelSaveAPen, new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                mList = (List<SaveMoneyPlanModel>) o;
                mAdapter.addMoreData(mList);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().release();
    }

    public void Save(View view) {
        startActivity(new Intent(this, SaveAPenActivity.class));
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {

    }


    public class SaveMoneyPlanAdapter extends BGARecyclerViewAdapter<SaveMoneyPlanModel> {

        public SaveMoneyPlanAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_show_save_money_plan);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, SaveMoneyPlanModel model) {
            helper.setText(R.id.item_plan_platform, model.getPlatForm()).setText(R.id.item_plan_money, model.getSaveMoney() + "");
            helper.setText(R.id.item_plan_starttime, model.getStartTime());
            helper.setText(R.id.item_plan_benjin, model.getSaveMoney() + "").setText(R.id.item_plan_endtime, model.getEndTime());
            String startTime = model.getStartTime();
            String endTime = model.getEndTime();
            String[] str = startTime.split("-");
            String startTimes = str[0] + str[1] + str[2];
            String[] strs = endTime.split("-");
            String endTimes = strs[0] + strs[1] + strs[2];
            double lixi = (model.getSaveMoney() * model.getYield() * (Integer.parseInt(endTimes) - Integer.parseInt(startTimes))) / 365;
            helper.setText(R.id.item_plan_lixi, lixi + "").setText(R.id.item_plan_daishoulixi, lixi + "");
            // helper.setVisibility(R.id.item_re_show, View.GONE);
        }
    }
}
