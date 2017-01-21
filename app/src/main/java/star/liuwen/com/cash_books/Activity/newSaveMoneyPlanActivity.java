package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;

import java.util.Date;

import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;

/**
 * Created by liuwen on 2017/1/19.
 */
public class newSaveMoneyPlanActivity extends BaseActivity implements View.OnClickListener {
    private ImageView imageUrl, imagePhoto;
    private RelativeLayout reMoney, reTime, reMark;
    private TextView txtMoney, txtTime, txtReMark, txtMubiao;
    private TimePickerView pvTime;

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

        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setRange(2017, 2055);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                txtTime.setText(DateTimeUtil.getYearMonthDay(date));

            }
        });

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(newSaveMoneyPlanActivity.this, UpDateSaveMoneyCommonActivity.class);
        if (v == reMoney) {
            intent.putExtra("UpdateSaveMoney", "ReMoney");
            startActivityForResult(intent, ReMoney);
        } else if (v == reTime) {
            pvTime.show();
        } else if (v == reMark) {
            intent.putExtra("UpdateSaveMoney", "ReMark");
            startActivityForResult(intent, ReMark);
        }
    }

    private static final int ReMoney = 101;
    private static final int ReMark = 103;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case ReMoney:
                txtMoney.setText(data.getExtras().getString("textInput"));
                break;
            case ReMark:
                txtReMark.setText(data.getExtras().getString("textInput"));
                break;
        }
    }
}
