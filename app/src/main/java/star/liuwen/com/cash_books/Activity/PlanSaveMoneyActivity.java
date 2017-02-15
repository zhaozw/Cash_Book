package star.liuwen.com.cash_books.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;

/**
 * Created by liuwen on 2017/1/13.
 */
public class PlanSaveMoneyActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private PlanSaveMoneyAdapter mAdapter;


    @Override
    public int activityLayoutRes() {
        return R.layout.plan_save_money_activity;
    }

    @Override
    public void initView() {
        setTitle(getString(R.string.save_money_target));
        setBackView();
        setLeftText(getString(R.string.back));
        setLeftImage(R.mipmap.fanhui_lan);
        setRightText(getString(R.string.me_setting), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.plan_save_money_recyclerView);
        mAdapter = new PlanSaveMoneyAdapter(mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setData(DataEnige.getPlanSaveMoneyData());
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                Intent intent = new Intent(PlanSaveMoneyActivity.this, newSaveMoneyPlanActivity.class);
                PlanSaveMoneyModel model = mAdapter.getItem(position);
                intent.putExtra(Config.PlanSaveMoneyModel, model);
                startActivity(intent);
            }
        });
        initData();
    }

    private void initData() {
        RxBus.getInstance().toObserverableOnMainThread(Config.Game, new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                boolean close = (boolean) o;
                if (close) {
                    PlanSaveMoneyActivity.this.finish();
                }
            }
        });
    }

    public class PlanSaveMoneyAdapter extends BGARecyclerViewAdapter<PlanSaveMoneyModel> {

        public PlanSaveMoneyAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_plan_save_money);
        }

        @Override
        protected void fillData(final BGAViewHolderHelper helper, int position, PlanSaveMoneyModel model) {
            helper.setText(R.id.item_plan_name, model.getPlanName()).setImageResource(R.id.item_url, model.getUrl())
                    .setText(R.id.item_message, model.getMessage()).setText(R.id.item_add, model.getAdd())
                    .setText(R.id.item_peopele_number, model.getPlanPeopleNumber());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().release();
    }
}
