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
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemLongClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import star.liuwen.com.cash_books.Activity.CalendarActivity;
import star.liuwen.com.cash_books.Adapter.HomeAdapter;
import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Dialog.TipAndEditDialog;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.View.DefineBAGRefreshWithLoadView;
import star.liuwen.com.cash_books.View.NumberAnimTextView;
import star.liuwen.com.cash_books.bean.AccountModel;

/**
 * 明细
 */
public class HomeFragment extends BaseFragment implements BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnRVItemClickListener, BGAOnRVItemLongClickListener {
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;

    private List<AccountModel> mList;
    private TextView tvShouRuMonth, tvZhiChuMonth;
    private NumberAnimTextView tvShouRuData, tvZhiChuData;

    private DefineBAGRefreshWithLoadView mDefineBAGRefreshWithLoadView = null;
    private BGARefreshLayout mBGARefreshLayout;

    private ViewStub mViewStub;
    private View headView;
    private double zhiChuAdd = 0, shouRuAdd = 0;

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

        mViewStub = (ViewStub) getContentView().findViewById(R.id.view_stub);
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.f_h_recycler);
        mBGARefreshLayout = (BGARefreshLayout) getContentView().findViewById(R.id.define_bga_refresh_with_load);   //设置刷新和加载监听
        mBGARefreshLayout.setDelegate(this);

        headView = View.inflate(getActivity(), R.layout.layout_head_home, null);
        tvShouRuMonth = (TextView) headView.findViewById(R.id.home_shouru_month);
        tvZhiChuMonth = (TextView) headView.findViewById(R.id.home_zhichu_month);
        tvShouRuData = (NumberAnimTextView) headView.findViewById(R.id.home_shouru_data);
        tvZhiChuData = (NumberAnimTextView) headView.findViewById(R.id.home_zhichu_data);
        tvShouRuMonth.setText(String.format("%s收入", DateTimeUtil.getCurrentMonth()));
        tvZhiChuMonth.setText(String.format("%s支出", DateTimeUtil.getCurrentMonth()));
        mList = new ArrayList<>();
        mAdapter = new HomeAdapter(mRecyclerView);
        mAdapter.addHeaderView(headView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (App.accountMaps != null) {
            mList = App.accountMaps;
            mAdapter.addNewData(mList);
            mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        } else {
            mViewStub.inflate();
            headView.setVisibility(View.GONE);
            mBGARefreshLayout.setVisibility(View.GONE);
            mAdapter.setData(mList);
            mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        }
        mAdapter.setOnRVItemClickListener(this);
        mAdapter.setOnRVItemLongClickListener(this);

    }

    private void initData() {

        RxBus.getInstance().toObserverableOnMainThread("AccountModel", new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                mList = (List<AccountModel>) o;
                mAdapter.addNewData(mList);
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).mAccountType.equals(AccountModel.AccountType.zhiChu)) {
                        zhiChuAdd = zhiChuAdd + mList.get(i).getMoney();
                    } else {
                        shouRuAdd = shouRuAdd + mList.get(i).getMoney();
                    }
                }
                tvShouRuData.setNumberString(String.format("%.2f", shouRuAdd));
                tvZhiChuData.setNumberString(String.format("%.2f", zhiChuAdd));
                mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
                mViewStub.setVisibility(View.GONE);
                headView.setVisibility(View.VISIBLE);
                mBGARefreshLayout.setVisibility(View.VISIBLE);
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

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {

    }

    @Override
    public boolean onRVItemLongClick(final ViewGroup parent, View itemView, final int position) {
        final TipAndEditDialog dialog = new TipAndEditDialog(getActivity(), "确定要删除吗?");
        dialog.show();
        dialog.setLeftText(getString(R.string.cancel));
        dialog.setLeftTextColor(getResources().getColor(R.color.jiechu));
        dialog.setRightText(getString(R.string.sure));
        dialog.setRightTextColor(getResources().getColor(R.color.blue));
        dialog.setListener(new TipAndEditDialog.ITipEndEditDialogListener() {
            @Override
            public void ClickLeft() {
                dialog.dismiss();
            }

            @Override
            public void ClickRight() {
                mAdapter.removeItem(position);
            }
        });
        return true;
    }
}


