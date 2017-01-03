package star.liuwen.com.cash_books.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Activity.PaymentActivity;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * 钱包页面
 */
public class QianBaoFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mRyYuer, mRyCash, mRyChuxuka, mRyxinyKa, mRyzhifb, mRyjiechu, mRyjieru;
    private TextView tvYuer, tvCash, tvChuxuka, tvXinyKa, tvzhifb, tvjiechu, tvjieru;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_qian_bao);
        setTitle(getString(R.string.qianbao_my));
        setRightText(getString(R.string.qianbao_zhuanzhang), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(getActivity(), "点击了转账");
            }
        });
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        mRyYuer = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_yuer);
        mRyCash = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_cash);
        mRyChuxuka = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_chuxuka);
        mRyxinyKa = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_xinyka);
        mRyzhifb = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_zhifubao);
        mRyjiechu = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_jiechu);
        mRyjieru = (RelativeLayout) getContentView().findViewById(R.id.qb_ry_jieru);

        tvYuer = (TextView) getContentView().findViewById(R.id.yuer_jia);
        tvCash = (TextView) getContentView().findViewById(R.id.cash_jia);
        tvChuxuka = (TextView) getContentView().findViewById(R.id.chuxuka_jia);
        tvXinyKa = (TextView) getContentView().findViewById(R.id.xinyka_jia);
        tvzhifb = (TextView) getContentView().findViewById(R.id.zhifb_jia);
        tvjiechu = (TextView) getContentView().findViewById(R.id.jiechu_jia);
        tvjieru = (TextView) getContentView().findViewById(R.id.jieru_jia);

        mRyYuer.setOnClickListener(this);
        mRyCash.setOnClickListener(this);
        mRyChuxuka.setOnClickListener(this);
        mRyxinyKa.setOnClickListener(this);
        mRyzhifb.setOnClickListener(this);
        mRyjiechu.setOnClickListener(this);
        mRyjieru.setOnClickListener(this);

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        switch (v.getId()) {
            case R.id.qb_ry_yuer:
                ToastUtils.showToast(getActivity(), "点击了余额");
                break;
            case R.id.qb_ry_cash:
                intent.putExtra("666", "cash");
                startActivity(intent);
                break;
            case R.id.qb_ry_chuxuka:
                intent.putExtra("666", "chuxuka");
                startActivity(intent);
                break;
            case R.id.qb_ry_xinyka:
                intent.putExtra("666", "xinyaka");
                startActivity(intent);
                break;
            case R.id.qb_ry_zhifubao:
                intent.putExtra("666", "zhifubao");
                startActivity(intent);
                break;
            case R.id.qb_ry_jiechu:
                ToastUtils.showToast(getActivity(), "点击了借出");
                break;
            case R.id.qb_ry_jieru:
                ToastUtils.showToast(getActivity(), "点击了借入");
                break;
        }
    }
}
