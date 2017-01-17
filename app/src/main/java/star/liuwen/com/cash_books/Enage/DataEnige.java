package star.liuwen.com.cash_books.Enage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.IndexModel;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;
import star.liuwen.com.cash_books.bean.ZhiChuModel;
import star.liuwen.com.cash_books.widget.CharacterParser;
import star.liuwen.com.cash_books.widget.PinyinComparator;

/**
 * Created by liuwen on 2017/1/5.
 */
public class DataEnige {


    public static List<ZhiChuModel> getZhiChuData() {
        List<ZhiChuModel> mList = new ArrayList<>();
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
        return mList;
    }

    public static List<PlanSaveMoneyModel> getPlanSaveMoneyData() {
        List<PlanSaveMoneyModel> list = new ArrayList<>();
        list.add(new PlanSaveMoneyModel("旅行", "48234人已经加入", R.mipmap.lvyou, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        list.add(new PlanSaveMoneyModel("演唱会", "4987人已经加入", R.mipmap.yanchanghui, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        list.add(new PlanSaveMoneyModel("比赛", "429人已经加入", R.mipmap.bisai, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        list.add(new PlanSaveMoneyModel("买房装修", "20312人已经加入", R.mipmap.fang, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        list.add(new PlanSaveMoneyModel("买车", "18929人已经加入", R.mipmap.che, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        list.add(new PlanSaveMoneyModel("存下第一笔十万", "78551人已经加入", R.mipmap.shiwan, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        return list;
    }


    public static List<IndexModel> getXykData() {
        List<IndexModel> data = new ArrayList<>();
        data.add(new IndexModel("北京农商银行"));
        data.add(new IndexModel("北京银行"));
        data.add(new IndexModel("成都工商银行"));
        data.add(new IndexModel("成都银行"));
        data.add(new IndexModel("长沙银行"));
        data.add(new IndexModel("重庆银行"));
        data.add(new IndexModel("大连银行"));
        data.add(new IndexModel("东莞银行"));
        data.add(new IndexModel("甘肃银行"));
        data.add(new IndexModel("广州银行"));
        data.add(new IndexModel("工商银行"));
        data.add(new IndexModel("广发银行"));
        data.add(new IndexModel("光大银行"));
        data.add(new IndexModel("杭州银行"));
        data.add(new IndexModel("河北银行"));
        data.add(new IndexModel("恒丰银行"));
        data.add(new IndexModel("恒生银行"));
        data.add(new IndexModel("华夏银行"));
        data.add(new IndexModel("吉林银行"));
        data.add(new IndexModel("江苏银行"));
        data.add(new IndexModel("建设银行"));
        data.add(new IndexModel("交通银行"));
        data.add(new IndexModel("兰州银行"));
        data.add(new IndexModel("民泰银行"));
        data.add(new IndexModel("民生银行"));
        data.add(new IndexModel("南京银行"));
        data.add(new IndexModel("内蒙古银行"));
        data.add(new IndexModel("宁波银行"));
        data.add(new IndexModel("宁夏银行"));
        data.add(new IndexModel("农商银行"));
        data.add(new IndexModel("农业银行"));
        data.add(new IndexModel("平安银行"));
        data.add(new IndexModel("浦东发展银行"));
        data.add(new IndexModel("齐鲁银行"));
        data.add(new IndexModel("青岛银行"));
        data.add(new IndexModel("青海银行"));
        data.add(new IndexModel("其他"));
        data.add(new IndexModel("上海农商银行"));
        data.add(new IndexModel("上海银行"));
        data.add(new IndexModel("天津银行"));
        data.add(new IndexModel("温州银行"));
        data.add(new IndexModel("兴业银行"));
        data.add(new IndexModel("邮政银行"));
        data.add(new IndexModel("浙商银行"));
        data.add(new IndexModel("中国银行"));
        data.add(new IndexModel("招商银行"));
        data.add(new IndexModel("中信银行"));
        PinyinComparator pinyinComparator = new PinyinComparator();
        CharacterParser characterParser = CharacterParser.getInstance();
        for (IndexModel indexModel : data) {
            indexModel.topc = characterParser.getSelling(indexModel.name).substring(0, 1).toUpperCase();
            if (indexModel.name.equals("重庆银行")) {
                indexModel.topc = "C";
            }
        }
        Collections.sort(data, pinyinComparator);
        return data;

    }


    public static List<IndexModel> getHeadBankData() {
        List<IndexModel> list = new ArrayList<>();
        list.add(new IndexModel("工商银行"));
        list.add(new IndexModel("建设银行"));
        list.add(new IndexModel("交通银行"));
        list.add(new IndexModel("农业银行"));
        list.add(new IndexModel("中国银行"));
        list.add(new IndexModel("招商银行"));
        list.add(new IndexModel("邮政储蓄"));
        list.add(new IndexModel("民生银行"));
        list.add(new IndexModel("兴业银行"));
        list.add(new IndexModel("中信银行"));
        list.add(new IndexModel("浦发银行"));
        list.add(new IndexModel("平安银行"));
        list.add(new IndexModel("广发银行"));
        list.add(new IndexModel("光大银行"));
        list.add(new IndexModel("其他"));
        return list;
    }
}
