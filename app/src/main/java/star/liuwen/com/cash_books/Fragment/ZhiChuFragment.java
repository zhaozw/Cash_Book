package star.liuwen.com.cash_books.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Enage.DataEnige;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.ZhiChuModel;

/**
 * Created by liuwen on 2017/1/5.
 */
public class ZhiChuFragment extends BaseFragment implements View.OnClickListener {

    private List<ZhiChuModel> mList;
    private RecyclerView mRecyclerView;
    private ZhiChuAdapter mAdapter;
    private EditText edName;
    private ImageView imageName;
    private TextView txtName;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_zhichu);
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.f_zhichu_recycler);

        View headView = View.inflate(getActivity(), R.layout.zhichu_shouru_head, null);
        edName = (EditText) headView.findViewById(R.id.zhichu_name);
        imageName = (ImageView) headView.findViewById(R.id.imag_name);
        txtName = (TextView) headView.findViewById(R.id.txt_name);

        mAdapter = new ZhiChuAdapter(mRecyclerView);
        mAdapter.addHeaderView(headView);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 5, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mList = new ArrayList<>();
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_qita, "一般"));
        mList.add(new ZhiChuModel(R.mipmap.maicai, "买菜"));
        mList.add(new ZhiChuModel(R.mipmap.zaocan, "早餐"));
        mList.add(new ZhiChuModel(R.mipmap.zhongfan, "中饭"));
        mList.add(new ZhiChuModel(R.mipmap.xiaochi, "小吃"));
        mList.add(new ZhiChuModel(R.mipmap.naifen, "奶粉"));
        mList.add(new ZhiChuModel(R.mipmap.jiushui, "酒水"));
        mList.add(new ZhiChuModel(R.mipmap.lingshi, "零食"));
        mList.add(new ZhiChuModel(R.mipmap.richangyongpin, "生活品"));
        mList.add(new ZhiChuModel(R.mipmap.xiezi, "鞋子"));
        mList.add(new ZhiChuModel(R.mipmap.yaopinfei, "医药费"));
        mList.add(new ZhiChuModel(R.mipmap.yifu, "衣服"));
        mAdapter.setData(mList);
        mAdapter.addLastItem(new ZhiChuModel(R.mipmap.icon_add, "更多"));
        mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        mAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                if (mList.size() == position) {
                    ToastUtils.showToast(getActivity(), "更多");
                } else {
                    txtName.setText(mList.get(position).getName());
                    imageName.setImageResource(mList.get(position).getUrl());

                }

            }
        });

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        if (v == edName) {

        }
    }


    public class ZhiChuAdapter extends BGARecyclerViewAdapter<ZhiChuModel> {

        public ZhiChuAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_zhichu_fragment);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, ZhiChuModel model) {
            if (mList == null || position == mList.size()) {
                helper.setImageResource(R.id.item_imag, R.mipmap.icon_add);
                helper.setText(R.id.item_name, "添加");
            } else {
                helper.setImageResource(R.id.item_imag, model.getUrl());
                helper.setText(R.id.item_name, model.getName());
            }
        }
    }

}
