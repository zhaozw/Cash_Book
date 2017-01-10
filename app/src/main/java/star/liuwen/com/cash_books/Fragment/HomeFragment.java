package star.liuwen.com.cash_books.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import star.liuwen.com.cash_books.Activity.CalendarActivity;
import star.liuwen.com.cash_books.Adapter.HomeAdapter;
import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.View.DefineBAGRefreshWithLoadView;
import star.liuwen.com.cash_books.bean.AccountModel;

/**
 * 明细
 */
public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;

    private List<Map<String, AccountModel>> mList;
    private TextView tvShouRu, tvZhiChu;

    private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;
    private BGARefreshLayout mBGARefreshLayout;

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
        setBgaRefreshLayout();
        return getContentView();
    }

    /**
     * 设置 BGARefreshLayout刷新和加载
     */
    private void setBgaRefreshLayout() {
        mDefineBAGRefreshWithLoadView = new DefineBAGRefreshWithLoadView(getActivity(), true, true);
        //设置刷新样式
        mBGARefreshLayout.setRefreshViewHolder(mDefineBAGRefreshWithLoadView);
        mDefineBAGRefreshWithLoadView.setRefreshingText("同步账单中...");
        mDefineBAGRefreshWithLoadView.setPullDownRefreshText("同步账单中...");
        mDefineBAGRefreshWithLoadView.setReleaseRefreshText("下拉同步账单中...");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //避免内存溢出
        RxBus.getInstance().removeObserverable("AccountModel");
        RxBus.getInstance().release();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.f_h_recycler);
        mBGARefreshLayout = (BGARefreshLayout) getContentView().findViewById(R.id.define_bga_refresh_with_load);   //设置刷新和加载监听
        mBGARefreshLayout.setDelegate(this);

        View headView = View.inflate(getActivity(), R.layout.layout_head_home, null);
        tvShouRu = (TextView) headView.findViewById(R.id.home_shouru);
        tvZhiChu = (TextView) headView.findViewById(R.id.zhichu_name);

        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(mRecyclerView);
        mAdapter.addHeaderView(headView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (App.accountMaps != null) {
            mList = App.accountMaps;
            mAdapter.addNewData(mList);
            mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        } else {
            mAdapter.setData(mList);
            mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        }
    }

    private void initData() {

        RxBus.getInstance().toObserverableOnMainThread("AccountModel", new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                mList = (List<Map<String, AccountModel>>) o;
                mAdapter.addNewData(mList);
                mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
            }
        });

    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mDefineBAGRefreshWithLoadView.showLoadingMoreImg();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBGARefreshLayout.endRefreshing();
            }
        }, 1500);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}


