package star.liuwen.com.cash_books.Activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.MainActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.ReportsDetailModel;

/**
 * Created by liuwen on 2017/2/7.
 */
public class ReportsDetailActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private ReportsDetailAdapter mAdapter;

    @Override
    public int activityLayoutRes() {
        return R.layout.reports_detail_activity;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.reports_detail));
        setBackView();
        setLeftImage(R.mipmap.fanhui_lan);
        setLeftText(getString(R.string.back));
        setRightText(getString(R.string.dao_excel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(ReportsDetailActivity.this, "该功能正在开发");
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.reports_detail_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ReportsDetailAdapter(mRecyclerView);
        mAdapter.setData(DataEnige.getReportsData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(BGADivider.newShapeDivider());

    }


    public class ReportsDetailAdapter extends BGARecyclerViewAdapter<ReportsDetailModel> {

        public ReportsDetailAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_reports_detail);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, ReportsDetailModel model) {
            helper.setImageResource(R.id.item_rd_image, model.getUrl());
            helper.setText(R.id.item_rd_txtName, model.getConsumeName());
            helper.setText(R.id.item_rd_percent, String.format("%.2f", model.getConsumePercent()) + "%");
            helper.setText(R.id.item_rd_money, model.getConsumeMoney() + "元");
        }
    }
}
