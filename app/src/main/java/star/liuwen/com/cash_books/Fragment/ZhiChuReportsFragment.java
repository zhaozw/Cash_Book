package star.liuwen.com.cash_books.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.AccountModel;

/**
 * Created by liuwen on 2017/2/22.
 */
public class ZhiChuReportsFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private ReportsDetailAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.zhichu_reports_fragment);
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.zhichu_reports_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ReportsDetailAdapter(mRecyclerView);
        mAdapter.setData(DataEnige.getReportsData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(BGADivider.newShapeDivider());
    }

    private void initData() {

    }

    public class ReportsDetailAdapter extends BGARecyclerViewAdapter<AccountModel> {

        public ReportsDetailAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_reports_detail);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, AccountModel model) {
            if (needTitle(position)) {
                helper.setVisibility(R.id.item_data, View.VISIBLE);
                helper.setText(R.id.item_data, model.getData());
            } else {
                helper.setVisibility(R.id.item_data, View.GONE);
            }
            helper.setImageResource(R.id.item_rd_image, model.getUrl());
            helper.setText(R.id.item_rd_txtName, model.getConsumeType());
            helper.setText(R.id.item_rd_percent, String.format("%.2f", model.getConsumePercent()) + "%");
            helper.setText(R.id.item_rd_money, model.getMoney() + "元");

        }

        private boolean needTitle(int position) {
            //第一个是分类
            if (position == 0) {
                return true;
            }
            if (position < 0) {
                return false;
            }
            AccountModel currentModel = (AccountModel) getItem(position);
            AccountModel previousModel = (AccountModel) getItem(position - 1);
            if (currentModel == null || previousModel == null) {
                return false;
            }
            String currentData = currentModel.getData();
            String previousData = previousModel.getData();

            // 当前item分类名和上一个item分类名不同，则表示两item属于不同分类
            if (currentData.equals(previousData)) {
                return false;
            }
            return true;
        }
    }
}
