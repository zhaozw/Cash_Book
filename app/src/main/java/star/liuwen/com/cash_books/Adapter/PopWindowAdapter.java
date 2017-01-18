package star.liuwen.com.cash_books.Adapter;

import android.content.Context;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.ChoiceAccount;

/**
 * Created by liuwen on 2017/1/18.
 */
public class PopWindowAdapter extends BGAAdapterViewAdapter<ChoiceAccount> {

    public PopWindowAdapter(Context context, int itemLayoutId) {
        super(context, itemLayoutId);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ChoiceAccount model) {
        helper.setText(R.id.choose_cash_txt, model.getAccountName()).setImageResource(R.id.choose_cash_img, model.getUrl())
                .setText(R.id.layout_cash_y, "余额(元)：" + model.getMoney());
    }
}