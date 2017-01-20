package star.liuwen.com.cash_books.Fragment;


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

import java.util.ArrayList;
import java.util.List;

import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.BitMapUtils;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.View.MagnificentChart;
import star.liuwen.com.cash_books.View.MagnificentChartItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportsFragment extends BaseFragment implements View.OnClickListener {
    private MagnificentChart mChart;
    private TextView txtChoiceData;
    private DrawerLayout mDrawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_reports);
        setTitle(getString(R.string.reports_my));
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        mChart = (MagnificentChart) getContentView().findViewById(R.id.f_re_magnificentChart);
        txtChoiceData = (TextView) getContentView().findViewById(R.id.f_re_data);
        mDrawerLayout = (DrawerLayout) getContentView().findViewById(R.id.drawer_layout);


        txtChoiceData.setOnClickListener(this);

        MagnificentChartItem firstItem = new MagnificentChartItem("first", 30, Color.parseColor("#BAF0A2"));
        MagnificentChartItem secondItem = new MagnificentChartItem("second", 12, Color.parseColor("#2F6994"));
        MagnificentChartItem thirdItem = new MagnificentChartItem("third", 3, Color.parseColor("#FF6600"));
        MagnificentChartItem fourthItem = new MagnificentChartItem("fourth", 41, Color.parseColor("#800080"));
        MagnificentChartItem fifthItem = new MagnificentChartItem("fifth", 14, Color.parseColor("#708090"));

        List<MagnificentChartItem> chartItemsList = new ArrayList<MagnificentChartItem>();
        chartItemsList.add(firstItem);
        chartItemsList.add(secondItem);
        chartItemsList.add(thirdItem);
        chartItemsList.add(fourthItem);
        chartItemsList.add(fifthItem);

        mChart.setChartItemsList(chartItemsList);
        mChart.setMaxValue(100);

        if (!SharedPreferencesUtil.getStringPreferences(getActivity(), Config.ChangeBg, null).isEmpty()) {
            Bitmap bitmap = BitMapUtils.getBitmapByPath(getActivity(), SharedPreferencesUtil.getStringPreferences(getActivity(), Config.ChangeBg, null), false);
            mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }
    }

    private void initData() {
        RxBus.getInstance().toObserverableOnMainThread(Config.isBgCash, new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                Bitmap bitmap = BitMapUtils.getBitmapByPath(getActivity(), o.toString(), false);
                mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_re_data:
                mChart.setAnimationSpeed(MagnificentChart.ANIMATION_SPEED_FAST);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().release();
    }
}
