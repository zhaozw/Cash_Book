package star.liuwen.com.cash_books.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import star.liuwen.com.cash_books.Activity.PaymentActivity;
import star.liuwen.com.cash_books.Base.App;
import star.liuwen.com.cash_books.Base.BaseFragment;
import star.liuwen.com.cash_books.Base.Config;
import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.RxBus.RxBus;
import star.liuwen.com.cash_books.RxBus.RxBusResult;
import star.liuwen.com.cash_books.Utils.BitMapUtils;
import star.liuwen.com.cash_books.Utils.SharedPreferencesUtil;
import star.liuwen.com.cash_books.Utils.ToastUtils;
import star.liuwen.com.cash_books.View.CustomPopWindow;

/**
 * 钱包页面
 */
public class QianBaoFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mRyYuer, mRyCash, mRyChuxuka, mRyxinyKa, mRyzhifb, mRyjiechu, mRyjieru;
    private TextView tvYuer, tvCash, tvChuxuka, tvXinyKa, tvzhifb, tvjiechu, tvjieru;
    private CustomPopWindow mCustomPopWindow;
    private ImageView imageCash, imageCxk, imageXYk, imageZfb, imageJC, imageJR;
    private int position;
    private DrawerLayout mDrawerLayout;

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

        mDrawerLayout = (DrawerLayout) getContentView().findViewById(R.id.drawer_layout);

        mRyYuer.setOnClickListener(this);
        mRyCash.setOnClickListener(this);
        mRyChuxuka.setOnClickListener(this);
        mRyxinyKa.setOnClickListener(this);
        mRyzhifb.setOnClickListener(this);
        mRyjiechu.setOnClickListener(this);
        mRyjieru.setOnClickListener(this);

        if (!SharedPreferencesUtil.getStringPreferences(getActivity(), Config.ChangeBg, null).isEmpty()) {
            Bitmap bitmap = BitMapUtils.getBitmapByPath(getActivity(), SharedPreferencesUtil.getStringPreferences(getActivity(), Config.ChangeBg, null), false);
            mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
        }

    }

    private void initData() {
        RxBus.getInstance().toObserverableOnMainThread(Config.isBgCash, new RxBusResult() {
            @Override
            public void onRxBusResult(Object o) {
                Bitmap bitmap = BitMapUtils.getBitmapByPath(getActivity(), o.toString(), false);
                mDrawerLayout.setBackgroundDrawable(new BitmapDrawable(getResources(), bitmap));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);
        switch (v.getId()) {
            case R.id.qb_ry_yuer:
                showPopWindow();
                break;
            case R.id.qb_ry_cash:
                intent.putExtra("paySetting", "cash");
                startActivity(intent);
                break;
            case R.id.qb_ry_chuxuka:
                intent.putExtra("paySetting", "chuxuka");
                startActivity(intent);
                break;
            case R.id.qb_ry_xinyka:
                intent.putExtra("paySetting", "xinyaka");
                startActivity(intent);
                break;
            case R.id.qb_ry_zhifubao:
                intent.putExtra("paySetting", "zhifubao");
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

    private void showPopWindow() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_menu, null);
        //处理popWindow 显示内容
        handleLogic(contentView);

        imageCash = (ImageView) contentView.findViewById(R.id.image_1);
        imageCxk = (ImageView) contentView.findViewById(R.id.image_2);
        imageXYk = (ImageView) contentView.findViewById(R.id.image_3);
        imageZfb = (ImageView) contentView.findViewById(R.id.image_4);
        imageJC = (ImageView) contentView.findViewById(R.id.image_5);
        imageJR = (ImageView) contentView.findViewById(R.id.image_6);

        imageCash.setImageResource(App.isBgCash ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
        imageCxk.setImageResource(App.isBgCXK ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
        imageXYk.setImageResource(App.isBgXYK ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
        imageZfb.setImageResource(App.isBgZFB ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
        imageJC.setImageResource(App.isBgJC ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
        imageJR.setImageResource(App.isBgJR ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);

        //创建并显示popWindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(getActivity())
                .setView(contentView)
                .create()
                .showAsDropDown(mRyYuer, 0, 20);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.menu1:
                        position = 1;
                        setPopWindowBG(position);
                        break;
                    case R.id.menu2:
                        position = 2;
                        setPopWindowBG(position);
                        break;
                    case R.id.menu3:
                        setPopWindowBG(position);
                        position = 3;
                        break;
                    case R.id.menu4:
                        position = 4;
                        setPopWindowBG(position);
                        break;
                    case R.id.menu5:
                        position = 5;
                        setPopWindowBG(position);
                        break;
                    case R.id.menu6:
                        position = 6;
                        setPopWindowBG(position);
                }
            }
        };
        contentView.findViewById(R.id.menu1).setOnClickListener(listener);
        contentView.findViewById(R.id.menu2).setOnClickListener(listener);
        contentView.findViewById(R.id.menu3).setOnClickListener(listener);
        contentView.findViewById(R.id.menu4).setOnClickListener(listener);
        contentView.findViewById(R.id.menu5).setOnClickListener(listener);
        contentView.findViewById(R.id.menu6).setOnClickListener(listener);
    }

    public void setPopWindowBG(int position) {
        switch (position) {
            case 1:
                imageCash.setImageResource(!App.isBgCash ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
                App.isBgCash = !App.isBgCash;
                SharedPreferencesUtil.setBooleanPreferences(getActivity(), Config.isBgCash, App.isBgCash);
                break;
            case 2:
                imageCxk.setImageResource(!App.isBgCXK ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
                App.isBgCXK = !App.isBgCXK;
                SharedPreferencesUtil.setBooleanPreferences(getActivity(), Config.isBgCXK, App.isBgCXK);
                break;
            case 3:
                imageXYk.setImageResource(!App.isBgXYK ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
                App.isBgXYK = !App.isBgXYK;
                SharedPreferencesUtil.setBooleanPreferences(getActivity(), Config.isBgXYK, App.isBgXYK);
                break;
            case 4:
                imageZfb.setImageResource(!App.isBgZFB ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
                App.isBgZFB = !App.isBgZFB;
                SharedPreferencesUtil.setBooleanPreferences(getActivity(), Config.isBgZFB, App.isBgZFB);
                break;
            case 5:
                imageJC.setImageResource(!App.isBgJC ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
                App.isBgJC = !App.isBgJC;
                SharedPreferencesUtil.setBooleanPreferences(getActivity(), Config.isBgJC, App.isBgJC);
                break;
            case 6:
                imageJR.setImageResource(!App.isBgJR ? R.mipmap.btg_icon_tick_pressed : R.mipmap.btg_icon_priority_1_normal);
                App.isBgJR = !App.isBgJR;
                SharedPreferencesUtil.setBooleanPreferences(getActivity(), Config.isBgJR, App.isBgJR);
                break;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().release();
    }
}
