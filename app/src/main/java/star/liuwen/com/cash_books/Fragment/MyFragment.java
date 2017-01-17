package star.liuwen.com.cash_books.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import star.liuwen.com.cash_books.Activity.RemindActivity;
import star.liuwen.com.cash_books.Activity.SaveMoneyActivity;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout reUserInfo, reJq, reCq, reHf, reZd, reDaoData, reTx, reSuggest, reSetting, reAbout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_my);
        setTitle(getString(R.string.my_more));
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        reUserInfo = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_userInfo);
        reJq = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_jieqian);
        reCq = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_cunqian);
        reHf = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_huanfu);
        reZd = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_zhangdan);
        reDaoData = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_daochushuju);
        reTx = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_tixing);
        reSuggest = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_suggest);
        reSetting = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_setting);
        reAbout = (RelativeLayout) getContentView().findViewById(R.id.re_f_my_about_us);

        reUserInfo.setOnClickListener(this);
        reJq.setOnClickListener(this);
        reCq.setOnClickListener(this);
        reHf.setOnClickListener(this);
        reZd.setOnClickListener(this);
        reDaoData.setOnClickListener(this);
        reTx.setOnClickListener(this);
        reSuggest.setOnClickListener(this);
        reSetting.setOnClickListener(this);
        reAbout.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), SaveMoneyActivity.class);
        if (v == reUserInfo) {

        } else if (v == reJq) {

        } else if (v == reCq) {
            intent.putExtra("plan", "cunqian");
            startActivity(intent);
        } else if (v == reHf) {

        } else if (v == reZd) {
            intent.putExtra("plan", "zhangdan");
            startActivity(intent);
        } else if (v == reHf) {

        } else if (v == reDaoData) {

        } else if (v == reTx) {
            startActivity(new Intent(getActivity(), RemindActivity.class));
        } else if (v == reSuggest) {

        } else if (v == reSetting) {

        } else if (v == reAbout) {

        }
    }
}
