package star.liuwen.com.cash_books.Activity;

import android.support.v7.widget.RecyclerView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.ReportsDetailModel;

/**
 * Created by liuwen on 2017/2/7.
 */
public class ReportsDetailActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

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

        mRecyclerView = (RecyclerView) findViewById(R.id.reports_detail_recyclerView);

    }


    public class ReportsDetailAdapter extends BGARecyclerViewAdapter<ReportsDetailModel> {

        public ReportsDetailAdapter(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, ReportsDetailModel model) {

        }
    }
}
