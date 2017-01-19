package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;

/**
 * Created by liuwen on 2017/1/19.
 */
public class newSaveMoneyPlanActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageUrl, imagePhoto;
    private RelativeLayout reMoney, reTime, reMark;
    private TextView txtMoney, txtTime, txtReMark, txtMubiao;

    @Override
    public int activityLayoutRes() {
        return R.layout.new_save_money_plan_activity;
    }

    @Override
    public void initView() {
        setBackView();
        setLeftImage(R.mipmap.fanhui_lan);
        setLeftText(getString(R.string.back));
        setTitle("新建存钱计划");
        setRightText(getString(R.string.finish), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        PlanSaveMoneyModel model = (PlanSaveMoneyModel) getIntent().getExtras().getSerializable("PlanSaveMoneyModel");

        imageUrl = (ImageView) findViewById(R.id.new_money_image_url);
        imagePhoto = (ImageView) findViewById(R.id.new_money_photo);

        reMoney = (RelativeLayout) findViewById(R.id.re_new_money_mubiao);
        reTime = (RelativeLayout) findViewById(R.id.re_new_money_time);
        reMark = (RelativeLayout) findViewById(R.id.re_new_money_beizhu);

        txtMoney = (TextView) findViewById(R.id.new_money_txt_money);
        txtReMark = (TextView) findViewById(R.id.new_money_txt_beizhu);
        txtMubiao = (TextView) findViewById(R.id.new_money_txt_mubiao);
        txtTime = (TextView) findViewById(R.id.new_money_txt_time);

        reMoney.setOnClickListener(this);
        reTime.setOnClickListener(this);
        reMark.setOnClickListener(this);
        imagePhoto.setOnClickListener(this);

        imageUrl.setImageResource(model.getUrl());
        txtMubiao.setText(model.getPlanName());

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(newSaveMoneyPlanActivity.this, UpDateAccountCommonActivity.class);
        if (v == reMoney) {

        } else if (v == reTime) {


        } else if (v == reMark) {

        }
    }
}
