package star.liuwen.com.cash_books.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Map;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.AccountModel;

/**
 * Created by liuwen on 2017/1/9.
 */
public class HomeAdapter extends BGARecyclerViewAdapter<AccountModel> {

    public HomeAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_home);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, AccountModel model) {
        if (model.mAccountType == AccountModel.AccountType.zhiChu) {
            helper.setVisibility(R.id.re_shouru, View.GONE);
            helper.setText(R.id.item_h_txt_xiaofei_type, "消费类型：" + model.getAccountType());
            helper.setText(R.id.item_h__txt_data, model.getData());
            helper.setText(R.id.item_h_txt_zhichu_type, model.getConsumeType() + ":");
            helper.setText(R.id.item_h_txt_zhichu, model.getMoney() + "元");
            helper.setText(R.id.item_h_txt_zhichu_time, model.getTimeMinSec());
            helper.setImageResource(R.id.item_h_image_zhichu, model.getUrl());
        } else {
            helper.setVisibility(R.id.re_zhichu, View.GONE);
            helper.setText(R.id.item_h_txt_chucun_type, "存款类型：" + model.getAccountType());
            helper.setText(R.id.item_h__txt_data, model.getData());
            helper.setText(R.id.item_h_txt_shouru_type, model.getConsumeType() + ":");
            helper.setText(R.id.item_h_txt_shouru, model.getMoney() + "元");
            helper.setText(R.id.item_h_txt_shouru_time, model.getTimeMinSec());
            helper.setImageResource(R.id.item_h_image_shouru, model.getUrl());
        }
    }
}
