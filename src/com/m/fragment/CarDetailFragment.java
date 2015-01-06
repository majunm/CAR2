package com.m.fragment;

import java.util.LinkedHashMap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.m.base.BaseFragment;
import com.m.car2.R;
import com.m.domain.CarInfo;
import com.m.util.Tools;

public class CarDetailFragment extends BaseFragment {
	/** 车的logo显示 */
	private ImageView carLogo;
	/** 详情介绍 */
	private TextView carDetailInfo;

	public int currentIndex = -1;

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setRetainInstance(true);// 设置true，表明在存储中保留fragment对象。
		} catch (Exception e) {
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = init();
		return v;
	}

	/** 初始化 */
	private View init() {
		View v = View.inflate(getActivity(), R.layout.car_item, null);
		findView(v);
		car200();
		return v;
	}

	/** 初始化控件 */
	private void findView(View v) {
		carLogo = (ImageView) v.findViewById(R.id.car_logo);
		carDetailInfo = (TextView) v.findViewById(R.id.car_detail_info);
		carStoryScorll = (ScrollView) v.findViewById(R.id.car_story_scroll);
		carStory = (TextView) v.findViewById(R.id.car_story);
		mainLayout = (LinearLayout) v.findViewById(R.id.car_main_linearlayout);
		englishName = (TextView) v.findViewById(R.id.car_english_name);
		chinaName = (TextView) v.findViewById(R.id.car_china_name);
		carDescription = (TextView) v.findViewById(R.id.car_detail);
		carDetailLayout = (LinearLayout) v.findViewById(R.id.car_detail_layout);
		carIconLayout = (LinearLayout) v.findViewById(R.id.car_icon);
		hint = (TextView) v.findViewById(R.id.car_hint);
		hint1 = (TextView) v.findViewById(R.id.car_hint_1);
		registerListener();
	}

	/** 注册监听 */
	private void registerListener() {
		carStory.setOnClickListener(this);
		carStoryScorll.setOnClickListener(this);
		mainLayout.setOnClickListener(this);
		carLogo.setOnClickListener(this);
		carDetailInfo.setOnClickListener(this);
	}

	private int currentPage = 0;

	/** 200个品牌 */
	private void car200() {
		Log.e("car", "CarDetailFragment -- run---------->");
		currentPage = currentIndex + 1;
		hint.setText(currentPage + "/140");
		if (currentIndex != -1) {
			if (map.get(currentIndex) == null) {
				map.put(currentIndex, new CarInfo(currentIndex));
			}
			carInfo = map.get(currentIndex);
			try {
				carLogo.setImageResource(carInfo.getResId());
				carDetailInfo.setText(carInfo.getText());
				carDetailInfo.setTextSize(25);
				carDetailInfo.setSelected(true);
				carDetailInfo.setTextColor(Color.parseColor("#74DCFF"));
				englishName.setText(carInfo.getEnglishName());
				chinaName.setText(carInfo.getChinaName());
				carDescription.setText(carInfo.getDescription());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static LinkedHashMap<Integer, CarInfo> map;

	/** 获取fragment */
	public static CarDetailFragment newInstance(int currentIndex) {
		CarDetailFragment f = new CarDetailFragment();
		f.setCurrentIndex(currentIndex);
		// CarInfo carInfo = map.get(currentIndex);
		// Bundle bundle = new Bundle();
		// bundle.putParcelable(PARCELABLE_KEY, carInfo);
		// f.setArguments(bundle);
		return f;
	}

	private Interpolator accelerator = new AccelerateInterpolator();
	private Interpolator decelerator = new DecelerateInterpolator();
	private ScrollView carStoryScorll;
	private TextView carStory;
	private LinearLayout mainLayout;
	/** 故事内容 */
	private String storyContent = null;

	private void flip(final int position) {
		runAnim(position);
	}

	/** 执行动画 */
	private void runAnim(final int position) {
		final View visibleView;
		final View invisibleView;
		if (mainLayout.getVisibility() == View.GONE) {
			visibleView = carStoryScorll;
			invisibleView = mainLayout;
		} else {
			visibleView = mainLayout;
			invisibleView = carStoryScorll;
		}
		ObjectAnimator visToInvis = ObjectAnimator.ofFloat(visibleView, "rotationY", 0f,
				90f);
		visToInvis.setDuration(500);
		visToInvis.setInterpolator(accelerator);
		final ObjectAnimator invisToVis = ObjectAnimator.ofFloat(invisibleView,
				"rotationY", -90f, 0f);
		invisToVis.setDuration(500);
		invisToVis.setInterpolator(decelerator);
		visToInvis.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator anim) {
				visibleView.setVisibility(View.GONE);
				invisToVis.start();
				invisibleView.setVisibility(View.VISIBLE);
				if (carStoryScorll.getVisibility() == View.VISIBLE) {
					try {
						hint1.setText(currentPage + "/140");
						if (storyContent == null) {
							Tools.showProgressDialog(getActivity(), "正在加载....");
							new Thread() {
								public void run() {
									storyContent = Tools
											.loadData(getActivity(), position);
									mHandler.sendEmptyMessage(0);
								}
							}.start();
						} else {
							fillStroyContent();
						}

					} catch (Exception e) {
						e.printStackTrace();
						carStory.setText("--");
					}
				}
			}
		});
		visToInvis.start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_story:
		case R.id.car_story_scroll:
		case R.id.car_main_linearlayout:
		case R.id.car_logo:
		case R.id.car_detail_info:
			flip(currentIndex);
			break;
		}
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			Tools.dismissProgressDialog();
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				fillStroyContent();
				break;
			default:
				break;
			}
		}
	};

	private void fillStroyContent() {
		carStory.setText(storyContent);
		carStory.setTextSize(18);
		// carStory.setTextColor(Color.parseColor("#666869"));
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.e("car", "CarDetailFragment -- onDestroy---------->");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.e("car", "CarDetailFragment -- onDetach---------->");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.e("car", "CarDetailFragment -- onPause()---------->");
	}

	public static String PARCELABLE_KEY = "hehe";
	private CarInfo carInfo;
	private TextView englishName;
	private TextView chinaName;
	/** 汽车简介 */
	private TextView carDescription;
	private LinearLayout carDetailLayout;
	private LinearLayout carIconLayout;
	/** 提示当前第几页 */
	private TextView hint;
	private TextView hint1;
	static {
		map = new LinkedHashMap<Integer, CarInfo>();
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(0, new CarInfo(R.drawable.a0, "劳斯莱斯", "Rolls-Royce", "劳斯莱斯",
				"劳斯莱斯以一个“贵族化”的汽车公司享誉全球，同时也是目前世界三大航空发动机生产商之一。"));
		map.put(1,
				new CarInfo(R.drawable.a1, "宾利", "Bentley", "宾利",
						"宾利(又译作本特利)(Bentley)轿车标志是以公司名的第一个字母“B”为主体，生出一对翅膀，似凌空翱翔的雄鹰，此标志一直沿用至今，过去曾用过一个展翅飞翔的“B”标志。"));
		map.put(2, new CarInfo(R.drawable.a2, "保时捷", "Porsche", "保时捷",
				"保时捷的英文车标引采用德国保时捷公司创始人费迪南德·保时捷的姓氏。图形车标采用公司所在地斯图加特市的盾形市徽。"));
		map.put(3,
				new CarInfo(
						R.drawable.a3,
						"法拉利",
						"Ferrari",
						"法拉利",
						"第一辆披着法拉利“红鬃烈马”标徽，在赛道上先驰得点的，是由125型赛车加以改良的166型SPYDER CORSA，搭配一九九二毫升直列六缸引擎，勇夺一九四八年意大利的塔卡-傅里欧(TARG FLORIO)及米里-麦利亚(MILLE MIGLIA)大赛的双项冠军，“法拉利”从此一举成名，并接连胜出超过五千场赛事的“跃马传奇”。"));
		map.put(4, new CarInfo(R.drawable.a4, "玛莎拉蒂", "Maserati", "玛莎拉蒂",
				"玛莎拉蒂是七十余年间生产了多辆传奇式赛车、跑车的意大利品牌，它在历史上各款车型制造过程中体现了精湛技艺和思维方面的独创性让人着迷。"));
		map.put(7,
				new CarInfo(
						R.drawable.a5,
						"兰博基尼",
						"Lamborghini",
						"兰博基尼",
						"兰博基尼的标志是一头浑身充满了力气，正准备向对手发动猛烈攻击的犟牛。据说兰博基尼本人就是这种不甘示弱的牛脾气，也体现了兰博基尼公司产品的特点，因为公司生产的汽车都是大功率、高速的运动型跑车。车头和车尾上的商标省去了公司名，只剩下一头犟牛。"));
		map.put(5,
				new CarInfo(
						R.drawable.a6,
						"奔驰",
						"Benz",
						"奔驰",
						"戴姆勒于1909年为三星标志申请专利权，但本茨则属于一个圆形徽章。奔驰的标志来源于戴姆勒给他妻子的信，他认为他画在家里房子上的这颗星会为他带来好运，这颗三叉星还象征着奔驰汽车公司向海陆空三个方向发展。1909年，戴姆勒先生为了纪念他的VELO型车大批量生产，将三叉星内的齿轮图案改为月桂枝，以示胜利，而标志内的“梅赛德斯”则取自埃米尔·耶利内克美丽女儿的名字。“梅赛德斯”在西班牙语中有幸运的含义，可惜这位美女于四十岁死于不幸的婚姻，而以她命名的做法却不胫而走。到了1916年，星形的标志与奔驰的名字终于合二为一，而随着这两家历史最悠久的汽车生产商的合并，厂方再次为商标申请专利权，而此圆环中的星形标志演变成今天的图案，一直沿用至今。并成为世界十大著名的商标之一。"));
		map.put(6,
				new CarInfo(
						R.drawable.a7,
						"宝马",
						"BMW",
						"宝马",
						"BMW，全称为巴伐利亚机械制造厂股份公司(德文：Bayerische Motoren Werke AG)，是德国一家世界知名的高档汽车和摩托车制造商，总部位于慕尼黑。BMW在中国大陆、香港与早年的台湾又常称为“宝马”。"));
		map.put(8,
				new CarInfo(
						R.drawable.a8,
						"迈巴赫",
						"Maybach",
						"迈巴赫",
						"迈巴赫(德文：Maybach)与迈巴赫引擎制造厂(德文：Maybach-Motorenbau GmbH)是一曾经在1921年到1940年间活跃于欧洲地区的德国超豪华汽车品牌与制造厂，车厂创始人卡尔·迈巴赫(Karl Maybach)的父亲威廉·迈巴赫(Wilhelm Maybach)曾担任戴姆勒发动机公司(今日戴姆勒·克莱斯勒集团前身)的首席技术总监，两厂渊源甚深。1997年戴姆勒·克莱斯勒集团在东京车展会场中展出一辆以Maybach为名的概念性超豪华四门轿车，正式让这个德国汽车品牌在销声匿迹多年后再次复活。"));
		map.put(9,
				new CarInfo(
						R.drawable.a9,
						"路斯特",
						"Lotusnyo",
						"路斯特",
						"路特斯(Lotus Cars，曾被译为“莲花汽车”)是世界著名的跑车与赛车生产商，总部设在英国诺福克郡(Norfolk)的Hethel，由柯林·查普曼(Colin Chapman)创立于1952年，旗下的跑车以纯粹的驾驶乐趣和轻量化的设计而著称。1996年，Lotus被马来西亚汽车制造商宝腾(Proton)收购，2011年6月，Lotus品牌正式进入中国，并发布其中文官方名称“路特斯”。"));

		map.put(10,
				new CarInfo(R.drawable.b0, "奥迪", "Audi", "奥迪",
						"奥迪轿车的标志为四个圆环，代表着合并前的四家公司。这些公司曾经是自行车、摩托车及小客车的 生产厂家。由于该公司原是由4家公司合并而成，因此每一环都是其中一个公司的象征。"));
		map.put(11,
				new CarInfo(
						R.drawable.b1,
						"悍马",
						"HUMMER",
						"悍马",
						"美国军方于七十年代末期，根据越战经验，发现需要新一代的轻型多用途军车。当时军方所要求的军用车需要符合高机动性、多用途、有轮(非履带式)，简称HMMWV(High Mobility Multi-purpose Wheeled Vehicle)。而Hummer正是取自HMMWV的昵称Humveer所译音而成HUMMER。"));
		map.put(12,
				new CarInfo(
						R.drawable.b2,
						"路虎",
						"Land Rover",
						"路虎",
						"路虎(Landrover)，曾在中国大陆翻译成陆虎(香港地区称为“越野路华”)，是世界著名的英国越野车品牌。今天，路虎公司是世界上唯一专门生产四驱车的公司。或许正是由于这一点，才使得路虎的价值--冒险、勇气和至尊，闪耀在其各款汽车中。"));
		map.put(13, new CarInfo(R.drawable.b3, "莲花", "LianHua", "莲花",
				"莲花汽车公司成立于1952年1月1日，是世界上著名的运动汽车生产厂家，与法拉利、保时捷一起并称为世界三大跑车制造商，在世界上享有盛誉。"));
		map.put(14, new CarInfo(R.drawable.b4, "阿尔法·罗密欧", "Alfa Romeo", "阿尔法·罗密欧",
				"阿尔法·罗密欧的风格如此特别，在现代的风格中带有历史的回归，暗示着始终的创新与技术完美，有着诗意的名字，就像一个梦中的精灵。"));
		map.put(15, new CarInfo(R.drawable.b5, "吉利", "GEELY", "吉利",
				"“椭圆”：象征地球，表示面向世界、走向国际化;椭圆在动态中是最稳定的，喻示及祝愿吉利的事业稳如磐石，在风雨中屹立不倒."));
		map.put(16,
				new CarInfo(
						R.drawable.b6,
						"英菲尼迪",
						"INFINITI",
						"英菲尼迪",
						"Infiniti(英菲尼迪)的椭圆形标志表现的是一条无限延伸的道路。椭圆曲线代表无限扩张之意，也象征着“全世界”;两条直线代表通往巅峰的道路，象征无尽的发展。Infiniti(英菲尼迪)的标志和名称象征着英菲尼迪人的一种永无止境的追求，那就是创造有全球竞争力的真正的豪华车用户体验和最高的客户满意度。"));
		map.put(17, new CarInfo(R.drawable.b7, "铃木", "SUZUKI", "铃木",
				"铃木标志图案中的“S”是“SUZUKI”的第一个大写字母，它给人以无穷力量 的感觉，象征无限发展的铃木汽车公司。"));
		map.put(18,
				new CarInfo(
						R.drawable.b8,
						"雪铁龙",
						"CITROEN",
						"雪铁龙",
						"雪铁龙(Citroën)汽车公司是法国第三大汽车公司，它创立于1915年，创始人是安德列·雪铁龙。主要产品是小客车和轻型载货车。雪铁龙公司总部设在法国巴黎。雇员总数为5万人左右，可年产汽车90万辆"));
		map.put(19,
				new CarInfo(
						R.drawable.b9,
						"长安",
						"CHANGAN",
						"长安",
						"重庆长安汽车股份有限公司，简称长安汽车或重庆长安，为中国长安汽车集团股份有限公司旗下的核心整车企业，1996年在深圳证券交易所上市，A股代码000625，B股代码200625。"));

		map.put(20,
				new CarInfo(
						R.drawable.c0,
						"长安商用",
						"----",
						"长安商用",
						"重庆长安汽车股份有限公司，简称长安汽车或重庆长安，为中国长安汽车集团股份有限公司旗下的核心整车企业，1996年在深圳证券交易所上市，A股代码000625，B股代码200625。"));
		map.put(21,
				new CarInfo(R.drawable.c1, "名爵", "MG", "名爵",
						"名爵(MG)，原本在英国是Morris汽车代理商，后来制造自己开发的订制版汽车，以双门敞蓬跑车闻名于汽车界。南京汽车集团有限公司旗下轿车品牌。"));
		map.put(22,
				new CarInfo(
						R.drawable.c2,
						"开瑞",
						"Karry",
						"开瑞",
						"开瑞品牌为奇瑞公司于2009年1月12日推出的全新高性能微型车品牌。开瑞定位于行业用户工作车、中小企业物流车、个体经营工具车、宜商宜家的多功能车，真正体现“奇瑞开瑞，大有可为，承载我生活”的品牌理念。并且给用户“更安心、更大度、更舒适、更全能、更通达”的最直接感受。"));
		map.put(23,
				new CarInfo(
						R.drawable.c3,
						"海马",
						"haima",
						"海马",
						"1988年，海南建省，海马建厂，开始了第一次创业。1998到2005的第二次创业中，海马与战略伙伴合作，走出了一条“开放、合作、学习、发展”的路子， 海马夯实了自我发展的基础。从2006年起，海马人开始了第三次创业。今天，海马已经拥有两个整车工厂，一个年产15万台的发动机工厂。更重要的是海马拥有了一批成熟的汽车产业人才队伍，拥有了自主的整车研发能力，拥有了自主的销售服务网络，自主的供应配套体系，拥有了超过30万用户的品牌信赖，拥有了自我生存，自我发展，以我为主去实现海马梦想的体系能力。"));
		map.put(24,
				new CarInfo(
						R.drawable.c4,
						"东风",
						"DongFeng",
						"东风",
						"东风汽车集团股份有限公司是东风汽车公司下属在香港联交所上市的控股子公司(股票代码：00489.HK)，是东风汽车公司主要的资本运作平台，也是中国汽车行业目前最大的上市公司。2006年3月6日，公司被纳入恒生中国企业指数，成为H股指数37只成份股中唯一代表中国汽车行业的上市公司。 公司注册地址：湖北省武汉市武汉经济技术开发区东风大道特一号。"));
		map.put(25,
				new CarInfo(
						R.drawable.c5,
						"吉利全球鹰",
						"GLEAGLE",
						"吉利全球鹰",
						"吉利“全球鹰”的品牌标识整体外廓为椭圆形，是图形中兼具动态和稳固特征的图形，并象征着全球化的背景，寓示吉利在全球市场的动态平稳的发展前景。椭圆形状呈犄角之势，意喻吉利开拓、奋进、忠诚和使命感。标识中间部分为吉利首字母“G”的变体，同时又是阿拉伯数字“6”形状，“6”在中国传统文化中含有“吉祥顺利”的寓意，全球鹰造型则昭示着在新的阶段，吉利正以全新的激情和姿态，蓄势待发，并在不断的自我雕琢中崭露头角。"));
		map.put(26,
				new CarInfo(
						R.drawable.c6,
						"日产",
						"NISSAN",
						"日产",
						"“NISSAN’是日语 “日产”两个字的罗马音形式，是日本产业的简称，其含义是 “以人和汽车的明天为目标”。日产标志是将NISSAN放在一个火红的太阳上，简明扼要地表明了公司名称，突出了所在国家的形象，这在汽车商标文化中独树一帜。1914年，由田建治郎等人创建的 “快进社”，于1934年改为日产汽车公司。日产公司生产的轿车品牌很多，有总统、公子、桂冠、地平线、西尔维亚、羚羊、王子、南风、紫罗兰和小太阳等。“NISSAN’是日语 “日产”两个字的罗马音形式，是日本产业的简称，其含义是 “以人和汽车的明天为引目标”。"));
		map.put(27,
				new CarInfo(
						R.drawable.c7,
						"丰田",
						"TOYOTA",
						"丰田",
						"丰田公司的三个椭圆的标志是从1990年初开始使用的。标志中的大椭圆代表地球，中间由两个椭圆垂直组合成一个T字，代表丰田公司。它象征丰田公司立足于未来，对未来的信心和雄心，还象征着丰田公司立足于顾客，对顾客的保证，象征着用户的心和汽车厂家的心是连在一起的， 具有相互信赖感，同时喻示着丰田的高超技术和革新潜力。"));
		map.put(28,
				new CarInfo(
						R.drawable.c8,
						"欧宝",
						"OPEL",
						"欧宝",
						"OPEL在中国大陆称为欧宝、在台湾称为欧普。德国欧宝公司是美国通用汽车公司的子公司，是通用公司在欧洲的一个窗口。它由Adan Opel，即亚当·欧宝(欧普)所创立，至今已有百多年历史。"));
		map.put(29,
				new CarInfo(
						R.drawable.c9,
						"捷豹",
						"Jaguar",
						"捷豹",
						"捷豹(JAGUAR)是英国轿车的一种名牌产品，商标为一只正在跳跃前扑的\"美洲豹\"雕塑，矫健勇猛，形神兼备，具有时代感与视觉冲击力，它既代表了公司的名称，又表现出向前奔驰的力量与速度，象征该车如美洲豹一样驰骋于世界各地。"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(30,
				new CarInfo(
						R.drawable.d0,
						"福特野马",
						"MUSTANG",
						"福特野马",
						"1962年，福特汽车公司开始研发了野马的第一辆概念车——野马型车。 它是一部发动机中置的两座跑车，为了纪念在二战中富有传奇色彩的北美P57型“野马战斗机，福特汽车将这辆跑车命名为“野马”。它的初次亮相是在1962年10月，赛车手丹.格尼(Dan Gurney)驾着它参加了在纽约举办的美国汽车大奖赛。"));
		map.put(31,
				new CarInfo(
						R.drawable.d1,
						"别克",
						"Buick",
						"别克",
						"别克(BUICK)商标中那形似“三颗子弹”的图案为其图形商标，它是别克分部的标志。它被安装在汽车散器格栅上。图中那三颗颜色不同(从左到右：红、白、蓝三种颜色)并依次排列在不同高度位置上的子弹，给人一种积极进取、不断攀登的感觉;它表示别克分部采用顶级技术，刃刃见锋;也表示别克分部培养的人才个个游刃有余，是无坚不摧、勇于登峰的勇士。自2002年起,别克(BUICK)的商标更换为简洁的形式。"));
		map.put(32,
				new CarInfo(
						R.drawable.d2,
						"沃尔沃",
						"Volvo",
						"沃尔沃",
						"1915年6月，“Volvo”名称首先出现在SKF一具滚珠轴承上，并正式于瑞典皇家专利与商标注册局注册成为商标。从那一天起，SKF公司出品的每一组汽车用滚珠与滚子轴承侧面，都打上了全新的Volvo标志。 在拉丁语中，“Volvere”是动词“roll”(滚动)的不定式，例如，带转轮的手枪就被称为“revolver”。在采用第一人称单数形式时，动词“volvere”就成为“volvo”，“I roll”就是“我勇往直前”的意思。因此Volvo意为“滚滚向前”。目前中文名称统一为“沃尔沃”，过去也曾有“富豪”的中文名称。"));
		map.put(33,
				new CarInfo(
						R.drawable.d3,
						"斯柯达",
						"SKODA",
						"斯柯达",
						"“斯柯达”商标的含义是：巨大的圆环象征着斯柯达为全世界无可挑剔的产品;鸟翼象征着技术进步的产品行销全世界;向右飞行着的箭头，则象征着先进的工艺;外环中朱黑的颜色象征着斯柯达公司百余年的传统;中央铺着的绿色，则表达了斯柯达人对资源再生和环境保护的重视。现在生产的斯柯达.弗雷西亚牌汽车的商标最下边部分的桂枝树叶，表示胜利。另外，关于“斯柯达“商标还有一个传说：据说，该厂的经理从美洲带回一名印第安仆人，这个人很勤快，脸谱也很美，所以就选用了他的脸谱作为商标，即现在的斯柯达箭形商标。"));
		map.put(34,
				new CarInfo(
						R.drawable.d4,
						"荣威",
						"Roewe",
						"荣威",
						"以两只站立的东方雄狮构成。狮子是百兽之王，在中国文化中代表着吉祥、威严、庄重，同时在西方文化中狮子也是王者与勇敢精神的象征，其昂然站立的姿态传递出一种崛起与爆发的力量感。双狮图案以直观的艺术化手法，展现出尊贵、威仪、睿智的强者气度。符号寓意：图案的中间是双狮护卫着的华表。华表是中华文化中的经典图腾符号，不仅蕴含了民族的威仪，同时具有高瞻远瞩，祈福社稷繁荣、和谐发展的寓意。"));
		map.put(35,
				new CarInfo(
						R.drawable.d5,
						"吉利帝豪",
						"EMGRAND",
						"吉利帝豪",
						"帝豪EMGRAND，象征“豪华、稳健、力量 ”，是吉利汽车在吉利母品牌之下构建的一个子品牌。帝豪品牌对吉利汽车有传承亦有突破，其标识设计高贵、庄雅透露着浓郁的国际化特质，在甩掉吉利原有历史印象包袱的同时，也有效地传承了优秀固有文化基因。稳重、高贵的标识内涵将卓有成效地为帝豪品牌开拓广阔而稳定的全新市场助力。"));
		map.put(36,
				new CarInfo(R.drawable.d6, "英伦", "engloncar", "英伦",
						"英伦汽车，既现代又古典、既繁华又祥和、既庄重又内敛，彰显自信气度，阐释了融汇西方先进的生产工艺和管理理念，自主创新、开拓进取，打造属于中国的国际化品牌的决心和信念!"));
		map.put(37,
				new CarInfo(
						R.drawable.d7,
						"瑞麒",
						"RIICH",
						"瑞麒 	",
						"瑞麒的标志由一双展开的飞翼和字母“R”组成，彰显了其奋进、大气、颇具领导力的风格，也凸现了这一品牌的核心价值：自由、驾驭、先锋感。中间的“R”字不仅醒目的展示了瑞麒的品牌标识，也呈现出聚焦稳重的视觉美感。"));
		map.put(38,
				new CarInfo(
						R.drawable.d8,
						"斯巴鲁",
						"SUBARU",
						"斯巴鲁",
						"斯巴鲁是富士重工业株式会社( FHI )旗下专业从事汽车制造的一家分公司，成立于1953年，最初主要生产汽车，同时也制造飞机和各种发动机，是生产多种类型、多用途运输设备的制造商。富士重工“斯巴鲁”汽车的标志采用六连星的形式。"));
		map.put(39,
				new CarInfo(
						R.drawable.d9,
						"威麟",
						"RELY",
						"威麟",
						"威麟品牌是奇瑞旗下的中高端全能商务车品牌，它以“先见、进取、掌控”为核心，以“Engines & Drivers” 的品牌理念为依托，通过生产实用可靠的、经济环保的优质产品，满足消费者多种多样的商务用车需求。威麟所具有的领导性企业管理和产品生产意识，以及始终与时俱进的年轻化经营思维，与优秀的产品一起，将这一品牌核心清晰地展现出来。"));

		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(40,
				new CarInfo(
						R.drawable.e0,
						"标致",
						"PEUGEOT",
						"标致",
						"“标致”(PEUGEOT)曾译名为 “别儒”，公司采用 “狮子”作为汽车的商标。 “标致”的商标图案是蒙贝利亚尔创建人别儒家族的徽章。据说别儒的祖先曾到美洲和非洲探险，在那里见到了令人惊奇的动物—狮子，为此就用狮子作为本家族的徽章。后来，这尊小狮子又成为蒙贝利亚尔省的省徽。"));
		map.put(41,
				new CarInfo(R.drawable.e1, "启辰", "Venucia", "启辰",
						"“蔚蓝星空”寓意着启辰立志高远，矢志不渝的无限追求; “五星辉映”源自“天有五星，地有五行”，“五行俱全”寓意和谐，蕴涵祥瑞之意，并诠释着启辰更深层次的品牌内涵。"));
		map.put(42,
				new CarInfo(
						R.drawable.e2,
						"本田",
						"HONDA",
						"本田",
						"本田公司在80年代成立了商标设计研究组，从来自世界各地的2500多件设计图稿中，确定了现在的三弦音箱式商标，也就是带框的“H”，图案中的H是“本田”拼音Honda的第一个字母。这个标志体现出技术创新，职工完美和经营坚实的特点，同时还有紧张感和可以放松一下的轻松感。"));
		map.put(43,
				new CarInfo(R.drawable.e3, "GMC", "GMC", "GMC",
						"GMC是通用集团旗下的MPV部门。现有使节(Envoy)、峡谷(Canyon)、西拉(Sierra)育空河(Yukon)、旅行(Safari)、Savana等一系列车型。"));
		map.put(44,
				new CarInfo(R.drawable.e4, "宝马Mini", "Mini", "宝马Mini",
						"宝马MINI凭借独特的外观、灵巧的操控性能和出色的安全性能赢得了众多年轻一族的青睐，2000年9月，新款MINI在蒙特罗车展中亮相，旧款MINI宣告停产。"));
		map.put(45,
				new CarInfo(
						R.drawable.e5,
						"马自达",
						"mazda",
						"马自达",
						"MAZDA是日本最著名的汽车品牌之一，日本第四大汽车制造商，是世界著名汽车品牌，是世界上唯一研发和生产转子发动机的汽车公司。2008年《财富》全球500强企业排名中名列第二百五十五位。"));
		map.put(46,
				new CarInfo(R.drawable.e6, "长城", "Great Wall", "长城",
						"椭圆外形： 立足世界 走向中国\n烽火台形象：中国传统文化象征\n剑锋箭头： 充满活力 蒸蒸日上\n敢于亮剑 无坚不摧\n立体“1”：快速反应 永争第一"));
		map.put(47,
				new CarInfo(
						R.drawable.e7,
						"理念",
						"EVERUS",
						"理念",
						"理念(EVERUS)作为广汽本田旗下的两大品牌之一，其品牌产品将享有与广汽本田现有产品相同的技术、品质和服务，即“技术同步、品质同源、服务同网”。它的许多零部件都将会依据中国消费者需求进行开发，并完全按照广汽本田的技术标准及要求开发国内的供应商，确保机能性方面的零部件品质实现和广汽本田一致性。"));
		map.put(48,
				new CarInfo(
						R.drawable.e8,
						"大众",
						"Volkswagen",
						"大众",
						"大众，汽车品牌名，大众汽车公司是德国最年轻的、同时也是德国最大的汽车生产厂家。使大众公司扬名的产品是甲壳虫(Beetle)式轿车(由费迪南德·保时捷设计)，该车在80年代初已生产了2000万辆。它启动了大众公司的第一班高速列车，紧随其后的高尔夫(Golf)、帕萨特(Passat)等也畅销全世界。从1984年大众汽车进入中国市场，大众汽车是第一批在中国开展业务的国际汽车制造商之一。大众汽车自进入中国市场以来，就一直保持着在中国轿车市场中的领先地位。"));
		map.put(49,
				new CarInfo(
						R.drawable.e9,
						"陆风",
						"landwind",
						"陆风",
						"陆风汽车由江铃控股有限公司在国家新的汽车产业政策的引导和支持下，于2004年11月由长安汽车股份有限公司和江铃汽车集团公司通过强强联合，实现中、中合作的国内首家汽车制造企业。拥有江铃控股本部拥有两大生产基地：昌北基地与小蓝轿车基地。"));

		map.put(50,
				new CarInfo(
						R.drawable.f0,
						"雪佛兰",
						"Chevrolet",
						"雪佛兰",
						"雪佛兰汽车公司(Chevrolet)，亦作雪佛莱或雪福兰，由美国人威廉·杜兰特与通用汽车大股东不合离开后与瑞士赛车手、工程师路易斯·雪佛兰于1911年在美国底特律创立，美国人常昵称雪佛兰为Chevy。"));
		map.put(51, new CarInfo(R.drawable.f1, "福特", "Ford", "福特",
				"福特汽车的标志是采用福特英文Ford字样，蓝底白字。由于创建人亨利·福特喜欢小动物，所以标志设计者把福特的英文画成一只小白兔样子的图案。"));
		map.put(52,
				new CarInfo(
						R.drawable.f2,
						"起亚",
						"KIA",
						"起亚",
						"起亚的名字，源自汉语，“起”代表起来，“亚”代表在亚洲。因此，起亚的意思，就是“起于东方”或“起于亚洲”。源自汉语的名字、代表亚洲崛起的含义，正反映了起亚的胸襟——崛起亚洲、走向世界。"));
		map.put(53,
				new CarInfo(
						R.drawable.f3,
						"萨博",
						"SAAB",
						"萨博",
						"萨博公司是由斯堪尼亚公司和瑞典飞机有限公司合并，原飞机公司瑞典文缩写为SAAB，后即作为公司轿车的标志。 商标正中是一头戴王冠的狮子头像，王冠象征着轿车的高贵，狮子则为欧洲人崇尚的权利象征。半鹰、半狮的怪兽图案象征着一种警觉，这是瑞典南部两个县流行的一种象征，而萨博汽车和航行器的生产就起源在这里。设计者为瑞典艺术家Carl Fredrik Reutersward。 萨博汽车重新设计了蓝形小圆盘，现在融进了SAAB传统的狮身鹫首怪兽的纹章以及“SAAB”的标识字母。风格整体一致，整齐划一。2001款的汽车即开始采用这种标识。新的标识同样是由瑞典艺术家Carl Fredrik Reutersward设计的。"));
		map.put(54,
				new CarInfo(
						R.drawable.f4,
						"华普",
						"----",
						"华普",
						"华普诞生于上海，从“在上海区域内按上海标准生产的汽车”角度，提出“海派汽车”的概念，和“享受海派生活/Shanghai Spirit”的品牌诉求口号，逐渐形成品牌塑造中的创新元素：精致、运动、时尚。把上海美好的城市精神熔铸成产品和品牌的核心价值，让用户在使用华普产品时，会感知和联想到上海的国际性、先进性、包容性、时尚性、精致性、超值性，这是华普文化造车的愿景。"));
		map.put(55,
				new CarInfo(
						R.drawable.f5,
						"中华",
						"---",
						"中华",
						"华晨集团早在1997年就开始为生产中华轿车作积极准备了，而第一代的中华车是在2000年底下线的，产量大约在300辆，也就是华晨金杯厂里面统称的第一批车。这批车由于没有得到上市销售的准入证，所以只是在随后的几大车展上露了露面，然后就分配给厂内自用了。第一代的中华轿车虽然有着潇洒大气的由意大利设计的外形，但这批车无论是内饰质量还是装配水平，乃至机械性能都处在很低的水平，内饰质量和行车感觉真的是糟透了，粗糙的内饰像街道工厂的手艺，车开起来仿佛一切都和你较劲。"));
		map.put(56,
				new CarInfo(
						R.drawable.f6,
						"奇瑞",
						"chery",
						"奇瑞",
						"中文释义：奇，有特别地的意思;瑞，有“吉祥 如意”的意思，合起来就是特别地吉祥如意。\n英文释义：CHERY是英文单词CHEERY(中文意 思为“欢呼地、兴高采烈地”)减去一个“e”而来，表达了企业努力追求、永不满足现状的理念。"));
		map.put(57,
				new CarInfo(
						R.drawable.f7,
						"阿斯顿·马丁",
						"AstonMartin",
						"阿斯顿·马丁",
						"阿斯顿·马丁汽车标志为一只展翅飞翔的大鹏，喻示该公司象大鹏一样，具有从天而降的冲刺速度和远大的志向。分别注有阿斯顿、马丁英文字样表明是一家“三结义”汽车公司。以生产敞蓬旅行车、赛车和限量生产的跑车而闻名世界的阿斯顿•马丁•拉宫达公司名声赫赫，不知是否得益与这只大鹏带来的运气。"));
		map.put(58,
				new CarInfo(R.drawable.f8, "夏利", "XiaLi", "夏利",
						"天津一汽夏利汽车股份有限公司是中国第一汽车集团公司控股的经济型轿车制造企业，是一家集整车制造、发动机、变速器生产以及科研开发于一体的股份制公司。"));
		map.put(59,
				new CarInfo(
						R.drawable.f9,
						"雷克萨斯",
						"Lexus",
						"雷克萨斯",
						"用椭圆环绕的L字母，根据美国丰田汽车销售公司的官方说法，这个椭圆弧度依照精确的数学公式修 饰，动用三个以上的设计商和广告商，花了半年多的时间才完成：这个脱颖而出的标志，击败了五个设计比稿。1987年，摩利设计公司(Molly DesignsInc.)负责人摩利·山德斯(MollySanders)，花了三个月的时间精巧制作出这个别具特色的椭圆和L，取代原先最有希望获选的版本——一个没有圆圈环绕，看起来像海鸥翅膀的L。"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(60,
				new CarInfo(R.drawable.g0, "现代", "HYUNDAI", "现代",
						"现代汽车公司的标志椭圆内的斜字母H是现代公司英文名HYUNDAI的首个字母，椭圆既代表汽车方向盘，又可看作地球，两者结合寓意了现代汽车遍布世界。"));
		map.put(61,
				new CarInfo(
						R.drawable.g1,
						"吉奥",
						"----",
						"吉奥",
						"吉奥汽车集团有限公司是一家集汽车整车及零部件生产企业的综合性集团。创建于2 003年9月27日，现下辖2个整车生产基地、1个汽车研发中心和7家包括发动机在内的零部件生产厂家，现有员工2000余人，其中，中、高级工程技术及高级管理人员200余人。"));
		map.put(62,
				new CarInfo(
						R.drawable.g2,
						"菲亚特",
						"FIAT",
						"菲亚特",
						"意大利有一家规模最大的汽车公司，雇用的职员近30万人，它就是意大利都灵汽车公司(FabbricaItalianadiAutomobiliTorino)，后来，该公司将其4个单词的第一个字母连起来，称为“FIAT”，音译为“菲亚特”并作为公司产品的商标。"));
		map.put(63,
				new CarInfo(
						R.drawable.g3,
						"凯迪拉克",
						"Cadillac",
						"凯迪拉克",
						"凯迪拉克徽标可谓是其精神内涵的集中体现，著名的花冠盾形取自安东尼(德)凯迪拉克的族徽，是典型的贵族标志，既表现了底特律城创始人的勇气和荣誉，同时也象征着其在汽车行业中的领导地位。选用“凯迪拉克”之名是为了向法国的皇家贵族、探险家、美国底特律城的创始人安东尼·门斯·凯迪拉克表示敬意。"));
		map.put(64, new CarInfo(R.drawable.g4, "北汽威旺", "----", "北汽威旺",
				"北汽威旺是北汽集团推出的一款全新的微客产品。以“威旺”命名，取意“威天下、旺未来”。"));
		map.put(65,
				new CarInfo(
						R.drawable.g5,
						"帕加尼",
						"PAGANI",
						"帕加尼",
						"Pagani Zonda堪称超级跑车，敢与法拉利、保时捷、兰博基尼等跑车界大腕一比高下，其完美的做工足以让法拉利汗颜。每一辆交付客户的Zonda皆具有款型皆具有独特的上空魅力、超强的动力性能，以及承袭自Pagani手工制作的尊荣质感，使到限量生产的Pagani Zonda超级跑车，身价益发不凡。"));
		map.put(66, new CarInfo(R.drawable.g6, "西雅特", "Seat", "西雅特",
				"西亚特(Seat)是西班牙最大的汽车公司，1950年成立于巴塞罗那。现在属于德国大众汽车公司子公司。"));
		map.put(67,
				new CarInfo(R.drawable.g7, "现代劳恩斯", "----", "现代劳恩斯", "隶属公司：韩车现代汽车车公司"));
		map.put(68,
				new CarInfo(
						R.drawable.g8,
						"众泰",
						"----",
						"众泰",
						"众泰控股集团始建于2003年，是一家以汽车整车及发动机、模具、钣金件、变速器等汽车关键零部件的研发制造为核心业务和发展方向的现代化民营企业集团。现有浙江、湖南两大整车生产基地，形成“众泰”、“江南”两大汽车整车自主品牌，具有国内外先进水平的冲压、焊装、涂装、总装四大工艺生产线和整车动态性能检测线。是目前国内拥有轿车、轻型客车、轻型货车和纯电动汽车等完整汽车生 产资质的民营汽车专业制造商之一，企业注册资本6.6亿元。"));
		map.put(69,
				new CarInfo(
						R.drawable.g9,
						"克莱斯勒",
						"Chrysler",
						"克莱斯勒",
						"2007年5月14日 ，戴姆勒·克莱斯勒公司宣布，将子公司克莱斯勒集团80.1%的股权出售给著名的私募基金Cerberus，收购价格为55亿欧元。次日是新克莱斯勒成立后的第一个工作日，从位于美国密歇根州的克莱斯勒全球总部到世界各地的分支机构，都举行了各种庆祝活动。据悉，新克莱斯勒公司的亮相活动将持续到年底。"));

		map.put(70,
				new CarInfo(
						R.drawable.h0,
						"江淮",
						"JAC",
						"江淮",
						"安徽江淮汽车股份有限公司(以下简称江淮汽车)前身是创建于1964年的合肥江淮汽车制造厂，1999年9月改制为股份制企业，隶属于安徽江淮汽车集团有限公司。2001年在上海证券交易所挂牌上市，股票名称为“G江汽”(原名为“江淮汽车”)，股票代码600418。"));
		map.put(71,
				new CarInfo(
						R.drawable.h1,
						"雷诺",
						"RENAULT",
						"雷诺",
						"雷诺，(Renault S.A.)是一间法国车辆制造商，生产的车辆种类有小型车、中型车、休旅车、大型车(包含卡车和工程用车及巴士)等。雷诺第一次进入美国市场销售是1950年代及1960年代之间，他们在美国市场确定的品牌发音方式是“Ren-ALT”，而这个发音方是今日最为广泛接受的。然而雷诺正确的发音是“Rhen-oh”(就如同英国常见的发音方式)。"));
		map.put(72,
				new CarInfo(
						R.drawable.h2,
						"科尼赛克",
						"Koenigsegg",
						"科尼赛克",
						"它的标志是瑞典皇空军的标志，为一幽灵图!所以有“幽灵跑车”之称。1994年，瑞典一群有汽车工业经验和专业知识的优秀设计师和工程师，以克瑞汀·凡·科尼塞格的名字，在瑞典南部安吉荷姆附近成立了这家汽车厂。"));
		map.put(73,
				new CarInfo(
						R.drawable.h3,
						"巴博斯",
						"BRABUS",
						"巴博斯",
						"BRABUS公司由Bodo Buschmann 先生创建于1977年，现已跻身为全球最大的汽车改装厂商之一。BRABUS尽管不是奔驰的子公司，但它却是奔驰的御用改装厂，全世界所有的Smart变形车都出自BRABUS之手，一年改装的奔驰车有80000余辆。它的总部位于鲁尔区的腹地Bottrop，邻近 A2 高速公路。除了宽敞的展示大厅外，这家活跃于国际市场的企业还将其开发部和生产部设于此地。"));
		map.put(74,
				new CarInfo(
						R.drawable.h4,
						"宝骏",
						"----",
						"宝骏",
						"上汽通用五菱2010年创建的自主汽车品牌。上汽通用五菱正式发布的新乘用车品牌“宝骏汽车”，标志这个中国微车领头羊开始正式进军方兴未艾的轿车市场。宝骏品牌源由：“骏”的本义是良驹，宝骏即人们最心爱的良驹。"));
		map.put(75,
				new CarInfo(
						R.drawable.h5,
						"北京汽车",
						"----",
						"北京汽车",
						"北京汽车与北汽集团为统一标识。北汽集团新发布的品牌标识将“北”字作为设计的出发点，“北”既象征了中国北京，又代表了北汽集团，体现出企业的地域属性与身份象征。同时，“北”字好似一个欢呼雀跃的人形，表明了“以人为本”是北汽集团永远不变的核心。它传承与发展了北汽集团原有形象，呈现出一种新的活力，表达了北汽集团立足北京，放眼全球的远大目标。标识中的“北”字，犹如两扇打开的大门，它是北京之门，北汽之门，开放之门，未来之门，标志着北汽集团更加市场化、集团化、国际化，与集团全新的品牌口号 “融世界 创未来”相辅相成，表示北汽集团将以全新的、开放包容的姿态启动新的品牌战略。"));
		map.put(76,
				new CarInfo(
						R.drawable.h6,
						"北汽制造",
						"----",
						"北汽制造",
						"北京汽车制造厂有限公司是中国汽车工业发展的先驱之一，集北京汽车工业五十年之大成，是北京五十年汽车工业发展的结晶，是北京汽车民族品牌的代表。 企业前身北京汽车制造厂创建于1958年，由朱德委员长亲自题写厂名，是国家继第一汽车制造厂后兴建的第二家大型汽车生产企业。公司拥有四十余年的轻型越野车和卡车开发生产历史，为公司赢得了“越野世家”的美名。公司以越野车为基础，以满足军队装备和市场需求为目标，不断拓展产品系列，成为国家军车定点生产企业、中国轻型越野车骨干企业。"));
		map.put(77,
				new CarInfo(
						R.drawable.h7,
						"奔腾",
						"besturn",
						"奔腾",
						"正式命名为“奔腾”前，奔腾轿车的代号为c301，外界一向称其为“红旗c301”。对于c301的品牌归属问题，一汽轿车采取了十分谨慎的态度。2006年5月18日，一汽轿车正式对外宣布c301不会采用“红旗”品牌，而是以“奔腾”(besturn)品牌上市销售。"));
		map.put(78,
				new CarInfo(
						R.drawable.h8,
						"比亚迪",
						"BYD",
						"比亚迪",
						"比亚迪LOGO由两个同心的内外椭圆构成，象征比亚迪与合作伙伴一路同驰骋。内椭圆等分为蓝天白云两部分，突出比亚迪打造节能环保汽车的意志。两个椭圆间的黑色椭圆带中镶嵌的“BYD”，展现比亚迪立足科技的理念。整体的椭圆形结构，彰显比亚迪既是勇立潮头的大船，更是孕育自主品牌的摇篮。"));
		map.put(79,
				new CarInfo(
						R.drawable.h9,
						"布加迪",
						"BUGATTI",
						"布加迪",
						"布加迪是法国最具有特色的超级跑车车厂之一。布加迪以生产世界上最好的及最快的车闻名于世。最原始的布加迪品牌已经在第二次世界大战后消失。不过战后此品牌曾经有两度中兴，目前它是大众集团旗下的一个品牌。"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		// map.put(80, new CarInfo(R.drawable.ferrari, "法拉利"));
		// map.put(81, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		// map.put(82, new CarInfo(102));
		// map.put(83, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		// map.put(84, new CarInfo(R.drawable.maybach, "迈巴赫"));
		//
		// map.put(85, new CarInfo(R.drawable.ferrari, "法拉利"));
		// map.put(86, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		// map.put(87, new CarInfo(102));
		// map.put(88, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		// map.put(89, new CarInfo(R.drawable.maybach, "迈巴赫"));
		//
		// map.put(90, new CarInfo(R.drawable.ferrari, "法拉利"));
		// map.put(91, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		// map.put(92, new CarInfo(102));
		// map.put(93, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		// map.put(94, new CarInfo(R.drawable.maybach, "迈巴赫"));
		//
		// map.put(95, new CarInfo(R.drawable.ferrari, "法拉利"));
		// map.put(96, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		// map.put(97, new CarInfo(102));
		// map.put(98, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		// map.put(99, new CarInfo(R.drawable.maybach, "迈巴赫"));
		map.put(80,
				new CarInfo(
						R.drawable.i0,
						"昌河",
						"----",
						"昌河",
						"江西昌河汽车有限责任公司是直接隶属中国长安汽车集团股份有限公司的全资子公司，拥有景德镇、九江、合肥三个整车工厂和九江发动机工厂，具备年产30万辆整车和15万台汽车发动机的生产能力。2010年，将在中国长安的统领下，实施汽车和发动机的扩能改造与生产线建设。"));
		map.put(81,
				new CarInfo(
						R.drawable.i1,
						"川汽野马",
						"----",
						"川汽野马",
						"四川汽车工业集团有限公司诞生于20世纪80年代末，曾经生产的“金顶”牌客车系列、“野马”、“白鹿”牌轻型越野车和客货两用车风靡全国，尤其是“野马”牌越野车更是国家免检产品，国家“公检法”系统专用车辆定点生产厂家。 1994年5月由成都轻型汽车总厂等三家成都市属汽车企业全建制合并组成的四川汽车工业集团公司。于2002年9月改制后经成都市政府相关部门批复由全国民企500强——四川富临实业集团有限公司实施整体兼并重组，被列为四川省、成都市人民政府“十一五”规划重点发展的汽车制造企业。2005年8月完成新厂建设和技术改造,由市区搬迁至国家经济技术开发区——成都经济技术开发区(龙泉驿区)。2006年8月正式更名为四川汽车工业集团有限公司。"));
		map.put(82,
				new CarInfo(
						R.drawable.i2,
						"DS",
						"DS",
						"DS",
						"1955年DS在巴黎汽车展上第一次亮相。独特的外形和超前的技术几乎在一夜之间就将整个汽车行业与公众俘虏。车展开场后，15分钟内就收到了743份订单。而车展第一天， DS的订单数达到了12,000份。当时大多数记者完全把精力放在了DS上。除了滔滔不绝地讲述DS是如何戏剧性地将技术与设计相结合，从而创造出汽车动力与操控(ride vs handling)真正统一的新可能，他们对其它的事件再没什么兴趣了。"));
		map.put(83,
				new CarInfo(
						R.drawable.i3,
						"大发",
						"----",
						"大发",
						"“大发”是 “大发工业株式会社”的简称。大发工业株式会社，1907年创立于日本大阪，百年来始终致力于小型车领域的拓展，在发动机、空间、车辆安全及环境保护等方面都拥有先进的技术，是全球领先的小型车制造企业，被誉为“小型车专家”。作为丰田集团的一员，专门生产低油耗・省资源・省空间的小型车，并以成为“真正的国际企业”为目标，积极推动全球化。"));
		map.put(84,
				new CarInfo(
						R.drawable.i4,
						"道奇",
						"DODGE",
						"道奇",
						"道奇蝰蛇SRT-10　道奇兄弟公司实际上制造了福特汽车第一批产品中的大部分，包括发动机、底盘和所有的传动部件，而福特也很少使用其它制造商提供的车身和底盘。多年来道奇兄弟和福特的关系一直很好，约翰还是福特汽车的副总裁。然而在1913年，道奇兄弟开始注意到福特有想要自给自足的倾向。那时道奇兄弟已在密执安州重开了一家大型工厂，后来成为著名的道奇总厂。霍瑞德和约翰建造了世界上第一个汽车试验场，并于1914年设计出他们的第一辆车。"));
		map.put(85, new CarInfo(R.drawable.i5, "东南", "----", "东南",
				"“鹏鸟”造型整体由“SOUTH”和” EAST”的首字母变幻组成，意指东南汽车。"));
		map.put(86,
				new CarInfo(
						R.drawable.i6,
						"福迪",
						"FODAYAUTO",
						"福迪",
						"广东福迪汽车有限公司是中国国家发展和改革委员会认可的汽车重点生产企业，是广东省高新技术企业，整车生产历史可追溯到1988年。公司占地500多亩，拥有现代化的模具数控加工中心，先进的冲压、焊接、涂装、总装生产线，整车生产工艺齐全，拥有年生产30万台车身、首期5万辆整车的先进生产线，是目前华南地区最大的冲压中心和广东最大的汽车生产基地之一。"));
		map.put(87,
				new CarInfo(
						R.drawable.i7,
						"福田",
						"FOTON",
						"福田",
						"北汽福田汽车股份有限公司(简称福田汽车)成立于1996年8月28日，是一家跨地区、跨行业、跨所有制的国有控股上市公司。总部位于北京市昌平区，现有资产近80亿元，员工2.6万人，是一个以北京为管理中心，在京、津、鲁、冀、湘、鄂、辽、粤、新等9个"));
		map.put(88,
				new CarInfo(
						R.drawable.i8,
						"观致",
						"QOROS",
						"观致",
						"观致汽车有限公司(Qoros)，原奇瑞量子汽车有限公司(CQAC)，是由奇瑞汽车有限公司与以色列集团各出资50%组成的合资公司。公司于2007年12月成立，初始注册资金34亿元人民币。其口号为：“创造未来的历史。”"));
		map.put(89,
				new CarInfo(
						R.drawable.i9,
						"广汽传祺",
						"----",
						"广汽传祺",
						"2010年9月3日，广汽首款自主研发的乘用车传祺将在广州番禺下线。其实，传祺对我们来说并不陌生，早在北京车展广汽发布传祺品牌时，传祺样车就已亮相。从多方资料来看，传祺官方定位于B级车，竞争对手定为雅阁和凯美瑞，但售价在10.8-18万左右。"));

		map.put(90,
				new CarInfo(
						R.drawable.j0,
						"哈飞",
						"----",
						"哈飞",
						"哈尔滨哈飞汽车工业集团有限公司隶属于中国长安汽车集团，为中国微型车制造和研发的奠基者和先 行者，是中国汽车骨干生产企业和研发基地。公司包括哈飞汽车股份有限公司、威海分公司两大生产制造基地，现有职工7000余人，公司占地面积128万平方米，目前，公司汽车生产能力为40万辆/年。从1982年第一台微型载货汽车下线，经过三十年的发展和积累，截至2011年6月底，累计产销已达260万辆，其中出口突破20万辆。"));
		map.put(91,
				new CarInfo(
						R.drawable.j1,
						"海格",
						"HIGER",
						"海格",
						"金龙联合汽车工业(苏州)有限公司成立于1998年底，一般被人们简称为“苏州金龙”。10年来，苏州金龙艰辛探索，拼搏成长，建成50万平方米的现代化客车制造基地，年销量突破50亿元大关，海格客车驰骋全球55个国家和地区，以年均51%的增速迅猛崛起，开辟了一条可持续的快速发展之路。"));
		map.put(92,
				new CarInfo(R.drawable.j2, "红旗", "----", "红旗",
						"红旗轿车在中国是个家喻户晓的名字。“红旗”二字已经远远超出了一个轿车品牌的含义，新中国发生的太多历史事件都与“红旗”有关。在国人心里，它有其他品牌所不能代替的位置。"));
		map.put(93,
				new CarInfo(
						R.drawable.j3,
						"华泰",
						"----",
						"华泰",
						"华泰汽车集团是一家集汽车研发、核心零部件生产和整车制造于一体的综合性汽车制造企业。集团成立于2000年，现有总资产120亿元，员工7000余人。总部位于北京，下设北京汽车研发中心和山东荣成、内蒙古鄂尔多斯、吉林延边三个汽车生产基地及在内蒙古包头设有一个模具厂。华泰汽车以产业报国为己任，崇尚技术，创造品味，开发低炭技术，履行社会责任，以打造世界级的中高端汽车品牌为目标。目前已具备年产30万台欧Ⅳ/Ⅴ清洁柴油乘用车发动机、45万台自动变速器，35万辆整车的生产制造能力。"));
		map.put(94, new CarInfo(R.drawable.j4, "黄海", "----", "黄海",
				"“黄海”汽车是中国驰名商标。是辽宁曙光汽车集团股份有限公司旗下汽车品牌。"));
		map.put(95, new CarInfo(R.drawable.j5, "吉普", "Jeep", "吉普",
				"JEEP车标的含义就是英文吉普的意思，也是戴姆勒·克莱斯勒公司旗下生产越野车的公司JEEP的名称。而越野是泛指有越野性能的车。"));
		map.put(96,
				new CarInfo(
						R.drawable.j6,
						"江铃",
						"JMC",
						"江铃",
						"江铃汽车股份有限公司(简称“江铃”，英文全称Jiangling Motors Co., Ltd.，英文缩写JMC)，中国商用车行业最大的企业之一,连续四年位列中国上市公司百强。江铃于二十世纪八十年代中期在中国率先引进国际先进技术制造轻型卡车，成为中国主要的轻型卡车制造商。"));
		map.put(97,
				new CarInfo(R.drawable.j7, "金杯", "----", "金杯",
						"公司是华晨汽车集团所属的金杯汽车股份有限公司控股的国内主要卡车生产厂之一，于1958年建厂，具有悠久的发展历史，2002年由沈阳汽车制造厂重组为沈阳金杯车辆制造有限公司。"));
		map.put(98,
				new CarInfo(
						R.drawable.j8,
						"AC Schnitzer",
						"AC Schnitzer",
						"AC Schnitzer",
						"一提到BMW的改装，首先会想到BMW 御用改装厂AC Schnitzer。1987年才创建的AC Schnitzer是全世界最大的BMW专业改装厂，虽然建厂较晚，但因为是世界最大的BMW的经销商Kohl Automobile Gmbh和Schnitzer赛车集团合作创立。在经验和销售两方面都具有全面优势(早在1964年，Schnitzer就已经开始致力于改装BMW并参加各项赛事)。AC Schnitzer顺理成章地成为了全世界最牛的BMW改装厂。"));
		map.put(99,
				new CarInfo(
						R.drawable.j9,
						"中兴",
						"----",
						"中兴",
						"河北中兴汽车制造有限公司是1999年12月组建的合资公司， 注册资本3000万美元，总资产11900万美元。是目前国内最具规模的、具有完全自主知识产权及整车研发能力的现代化皮卡、SUV生产企业，是中国第一辆具有自主知识产权国产品牌皮卡车的诞生地。"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(100, new CarInfo(R.drawable.k0, "威兹曼", "Wiesmann", "威兹曼",
				"威兹曼以蜥蜴做为自己的品牌标志，表明所生产的跑车具备蜥蜴一般完全的抓地力和快速奔跑时突然改变方向的灵活身手!"));
		map.put(101, new CarInfo(R.drawable.k1, "双环", "----", "双环",
				"社会责任感和使命感的体现;所生产的品牌汽车以“华而不贵”为导向，不仅 得到了中国政府和广大消费者的推崇，同时也赢得了世界市场的普遍认同。"));
		map.put(102,
				new CarInfo(
						R.drawable.k2,
						"世爵",
						"SPYKER",
						"世爵",
						"世爵在第一次世界大战中与荷兰飞机厂合并。所以1915年，推出了公司标识，即由一个水平的飞机螺旋桨穿越镌刻公司座右铭的辐轮。之所以现在的LOGO车轮加上一副螺旋桨，主要是 因为有制造飞机的这样一段历史，因为毕竟飞机上所使用的这些性能如果能够合理运用到现在的一些汽车制造当中，尤其是空气动力学跑车制造当中是非常重要的。它的含义，体现了企业的箴言“Nulla tenaci invia est via”(“执着强悍·畅行无阻”)，对世爵来说所有的事情都是可能的。这也一方面体现了世爵企业的哲学，公司希望为世爵的车主制造出全球最先进，设计最独特的跑车，同时也印证了世爵的车主他们的成功史，对世爵每一位车主来说，任何人都不会特别一帆风顺的，都是克服了重重的困难，如今达到了这样的事业的顶峰。"));
		map.put(103,
				new CarInfo(
						R.drawable.k3,
						"三菱",
						"----",
						"三菱",
						"日本三菱汽车以三枚菱形钻石为标志，正为突显其蕴含在雅致的单纯性中的深邃灿烂光华-菱钻式的造车艺术。过去七十五年的作品，如Diamante、GTO、Galant、Mirage，RVR，Pajero、Lancer Evolution等车款，奠定了三菱在车坛上的菱钻形象。现在，这个标志是三菱组织中各公司全体职工的象征。"));
		map.put(104,
				new CarInfo(
						R.drawable.k4,
						"力帆",
						"LIFAN",
						"力帆",
						"力帆集团是中国最大的民营企业之一，成立于1992年。历经17年的艰苦奋斗，已迅速发展成为融汽车、摩托车的研发、生产、销售(包括出口)为主业，并投资于金融业的大型民营企业。2007年，力帆集团统计销售收入121.6亿元人民币，发动机产销量306万台，出口创汇4.096亿美元，专利拥有量4061项，上述四项指标均居全国同行领先地位。目前，力帆集团已有员工13715人，拥有一个国家级技术中心，连续多年入选中国500强企业，并居于重庆民营企业50强之首。"));
		map.put(105,
				new CarInfo(
						R.drawable.k5,
						"卡尔森",
						"carlsson",
						"卡尔森",
						"卡尔森汽车始建于1989年，是一家拥有独立品牌的汽车生产公司。卡尔森拥有最纯正的德国血统，致力于专业设计并制造高端整车，打造卡尔森汽车浓郁的贵族品牌特质。卡尔森汽车的品牌定位是：“德国至宝、贵族血统、气质优雅”。卡尔森的客户分布在全球各个国家和地区，尤其在欧洲、中东拥有很多贵族客户。"));
		map.put(106,
				new CarInfo(
						R.drawable.k6,
						"讴歌",
						"Acura",
						"讴歌",
						"讴歌(Acura)是日本本田汽车公司旗下的高端子品牌，于1986年在美国创立，其名称Acura源于拉丁语Accuracy(精确)，标志为一个用于工程测量的卡钳形象，反映出讴歌精湛的造车工艺与追求完美的理念。作为第二个日系豪华汽车品牌，讴歌以个性化和前瞻科技的“运动豪华”理念对豪华车的概念进行了重新诠释，品牌一经推出即在北美市场获得了巨大的成功。讴歌的车型均在北美进行设计、开发和生产，先后开发出了以TL、RL、MDX等车型为首的丰富产品线。讴歌品牌于2006年9月27日正式登陆中国市场。"));
		map.put(107,
				new CarInfo(R.drawable.k7, "思铭", "CIIMO", "思铭",
						"在2011年的广州车展上，东风本田发布了旗下的自主品牌标识，同时还有其首款车型的名称——思铭。近日，有消息称，该车将在今年4月份上市，而价格预计在11万起。"));
		map.put(108,
				new CarInfo(
						R.drawable.k8,
						"猎豹",
						"----",
						"猎豹",
						"湖南长丰汽车制造股份有限公司，是国内A股上市公司，现为广汽长丰汽车股份有限公司。目前三大股东分别是广汽集团股份有限公司、长丰集团有限责任公司、日本三菱自动车株式会社。 公司业务范围涵盖整车及相关零部件的研发、制造、销售与服务。“猎豹”品牌，在国内SUV行业中具有较为明显的专业与品牌优势;自主品牌轿车正处于后发赶超阶段。"));
		map.put(109,
				new CarInfo(
						R.drawable.k9,
						"双龙",
						"SSANGYONG",
						"双龙",
						"双龙汽车公司(SSANG YONG Motor Company)附属韩国双龙集团，是以制造四轮驱动汽车为主，并生产大型客车、特种车、汽车发动机及零配件的著名汽车制造企业。 其前身为创立于1954年的东亚汽车公司，1986年10月并入双龙集团，1988年3月更名为双龙汽车公司。以犀牛牌四轮驱动吉普车和克兰多牌家用型吉普车为代表的双龙汽车，已出口到欧洲、亚洲、中南美洲及非洲等60多个国家和地区。双龙汽车从专门生产四轮驱动越野车和特种车起家，后与德国奔驰汽车公司合资，引进先进技术，发展成为综合性的汽车制造企业。此外还有同名的恐龙、地名和人名等。"));

		map.put(110,
				new CarInfo(
						R.drawable.l0,
						"Gumpert",
						"Gumpert",
						"Gumpert",
						"Gumpert跑车厂商，Audi原厂的御用改装车，是MTM集团(Motoren Technik Mayer)的主力之一。\n原本全名为GMG Sportwagen Manufaktur，背后的MTM集团(Motoren Technik Mayer)与Audi原厂关系一直相当密切，从1985年开始Audi旗下便有不少概念车款或甚至赛车，都是由原厂性能改装部门与MTM合作开发而成，后来也陆续开发包括Audi S2、S3与S4、RS4的升级套件与改装部品;MTM同时也提供包括DTM房车赛Audi厂队的技术与赛事等支持。"));
		map.put(111, new CarInfo(R.drawable.l1, "光冈", "MITSUOKA", "光冈",
				"创建于1968年，主要是在日本市场销售欧美高级汽车，包括新车和二手车。旗下的五十多家销售店遍布日本各大主要城市。"));
		map.put(112,
				new CarInfo(
						R.drawable.l2,
						"凯佰赫",
						"KOMBAT",
						"凯佰赫",
						"Dartz集团因制造类似稀奇古怪的项目产品而闻名，而由著名的DARTZ(来自拉脱维亚的汽车公司)和俄罗斯KOMBAT联合打造的凯佰赫战盾可以说是世界上最安全的SUV，该车的最大特点就是全车装备了安全装甲系统，全车身覆盖厚达7厘米的装甲，并装有防弹玻璃，整备质量超过3吨。它所搭载的8.0升的8缸发动机，最大功率达到了456马力，极速达到240公里每小时。"));
		map.put(113,
				new CarInfo(
						R.drawable.l3,
						"纳智捷",
						"Luxgen",
						"纳智捷",
						"纳智捷(Luxgen)(杭州)汽车有限公司，是台湾最大的汽车企业裕隆集团与大陆合资成立之公司。纳智捷定位于“自主品牌，创新科技，后发先制，引领世界”，将生产车种为「豪华」、「智能」兼具的人性化智能车，车款包括MPV(厢式休旅车)、SUV(运动休旅车)与小型轿车等。"));
		map.put(114,
				new CarInfo(
						R.drawable.l4,
						"林肯",
						"lincoln",
						"林肯",
						"林肯：林肯大陆(LINCOLN CONTINENTAI)是林肯 · 默寇利部于1939年首推的名牌豪华车型。该车型显示林肯 · 默寇利部生产的高级轿车技术无懈可击，乃豪华车中的佼佼者，被称为福特汽车公司的传世佳作。 林肯(LINCOLN)轿车是以美国第16任总统的名字阿伯拉罕 · 林肯命名的汽车，借助林肯总统的名字来树立公司的形象，显示该公司生产的是顶级轿车。其商标是在一个矩形中含有一颗闪闪放光的星辰，表示林肯总统是美国联邦统一和废除奴隶制的启明星，也喻示福特 · 林肯牌轿车光辉灿烂。"));
		map.put(115,
				new CarInfo(
						R.drawable.l5,
						"迈凯轮",
						"McLaren",
						"迈凯轮",
						"迈凯轮是在1963年诞生于英国，它是一个以F1为主，公路跑车为辅的超跑品牌。作为一支驰骋F1赛场46年、曾获得8次车队总冠军以及11次车手冠军且从未被出售的老牌强队来说，它完全不必担心自己在赛场的知名度。而且在当今民用跑车市场上，迈凯轮依然是英国本土品牌的佼佼者。"));
		map.put(116,
				new CarInfo(
						R.drawable.l6,
						"永源",
						"jonway",
						"永源",
						"浙江永源汽车有限公司隶属于中国浙江永源集团。中国浙江永源集团始建于公元二十世纪八十年代，历经二十年的持续建设与均衡发展，为泛长三角区域经济跨越及回报社会公益事业作出了突出贡献。"));
		map.put(117,
				new CarInfo(
						R.drawable.l7,
						"腾势",
						"DENZA",
						"腾势",
						"DENZA腾势是深圳比亚迪戴姆勒新技术有限公司所拥有的品牌，是比亚迪与戴姆勒合资双方共同打造的中国首个专注于电动汽车的品牌。DENZA源自中文名“腾势”的音译，为“腾势而起，电动未来”之意，表达出借助双方资源整合和中国及全球电动汽车崛起的大势，呈现腾飞而起的气势，带给消费者超越性的汽车生活体验。"));
		map.put(118,
				new CarInfo(
						R.drawable.l8,
						"KTM",
						"KTM",
						"KTM",
						"KTM 是奥地利的一家生产摩托的厂商，但它的强项是越野摩托，性能卓越在多次越野赛事上拿过大奖。 在达喀尔上常常会有前五名选手都是驾驶KTM的战车的壮观景象出现。 07年的达喀尔上就有两支赞助商车队一支是大家熟悉的REPSOL.KTM当家车手科马另一支是一个法国赞助商GAULOISES.KTM车队 其他也有很多草根车手选择KTM赛车可以看出KTM在越野上的性能被大家广为推崇。"));
		map.put(119,
				new CarInfo(
						R.drawable.l9,
						"奔驰Smart",
						"BenZ-Smart",
						"奔驰Smart",
						"Smart是梅赛德斯-奔驰汽车公司与手表业巨头Swatch公司合作的产物。其名称中的S代表了斯沃奇，M代表了戴姆勒公司，art是英文艺术的意思，代表了双方合作的艺术性，而Smart车名本身就有聪明伶俐的意思，也契合了smart公司的设计理念。现代都市中车辆愈来愈多，为应对这个问题，许多汽车制造商陆续提出微型都市代步用车的概念。而由Swatch所设计的smart也不例外。再加上奔驰的设计功力，让smart可以保留概念车的创意，又兼具流行及实用可靠等优点。可爱的造型，配合智能化及人性化的操控设计，如同一部聪明的大玩具。"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(120,
				new CarInfo(
						R.drawable.m0,
						"赛恩",
						"Scion",
						"赛恩",
						"Scio（中文译赛恩）既是全球最年轻的汽车品牌，同时它也是北美丰田（Toyota Moto ale, U..A.简称TM） 继Toyota、Lexus之后，旗下所拥有的第三个品牌。TM赋予cio全新的品牌生命，Scio代表的是个人化汽车时代的来临，通过全新设立的经销体系，每一位车主将有机会体验到Scio精心设计的个性化购车流程，以及买到一部与众不同的个性化Scio汽车。"));
		map.put(121,
				new CarInfo(
						R.drawable.m1,
						"特斯拉",
						"TESLA",
						"特斯拉",
						"特斯拉(Tesla)汽车公司成立于2003年，只制造纯电动车，总部设在了美国加州的硅谷地带，特斯拉Tesla汽车集独特的造型、高效的加速、良好的操控性能与先进的技术为一身，从而使其成为公路上最快且最为节省燃料的车子。"));
		map.put(122,
				new CarInfo(
						R.drawable.m2,
						"一汽",
						"----",
						"一汽",
						"中国一汽出品的奔腾造型沉稳健硕、动力充沛澎湃，是一款性能卓越、品质超群的商务轿车。全球汽车设计理念和最新科技的集成运用，使得奔腾不仅具备了世界级品质，更具有中高级轿车中最佳的安全性能及操控性能，堪称高起点、高品质和高性能的自主品牌的代表。"));
		map.put(123,
				new CarInfo(
						R.drawable.m3,
						"欧朗",
						"Oley",
						"欧朗",
						"一汽欧朗是中国一汽为年轻消费群体倾力打造的全新品牌，着力通过“激情夺目的时尚”、“国际一流的品质”、“可感知的科技”、“360°安全”四大商品卖点，为年轻一族带来全新的驾乘享受。"));
		map.put(124,
				new CarInfo(
						R.drawable.m4,
						"哈弗",
						"HAVAL",
						"哈弗",
						"哈弗是长城汽车定位为CUV车型(City Utility Vehicle)，其中文 意思是指城市多功能车， 05年哈弗英文名为“HOVER”，代表“自由翱翔”。由于06年开始批量出口欧盟，在国际市场有了一定影响，原来的英文名字已被很多国家注册。只好重新寻找了一个在英文里没有实际意义、全球发音更一致的单词：“HAVAL”。我们想赋予它have all(无所不能)的含义，这就是LOGO “HAVAL”的起源。"));
		map.put(125,
				new CarInfo(R.drawable.m5, "达西亚", "Dacia", "达西亚",
						"达西亚——雷诺汽车公司旗下品牌之一。Dacia达西亚是与罗马尼亚的汽车工业一起成厂起来的汽车品牌，成立于1966年。与雷诺汽车的合作是达西亚快速发展的开始。"));
		map.put(126,
				new CarInfo(
						R.drawable.m6,
						"Fisker",
						"Fisker",
						"Fisker",
						"美国帕萨迪纳艺术中心设计学院(Art Center College)可谓是设计大师的“生产基地”，克里斯·班戈、J·梅斯、马丁·史密斯等都是从这所院校里走出来设计大师。其中，还有一位非常知名的大师级设计师，他不仅仅是一名设计师那么简单，他更是一家新兴的跑车品牌的总裁，亨德里克·菲斯克(Henrik Fisker)，Fisker汽车的领导者。"));
		map.put(127,
				new CarInfo(
						R.drawable.m7,
						"之诺",
						"ZINORO",
						"之诺",
						"2013年，华晨宝马做出承诺-----打造一个属于中国的高档汽车品牌。从诞生，直到践行，言出必行。随之将这个品牌命名为“之诺” ，并根据中文发展出英文“ZINORO”，“之”字代表其中国文化的属性，“诺”是指信守承诺。“之诺”的首款产品是一款采用全电力驱动技术的汽车，计划在2013年底广州车展进行世界首发，并于2014年第一季度推向市场。"));
		map.put(128,
				new CarInfo(
						R.drawable.m8,
						"Noble",
						"Noble",
						"Noble",
						"Noble的首席设计师诺贝尔·李(Noble Lee)，驱动他造车的冲动不是因为金钱，而是他对于跑车有着高涨的热情。1999年，诺贝尔·李在英国西约克郡的利兹开始他的小众跑车之路，最先利兹的工厂只制造的只有发动机。"));
		map.put(129, new CarInfo(R.drawable.m9, "摩根汽车", "morgan", "奔驰摩根汽车",
				"摩根汽车公司是一家有100年历史的英国汽车公司。自1902年推出首辆三轮汽车至今，这个地方几乎就没有改变过。"));

		map.put(130,
				new CarInfo(R.drawable.n0, "九龙汽车", "joylong", "九龙汽车",
						"公司于2007年注册成立，是主要从事豪华商务车及大中型客车研发、生产和销售、服务的企业。扬州江都区南临长江，北接淮水，东望上海，地处上海经济圈和南京都市圈交汇地带，交通便捷。"));
		map.put(131,
				new CarInfo(R.drawable.n1, "华骐", "HORKI", "华骐",
						"日前据国内媒体报道，东风悦达起亚总经理苏南永先生在专访时透露，旗下自主品牌华骐(HORKI)首款车HORKI-1的量产计划定于2015年上半年左右。"));
		map.put(132,
				new CarInfo(
						R.drawable.n2,
						"恒天汽车",
						"CHTC",
						"恒天汽车",
						"保定恒天汽车投资有限公司是国务院国资委所属企业-中国恒天集团有限公司下属子公司，是恒天集团进军汽车业的重要平台之一，致力于在皮卡和SUV细分市场上作出自己的特色。将针对中国汽车消费者的需求特点，为中国消费者精心打造适合中国国情的皮卡和SUV产品。"));
		map.put(133, new CarInfo(R.drawable.n3, "Hennessey", "Hennessey ", "Hennessey",
				"Hennessey---产　　地：美国"));
		map.put(134, new CarInfo(R.drawable.n4, "福汽新龙马", "NLM MOTOR", "福汽新龙马",
				"福汽新龙马是一个微车系自主研发品牌，隶属于福建省汽车工业集团控股公司，具有完全自主知识产权。"));
		map.put(135,
				new CarInfo(
						R.drawable.n5,
						"沃克斯豪尔",
						"VAUXHALL",
						"沃克斯豪尔",
						"　沃克斯豪尔汽车公司 (Vauxhall Motors)的历史可以追溯到1857年。当时苏格兰工程师亚历山大·威尔逊在英国沃克斯豪尔地区建立了一家生产蒸气机的工厂，最初的业务是制造船用发动机和铸件，这就是沃克斯豪尔车厂的前身。公司选用了十三世纪这片土地的领主Fulkle Breant使用的怪兽griffin徽标作为标志。"));
		map.put(136,
				new CarInfo(
						R.drawable.n6,
						"斯派朗",
						"Spirra",
						"斯派朗",
						"2010年12月29日，韩国?超级跑车Spirra在上海发布，正式进口中国，中文定名斯派朗。共分四款车型，售价98-188万人民币。Spirra-N售价98万元 ，Spirra-S售价118万元，Spirra-Turbo售价138万元，Spirra-EX售价188万元。该车型2011年预计销售30台"));
		map.put(137, new CarInfo(R.drawable.n7, "----", "----", "----", ""));
		map.put(138, new CarInfo(R.drawable.n8, "----", "----", "----", ""));
		map.put(139, new CarInfo(R.drawable.n9, "----", "----", "----", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */

	}

	@Override
	public void receiver(Context context, Intent intent) {
		dayOrNight();
	}

	/** 白天或黑夜 */
	public void dayOrNight() {
		if (isNight) {
			carDetailLayout.setBackgroundColor(Color.parseColor("#333333"));
			carDescription.setBackgroundResource(R.drawable.car_night_name_bg);
			carIconLayout.setBackgroundColor(Color.parseColor("#55667788"));
			carStory.setTextColor(Color.parseColor("#21EEFE"));
		} else {
			carStory.setTextColor(Color.parseColor("#666869"));
			carIconLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
			carDetailLayout.setBackgroundColor(Color.parseColor("#F4F5F7"));
			carDescription.setBackgroundResource(R.drawable.car_name_bg);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		dayOrNight();
	}

}
