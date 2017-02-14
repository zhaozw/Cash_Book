package star.liuwen.com.cash_books.Enage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import star.liuwen.com.cash_books.R;
import star.liuwen.com.cash_books.bean.AccountModel;
import star.liuwen.com.cash_books.bean.ChoiceAccount;
import star.liuwen.com.cash_books.bean.IndexModel;
import star.liuwen.com.cash_books.bean.PlanSaveMoneyModel;
import star.liuwen.com.cash_books.bean.ReportsDetailModel;
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
        list.add(new PlanSaveMoneyModel("演唱会", "4987人已经加入", R.mipmap.yanchanghui, "加入 +", "跨越万水千山去看你，只为静静听你唱，那首贯穿我整个青春的歌"));
        list.add(new PlanSaveMoneyModel("比赛", "429人已经加入", R.mipmap.bisai, "加入 +", "输赢都是精彩，在我心中你永远是英雄"));
        list.add(new PlanSaveMoneyModel("买房装修", "20312人已经加入", R.mipmap.fang, "加入 +", "生活不止眼前的苟且，还有远方的诗和田野"));
        list.add(new PlanSaveMoneyModel("买车", "18929人已经加入", R.mipmap.che, "加入 +", "家,是你在这个城市扎下的根,是下班回家后橘黄色灯光的暖"));
        list.add(new PlanSaveMoneyModel("存下第一笔十万", "78551人已经加入", R.mipmap.shiwan, "加入 +", "不再做月光，是时候为自己的未来打算了"));
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


    public static List<ChoiceAccount> getShouRuData() {
        List<ChoiceAccount> list = new ArrayList<>();
        list.add(new ChoiceAccount(R.mipmap.zongxiaofei, "现金", 500));
        list.add(new ChoiceAccount(R.mipmap.icon_add_1, "储蓄卡", 12500));
        list.add(new ChoiceAccount(R.mipmap.huankuan, "信用卡", 97000));
        list.add(new ChoiceAccount(R.mipmap.zhifubao, "支付宝", 20000));
        return list;
    }

    public static List<IndexModel> getHuoBiData() {
        List<IndexModel> list = new ArrayList<>();
        list.add(new IndexModel("人民币", "CNY"));
        list.add(new IndexModel("美元", "USD"));
        list.add(new IndexModel("欧元", "EUR"));
        list.add(new IndexModel("日元", "JPY"));
        list.add(new IndexModel("英镑", "GBP"));
        list.add(new IndexModel("澳大利亚元", "AUD"));
        list.add(new IndexModel("加拿大元", "CAD"));
        list.add(new IndexModel("澳门元", "MOP"));
        list.add(new IndexModel("新西兰元", "NZD"));
        list.add(new IndexModel("泰铢", "THB"));
        list.add(new IndexModel("印度卢比", "INR"));
        list.add(new IndexModel("新加坡元", "SGD"));
        list.add(new IndexModel("韩元", "KRW"));
        list.add(new IndexModel("阿联酋迪拉姆", "AED"));
        list.add(new IndexModel("巴西雷亚尔", "BRL"));
        list.add(new IndexModel("瑞士法郎", "CHF"));
        list.add(new IndexModel("丹麦克朗", "DKK"));
        list.add(new IndexModel("埃及镑", "EGP"));
        list.add(new IndexModel("印度尼西亚盾", "IDR"));
        list.add(new IndexModel("柬埔寨瑞尔", "KHR"));
        list.add(new IndexModel("老挝基普", "LAk"));
        list.add(new IndexModel("斯里兰卡卢比", "LKR"));
        list.add(new IndexModel("马来西亚林吉特", "MYR"));
        list.add(new IndexModel("缅甸元", "MMK"));
        list.add(new IndexModel("马尔代夫卢非亚", "MVR"));
        list.add(new IndexModel("菲律宾比索", "PHP"));
        list.add(new IndexModel("卢布", "RUB"));
        list.add(new IndexModel("瑞典克朗", "SEK"));
        list.add(new IndexModel("越南盾", "YND"));
        list.add(new IndexModel("南非兰特", "ZAR"));
        list.add(new IndexModel("文莱元", "BND"));
        list.add(new IndexModel("巴基斯坦卢比", "PKR"));
        list.add(new IndexModel("乌克兰格里夫钠", "UAH"));
        list.add(new IndexModel("哥斯达黎家克朗", "CRC"));
        list.add(new IndexModel("保加利亚新列佛", "BGN"));
        list.add(new IndexModel("孟加拉国塔卡", "BDT"));
        list.add(new IndexModel("塔桑尼亚先令", "TZS"));
        list.add(new IndexModel("以色列谢克尔", "ILS"));
        return list;
    }

    public static List<AccountModel> getReportsData() {
        List<AccountModel> list = new ArrayList<>();
        list.add(new AccountModel("淘宝", R.mipmap.icon_zhichu_type_taobao, "1200", (float) ((1200 / 3870.3) * 100), "2017-2-14"));
        list.add(new AccountModel("医药教育", R.mipmap.icon_zhichu_type_yiliaojiaoyu, "220", (float) ((220 / 3870.3) * 100), "2017-2-13"));
        list.add(new AccountModel("餐饮", R.mipmap.xiaochi, "57", (float) ((57 / 3870.3) * 100), "2017-2-13"));
        list.add(new AccountModel("酒水", R.mipmap.jiushui, "101", (float) ((101 / 3870.3) * 100), "2017-2-13"));
        list.add(new AccountModel("衣服", R.mipmap.yifu, "210", (float) ((210 / 3870.3) * 100), "2017-2-12"));
        return list;
    }

}
