package star.liuwen.com.cash_books.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import star.liuwen.com.cash_books.Activity.ReportsDetailActivity;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.PieChart.OnDateChangedLinstener;
import star.liuwen.com.cash_books.PieChart.StatisticsView;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.BitMapUtils;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.AccountModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportsFragment extends Fragment implements OnDateChangedLinstener, StatisticsView.OnClickDetailListener {
    private StatisticsView mView;
    private int total = 100;
    private float[] moneys;
    private String[] types;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
//        setContentView(R.layout.fragment_reports);
//        setTitle(getString(R.string.reports_my));
//        initView();
//        initData();
//        return getContentView();
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_layout, container, false);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        types = new String[DataEnige.getReportsData().size()];
        moneys = new float[DataEnige.getReportsData().size()];
        for (int i = 0; i < DataEnige.getReportsData().size(); i++) {
            types[i] = DataEnige.getReportsData().get(i).getConsumeType();
            moneys[i] = Float.parseFloat(String.valueOf(DataEnige.getReportsData().get(i).getMoney()));
        }
        mView = new StatisticsView(getActivity(), moneys, total, types);
        mView.setCurrDate(year, month);
        mView.setDateChangedListener(this);
        mView.setDetailListener(this);
        return mView;
    }


    @Override
    public void onDateChanged(String startDate, String endDate) {
        ToastUtils.showToast(getActivity(), "点击了日期" + startDate + "--" + endDate);
    }

    @Override
    public void showDetail() {
        startActivity(new Intent(getActivity(), ReportsDetailActivity.class));
    }

//    private void initView() {
//        txtChoiceData = (TextView) getContentView().findViewById(R.id.f_re_data);
//        mDrawerLayout = (DrawerLayout) getContentView().findViewById(R.id.drawer_layout);
//
//        txtChoiceData.setOnClickListener(this);
//
//
//        if (SharedPreferencesUtil.getStringPreferences(getActivity(), Config.ChangeBg, null) != null) {
//            Bitmap bitmap = BitMapUtils.getBitmapByPath(getActivity(), SharedPreferencesUtil.getStringPreferences(getActivity(), Config.ChangeBg, null), false);
//            mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
//        }
//    }
//
//    private void initData() {
//        RxBus.getInstance().toObserverableOnMainThread(Config.isBgCash, new RxBusResult() {
//            @Override
//            public void onRxBusResult(Object o) {
//                Bitmap bitmap = BitMapUtils.getBitmapByPath(getActivity(), o.toString(), false);
//                mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
//            }
//        });
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.f_re_data:
//                break;
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        RxBus.getInstance().release();
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().release();
    }
}
