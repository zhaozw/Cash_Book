package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.MainActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;

/**
 * Created by liuwen on 2017/2/15.
 */
public class ShowSaveMoneyPlanActivity extends BaseActivity {
    private TextView txtName, txtTime, txtPercent, txtMoney, txtFinishPercent;
    private ImageView url;
    private RecyclerView mRecyclerView;

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
        txtPercent = (TextView) findViewById(R.id.show_money_precent);
        txtFinishPercent = (TextView) findViewById(R.id.show_money_finish_percent);

        url = (ImageView) findViewById(R.id.show_money_image_url);

        mRecyclerView = (RecyclerView) findViewById(R.id.show_money_recyclerView);

        PlanSaveMoneyModel model = (PlanSaveMoneyModel) getIntent().getExtras().getSerializable(Config.PlanSaveMoneyModel);
        if (model != null) {
            txtName.setText(model.getPlanName());
            url.setImageResource(model.getUrl());
        }
        Bitmap bt = BitmapFactory.decodeFile(Config.RootPath + "TargetBg.jpg");
        if (bt != null) {
            url.setImageBitmap(bt);
        }
        txtTime.setText(SharedPreferencesUtil.getStringPreferences(this, Config.PlanFinishTime, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(this, Config.PlanFinishTime, ""));
        txtMoney.setText(SharedPreferencesUtil.getStringPreferences(this, Config.PlanMoney, "").isEmpty() ? getString(R.string.no_setting) : SharedPreferencesUtil.getStringPreferences(this, Config.PlanMoney, ""));

    }


    public void Save(View view) {
        startActivity(new Intent(this, SaveAPenActivity.class));
    }
}
