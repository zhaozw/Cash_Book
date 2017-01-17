package star.liuwen.com.cash_books.Fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.Utils.DateTimeUtil;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.bean.AccountModel;
import star.liuwen.com.cash_books.bean.ZhiChuModel;

/**
 * Created by liuwen on 2017/1/5.
 */
public class ShouRuFragment extends BaseFragment implements View.OnClickListener {

    private List<ZhiChuModel> mList;
    private RecyclerView mRecyclerView;
    private ShouRuAdapter mAdapter;
    private EditText edName;
    private ImageView imageName;
    private TextView txtName, tvData, tvZhanghu, tvSure, tvCashY, tvChukaY, tvXinYkaY, tvZfbY;
    private RelativeLayout ryCash, ryChuxuKa, ryXinyk, ryZfb;
    private int position, AccountUrl;
    private PopupWindow window;
    private List<Map<String, AccountModel>> homListData;
    private HashMap<String, AccountModel> mMap;
    private String AccountType, AccountData, AccountConsumeType;
    ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_shouru);
        initView();
        initData();
        return getContentView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) getContentView().findViewById(R.id.f_shouru_recycler);
        tvData = (TextView) getContentView().findViewById(R.id.f_shouru_data);
        tvZhanghu = (TextView) getContentView().findViewById(R.id.f_shouru_zhanghu);
        tvSure = (TextView) getContentView().findViewById(R.id.f_shouru_sure);

        tvData.setOnClickListener(this);
        tvZhanghu.setOnClickListener(this);
        tvSure.setOnClickListener(this);


        View headView = View.inflate(getActivity(), R.layout.zhichu_shouru_head, null);
        edName = (EditText) headView.findViewById(R.id.zhichu_name);
        imageName = (ImageView) headView.findViewById(R.id.imag_name);
        txtName = (TextView) headView.findViewById(R.id.txt_name);

        mAdapter = new ShouRuAdapter(mRecyclerView);
        mAdapter.addHeaderView(headView);

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 5, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mList = new ArrayList<>();
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_gongzi, "工资"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_shenghuofei, "生活费"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_hongbao, "红包"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_linghuaqian, "零花钱"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_jianzhiwaikuai, "外快兼职"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_touzishouru, "投资收入"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_jiangjin, "奖金"));
        mList.add(new ZhiChuModel(R.mipmap.icon_zhichu_type_baoxiaozhang, "报销"));
        mList.add(new ZhiChuModel(R.mipmap.xianjin, "现金"));
        mList.add(new ZhiChuModel(R.mipmap.tuikuan, "退款"));
        mList.add(new ZhiChuModel(R.mipmap.zhifubao, "支付宝"));
        mList.add(new ZhiChuModel(R.mipmap.icon_shouru_type_qita, "其他"));
        mAdapter.setData(mList);
        mAdapter.addLastItem(new ZhiChuModel(R.mipmap.icon_add, "更多"));
        mRecyclerView.setAdapter(mAdapter.getHeaderAndFooterAdapter());
        mAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {

                if (mAdapter.getItemCount() - 1 == position) {
                    ToastUtils.showToast(getActivity(), "改功能正在完善中");
                } else {
                    txtName.setText(mList.get(position).getName());
                    imageName.setImageResource(mList.get(position).getUrl());
                    AccountConsumeType = mList.get(position).getName();
                    AccountUrl = mList.get(position).getUrl();
                }

            }
        });
    }

    private void initData() {

    }


    @Override
    public void onClick(View v) {
        if (v == edName) {

        } else if (v == tvZhanghu) {
            showZhanghu();
        } else if (v == tvData) {
            showData();
        } else if (v == tvSure) {
            doSure();
        } else if (v == ryCash) {
            position = 0;
            setZhanghuText(position);
            window.dismiss();
        } else if (v == ryChuxuKa) {
            position = 1;
            setZhanghuText(position);
            window.dismiss();
        } else if (v == ryXinyk) {
            position = 2;
            setZhanghuText(position);
            window.dismiss();
        } else if (v == ryZfb) {
            position = 3;
            setZhanghuText(position);
            window.dismiss();
        }
    }

    private void doSure() {
        homListData = new ArrayList<>();
        mMap = new HashMap<>();
        String mEdName = edName.getText().toString();
        if (TextUtils.isEmpty(mEdName.trim())) {
            ToastUtils.showToast(getActivity(), "请输入金额");
            return;
        }
        if (tvZhanghu.getText().toString().equals("账户")) {
            ToastUtils.showToast(getActivity(), "请选择账户");
            return;
        }

        if (tvData.getText().toString().equals("选择日期")) {
            ToastUtils.showToast(getActivity(), "请选择日期");
        }

        mMap.put("AccountModel", new AccountModel(AccountType, AccountData, mEdName, AccountConsumeType, AccountUrl, DateTimeUtil.getCurrentTimeMinSec(), AccountModel.AccountType.shouRu));


        homListData.add(mMap);
        App.accountMaps = homListData;
        RxBus.getInstance().post("AccountModel", homListData);
        getActivity().finish();
    }

    public void setZhanghuText(int position) {
        switch (position) {
            case 0:
                tvZhanghu.setText("现金");
                AccountType = "现金";
                break;
            case 1:
                tvZhanghu.setText("储蓄卡");
                AccountType = "储蓄卡";
                break;
            case 2:
                tvZhanghu.setText("信用卡");
                AccountType = "信用卡";
                break;
            case 3:
                tvZhanghu.setText("支付宝");
                AccountType = "支付宝";
                break;
            default:
        }
    }


    private void showZhanghu() {
        View popView = View.inflate(getActivity(), R.layout.pop_zhanghu_dialog, null);
        RelativeLayout re = (RelativeLayout) popView.findViewById(R.id.layout_re);

        window = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        window.setBackgroundDrawable(new BitmapDrawable());
        window.setFocusable(true);
        window.setOutsideTouchable(true);

        //设置点击屏幕外PopWindon消失
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (window.isShowing()) {
                    window.dismiss();
                }
            }
        });

        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        window.showAtLocation(tvZhanghu, Gravity.BOTTOM, 0, 0);

        ryCash = (RelativeLayout) popView.findViewById(R.id.layout_choose_cash);
        ryChuxuKa = (RelativeLayout) popView.findViewById(R.id.layout_choose_chuxuka);
        ryXinyk = (RelativeLayout) popView.findViewById(R.id.layout_choose_xinyka);
        ryZfb = (RelativeLayout) popView.findViewById(R.id.layout_choose_zfb);

        tvCashY = (TextView) popView.findViewById(R.id.layout_cash_y);
        tvChukaY = (TextView) popView.findViewById(R.id.layout_chuxuKa_y);
        tvXinYkaY = (TextView) popView.findViewById(R.id.layout_xinyKa_y);
        tvZfbY = (TextView) popView.findViewById(R.id.layout_zfb_y);


        ryCash.setOnClickListener(this);
        ryChuxuKa.setOnClickListener(this);
        ryXinyk.setOnClickListener(this);
        ryZfb.setOnClickListener(this);

    }


    private void showData() {
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.show();
        DatePicker picker = new DatePicker(getActivity());
        picker.setDate(Integer.parseInt(DateTimeUtil.getShowCurrentTime()[0]), Integer.parseInt(DateTimeUtil.getShowCurrentTime()[1]));
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                tvData.setText(date);
                AccountData = date;
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }


    public class ShouRuAdapter extends BGARecyclerViewAdapter<ZhiChuModel> {

        public ShouRuAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_zhichu_fragment);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, ZhiChuModel model) {
            helper.setImageResource(R.id.item_imag, model.getUrl());
            helper.setText(R.id.item_name, model.getName());
        }
    }
}
