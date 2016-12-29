package star.liuwen.com.cash_books.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;

/**
 * 钱包页面
 */
public class QianBaoFragment extends BaseFragment {


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
    }

    private void initData() {

    }

}
