package star.liuwen.com.cash_books.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Adapter.FragmentAdapter;
import star.liuwen.com.cash_books.Base.BaseActivity;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.Fragment.BankFragment;
import star.liuwen.com.cash_books.Fragment.P2PFragment;
import star.liuwen.com.cash_books.Fragment.ShouRuReportsFragment;
import star.liuwen.com.cash_books.Fragment.ZhiChuReportsFragment;
import star.liuwen.com.cash_books.MainActivity;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.AccountModel;
import star.liuwen.com.cash_books.bean.ReportsDetailModel;

/**
 * Created by liuwen on 2017/2/7.
 */
public class ReportsDetailActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private FragmentAdapter mFragmentAdapter;
    private ShouRuReportsFragment mShouRuReportsFragment;
    private ZhiChuReportsFragment mZhiChuReportsFragment;
    private ViewPager mViewPager;
    private TextView txtShouRu, txtZhiChu;


    @Override
    public int activityLayoutRes() {
        return R.layout.reports_detail_activity;
    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.id_view_pager);
        txtShouRu = (TextView) findViewById(R.id.id_shouru_tab);
        txtZhiChu = (TextView) findViewById(R.id.id_zhichu_tab);


        mShouRuReportsFragment = new ShouRuReportsFragment();
        mZhiChuReportsFragment = new ZhiChuReportsFragment();
        mFragmentAdapter = new FragmentAdapter(getFragmentManager());
        mFragmentAdapter.add(mZhiChuReportsFragment, "消费明细");
        mFragmentAdapter.add(mShouRuReportsFragment, "收入明细");
        mViewPager.setAdapter(mFragmentAdapter);

        mViewPager.setOnPageChangeListener(this);
        txtShouRu.setOnClickListener(this);
        txtZhiChu.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == txtZhiChu) {
            mViewPager.setCurrentItem(0);
        } else if (v == txtShouRu) {
            mViewPager.setCurrentItem(1);
        }

    }

    public void onClose(View view) {
        ReportsDetailActivity.this.finish();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switchTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void switchTab(int index) {
        txtShouRu.setTextColor(index == 0 ? getResources().getColor(R.color.white) : getResources().getColor(R.color.text_color_66));
        txtShouRu.setBackgroundResource(index == 0 ? R.drawable.btn_fullblue_left_shape : R.drawable.btn_lineblue_left_shape);
        txtZhiChu.setBackgroundResource(index == 0 ? R.drawable.btn_lineblue_right_shape : R.drawable.btn_fullblue_right_shape);
        txtZhiChu.setTextColor(index == 0 ? getResources().getColor(R.color.text_color_33) : getResources().getColor(R.color.white));
    }
}
