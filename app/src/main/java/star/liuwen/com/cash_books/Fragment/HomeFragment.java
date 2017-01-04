package star.liuwen.com.cash_books.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Activity.CalendarActivity;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.HomeModel;

/**
 * 明细
 */
public class HomeFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private Button mButton;
    private HomeAdapter mAdapter;

    private List<Map<String, Object>> mList;
    private Map<String, Object> mMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_home);
        setTitle(getString(R.string.home_mingxi));
        setRightImage(R.mipmap.icon_zhichu_type_baoxiaozhang, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CalendarActivity.class));
            }
        });
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.f_h_recycler);
        mButton = (Button) getContentView().findViewById(R.id.f_h_bt);


        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setData(mList);
        mRecyclerView.setAdapter(mAdapter);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap = new HashMap<String, Object>();
                // 月-日
                SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
                // 时-分
                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                mMap.put("day", sdf.format(new Date()));
                mMap.put("time", sdf1.format(new Date()));
                mMap.put("content", new HomeModel(176f, 324f));

                mList.add(mMap);
                mAdapter.notifyDataSetChanged();
                Collections.reverse(mList);
            }
        });

    }

    private void initData() {

    }


    public class HomeAdapter extends BGARecyclerViewAdapter<Map<String, Object>> {

        public HomeAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_home);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, Map<String, Object> model) {

            helper.setText(R.id.item_h__txt_data, model.get("day").toString());
            helper.setText(R.id.item_h_txt_zhichu_time, model.get("time").toString());
            HomeModel home = (HomeModel) model.get("content");
            helper.setText(R.id.item_h_txt_shouru, home.getShouru() + ":收入");
            helper.setText(R.id.item_h_txt_zhichu, "支出" + home.getZhichu());

        }


    }

}


