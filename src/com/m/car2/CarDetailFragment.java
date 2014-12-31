package com.m.car2;

import java.util.LinkedHashMap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CarDetailFragment extends Fragment implements OnClickListener {
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

	/** 200个品牌 */
	private void car200() {
		Log.e("car", "CarDetailFragment -- run---------->");
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
						if (storyContent == null) {
							Tools.showProgressDialog(getActivity(), "正在加载....");
							new Thread() {
								public void run() {
									storyContent = Tools.loadData(getActivity(),
											position % 15);
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
		carStory.setTextColor(Color.parseColor("#666869"));
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
	static {
		map = new LinkedHashMap<Integer, CarInfo>();
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(0, new CarInfo(R.drawable.a0, "劳斯莱斯", "Rolls-Royce", "劳斯莱斯", "劳斯莱斯以一个“贵族化”的汽车公司享誉全球，同时也是目前世界三大航空发动机生产商之一。"));
		map.put(1, new CarInfo(R.drawable.a1, "宾利", "Bentley", "宾利", "宾利(又译作本特利)(Bentley)轿车标志是以公司名的第一个字母“B”为主体，生出一对翅膀，似凌空翱翔的雄鹰，此标志一直沿用至今，过去曾用过一个展翅飞翔的“B”标志。"));
		map.put(2, new CarInfo(R.drawable.a2, "保时捷", "Porsche", "保时捷", "保时捷的英文车标引采用德国保时捷公司创始人费迪南德·保时捷的姓氏。图形车标采用公司所在地斯图加特市的盾形市徽。"));
		map.put(3, new CarInfo(R.drawable.a3, "法拉利", "", "法拉利", ""));
		map.put(4, new CarInfo(R.drawable.a4, "玛莎拉蒂", "", "玛莎拉蒂", ""));
		map.put(7, new CarInfo(R.drawable.a5, "兰博基尼", "", "兰博基尼", ""));
		map.put(5, new CarInfo(R.drawable.a6, "奔驰", "", "奔驰", ""));
		map.put(6, new CarInfo(R.drawable.a7, "宝马", "", "宝马", ""));
		map.put(8, new CarInfo(R.drawable.a8, "迈巴赫", "", "迈巴赫", ""));
		map.put(9, new CarInfo(R.drawable.a9, "路斯特", "", "路斯特", ""));

		map.put(10, new CarInfo(R.drawable.b0, "奥迪", "", "奥迪", ""));
		map.put(11, new CarInfo(R.drawable.b1, "悍马", "", "悍马", ""));
		map.put(12, new CarInfo(R.drawable.b2, "路虎", "", "路虎", ""));
		map.put(13, new CarInfo(R.drawable.b3, "莲花", "", "莲花", ""));
		map.put(14, new CarInfo(R.drawable.b4, "阿尔法·罗密欧", "", "阿尔法·罗密欧", ""));
		map.put(15, new CarInfo(R.drawable.b5, "吉利", "", "吉利", ""));
		map.put(16, new CarInfo(R.drawable.b6, "英菲尼迪", "", "英菲尼迪", ""));
		map.put(17, new CarInfo(R.drawable.b7, "铃木", "", "铃木", ""));
		map.put(18, new CarInfo(R.drawable.b8, "雪铁龙", "", "雪铁龙", ""));
		map.put(19, new CarInfo(R.drawable.b9, "长安", "", "长安", ""));

		map.put(20, new CarInfo(R.drawable.c0, "长安商用", "", "长安商用", ""));
		map.put(21, new CarInfo(R.drawable.c1, "名爵", "", "名爵", ""));
		map.put(22, new CarInfo(R.drawable.c2, "开瑞", "", "开瑞", ""));
		map.put(23, new CarInfo(R.drawable.c3, "海马", "", "海马", ""));
		map.put(24, new CarInfo(R.drawable.c4, "东风", "", "东风", ""));
		map.put(25, new CarInfo(R.drawable.c5, "吉利全球鹰", "", "吉利全球鹰", ""));
		map.put(26, new CarInfo(R.drawable.c6, "日产", "", "日产", ""));
		map.put(27, new CarInfo(R.drawable.c7, "丰田", "", "丰田", ""));
		map.put(28, new CarInfo(R.drawable.c8, "欧宝", "", "欧宝", ""));
		map.put(29, new CarInfo(R.drawable.c9, "捷豹", "", "捷豹", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(30, new CarInfo(R.drawable.d0, "福特野马", "", "福特野马", ""));
		map.put(31, new CarInfo(R.drawable.d1, "别克", "", "别克", ""));
		map.put(32, new CarInfo(R.drawable.d2, "沃尔沃", "", "沃尔沃", ""));
		map.put(33, new CarInfo(R.drawable.d3, "斯柯达", "", "斯柯达", ""));
		map.put(34, new CarInfo(R.drawable.d4, "荣威", "", "荣威", ""));
		map.put(35, new CarInfo(R.drawable.d5, "吉利帝豪", "", "吉利帝豪", ""));
		map.put(36, new CarInfo(R.drawable.d6, "英伦", "", "英伦", ""));
		map.put(37, new CarInfo(R.drawable.d7, "瑞麒", "", "瑞麒 	", ""));
		map.put(38, new CarInfo(R.drawable.d8, "斯巴鲁", "", "斯巴鲁", ""));
		map.put(39, new CarInfo(R.drawable.d9, "威麟", "", "威麟", ""));

		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(40, new CarInfo(R.drawable.e0, "标致", "", "标致", ""));
		map.put(41, new CarInfo(R.drawable.e1, "启辰", "", "启辰", ""));
		map.put(42, new CarInfo(R.drawable.e2, "本田", "", "本田", ""));
		map.put(43, new CarInfo(R.drawable.e3, "GMC", "GMC", "GMC", ""));
		map.put(44, new CarInfo(R.drawable.e4, "宝马Mini", "", "宝马Mini", ""));
		map.put(45, new CarInfo(R.drawable.e5, "马自达", "", "马自达", ""));
		map.put(46, new CarInfo(R.drawable.e6, "长城", "", "长城", ""));
		map.put(47, new CarInfo(R.drawable.e7, "理念", "", "理念", ""));
		map.put(48, new CarInfo(R.drawable.e8, "大众", "", "大众", ""));
		map.put(49, new CarInfo(R.drawable.e9, "陆风", "", "陆风", ""));

		map.put(50, new CarInfo(R.drawable.f0, "雪佛兰", "", "雪佛兰", ""));
		map.put(51, new CarInfo(R.drawable.f1, "福特", "", "福特", ""));
		map.put(52, new CarInfo(R.drawable.f2, "起亚", "", "起亚", ""));
		map.put(53, new CarInfo(R.drawable.f3, "萨博", "", "萨博", ""));
		map.put(54, new CarInfo(R.drawable.f4, "华普", "", "华普", ""));
		map.put(55, new CarInfo(R.drawable.f5, "中华", "", "中华", ""));
		map.put(56, new CarInfo(R.drawable.f6, "奇瑞", "", "奇瑞", ""));
		map.put(57, new CarInfo(R.drawable.f7, "阿斯顿·马丁", "", "阿斯顿·马丁", ""));
		map.put(58, new CarInfo(R.drawable.f8, "夏利", "", "夏利", ""));
		map.put(59, new CarInfo(R.drawable.f9, "雷克萨斯", "", "雷克萨斯", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(60, new CarInfo(R.drawable.g0, "现代", "", "现代", ""));
		map.put(61, new CarInfo(R.drawable.g1, "吉奥", "", "吉奥", ""));
		map.put(62, new CarInfo(R.drawable.g2, "菲亚特", "", "菲亚特", ""));
		map.put(63, new CarInfo(R.drawable.g3, "凯迪拉克", "", "凯迪拉克", ""));
		map.put(64, new CarInfo(R.drawable.g4, "北汽威旺", "", "北汽威旺", ""));
		map.put(65, new CarInfo(R.drawable.g5, "帕加尼", "", "帕加尼", ""));
		map.put(66, new CarInfo(R.drawable.g6, "西雅特", "", "西雅特", ""));
		map.put(67, new CarInfo(R.drawable.g7, "现代劳恩斯", "", "现代劳恩斯", ""));
		map.put(68, new CarInfo(R.drawable.g8, "众泰", "", "众泰", ""));
		map.put(69, new CarInfo(R.drawable.g9, "克莱斯勒", "", "克莱斯勒", ""));

		map.put(70, new CarInfo(R.drawable.h0, "江淮", "", "江淮", ""));
		map.put(71, new CarInfo(R.drawable.h1, "雷诺", "", "雷诺", ""));
		map.put(72, new CarInfo(R.drawable.h2, "科尼赛克", "", "科尼赛克", ""));
		map.put(73, new CarInfo(R.drawable.h3, "巴博斯", "", "巴博斯", ""));
		map.put(74, new CarInfo(R.drawable.h4, "宝骏", "", "宝骏", ""));
		map.put(75, new CarInfo(R.drawable.h5, "北京汽车", "", "北京汽车", ""));
		map.put(76, new CarInfo(R.drawable.h6, "北汽制造", "", "北汽制造", ""));
		map.put(77, new CarInfo(R.drawable.h7, "奔腾", "", "奔腾", ""));
		map.put(78, new CarInfo(R.drawable.h8, "比亚迪", "", "比亚迪", ""));
		map.put(79, new CarInfo(R.drawable.h9, "布加迪", "", "布加迪", ""));
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
		map.put(80, new CarInfo(R.drawable.i0, "昌河", "", "昌河", ""));
		map.put(81, new CarInfo(R.drawable.i1, "川汽野马", "", "川汽野马", ""));
		map.put(82, new CarInfo(R.drawable.i2, "DS", "DS", "DS", ""));
		map.put(83, new CarInfo(R.drawable.i3, "大发", "", "大发", ""));
		map.put(84, new CarInfo(R.drawable.i4, "道奇", "", "道奇", ""));
		map.put(85, new CarInfo(R.drawable.i5, "东南", "", "东南", ""));
		map.put(86, new CarInfo(R.drawable.i6, "福迪", "", "福迪", ""));
		map.put(87, new CarInfo(R.drawable.i7, "福田", "", "福田", ""));
		map.put(88, new CarInfo(R.drawable.i8, "观致", "", "观致", ""));
		map.put(89, new CarInfo(R.drawable.i9, "广汽传祺", "", "广汽传祺", ""));

		map.put(90, new CarInfo(R.drawable.j0, "哈飞", "", "哈飞", ""));
		map.put(91, new CarInfo(R.drawable.j1, "海格", "", "海格", ""));
		map.put(92, new CarInfo(R.drawable.j2, "红旗", "", "红旗", ""));
		map.put(93, new CarInfo(R.drawable.j3, "华泰", "", "华泰", ""));
		map.put(94, new CarInfo(R.drawable.j4, "黄海", "", "黄海", ""));
		map.put(95, new CarInfo(R.drawable.j5, "吉普", "", "吉普", ""));
		map.put(96, new CarInfo(R.drawable.j6, "江铃", "", "江铃", ""));
		map.put(97, new CarInfo(R.drawable.j7, "金杯", "", "金杯", ""));
		map.put(98, new CarInfo(R.drawable.j8, "AC Schnitzer", "AC Schnitzer",
				"AC Schnitzer", ""));
		map.put(99, new CarInfo(R.drawable.j9, "中兴", "", "中兴", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(100, new CarInfo(R.drawable.k0, "威兹曼", "", "威兹曼", ""));
		map.put(101, new CarInfo(R.drawable.k1, "双环", "", "双环", ""));
		map.put(102, new CarInfo(R.drawable.k2, "世爵", "", "世爵", ""));
		map.put(103, new CarInfo(R.drawable.k3, "三菱", "", "三菱", ""));
		map.put(104, new CarInfo(R.drawable.k4, "力帆", "", "力帆", ""));
		map.put(105, new CarInfo(R.drawable.k5, "卡尔森", "", "卡尔森", ""));
		map.put(106, new CarInfo(R.drawable.k6, "讴歌", "", "讴歌", ""));
		map.put(107, new CarInfo(R.drawable.k7, "思铭", "", "思铭", ""));
		map.put(108, new CarInfo(R.drawable.k8, "猎豹", "", "猎豹", ""));
		map.put(109, new CarInfo(R.drawable.k9, "双龙", "", "双龙", ""));

		map.put(110, new CarInfo(R.drawable.l0, "Gumpert", "Gumpert", "Gumpert", ""));
		map.put(111, new CarInfo(R.drawable.l1, "光冈", "", "光冈", ""));
		map.put(112, new CarInfo(R.drawable.l2, "凯佰赫", "", "凯佰赫", ""));
		map.put(113, new CarInfo(R.drawable.l3, "纳智捷", "", "纳智捷", ""));
		map.put(114, new CarInfo(R.drawable.l4, "林肯", "", "林肯", ""));
		map.put(115, new CarInfo(R.drawable.l5, "迈凯轮", "", "迈凯轮", ""));
		map.put(116, new CarInfo(R.drawable.l6, "永源", "", "永源", ""));
		map.put(117, new CarInfo(R.drawable.l7, "腾势", "", "腾势", ""));
		map.put(118, new CarInfo(R.drawable.l8, "KTM", "KTM", "KTM", ""));
		map.put(119, new CarInfo(R.drawable.l9, "奔驰Smart", "BenZ-Smart", "奔驰Smart", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(120, new CarInfo(R.drawable.m0, "赛恩", "", "赛恩", ""));
		map.put(121, new CarInfo(R.drawable.m1, "特斯拉", "", "特斯拉", ""));
		map.put(122, new CarInfo(R.drawable.m2, "一汽", "", "一汽", ""));
		map.put(123, new CarInfo(R.drawable.m3, "欧朗", "", "欧朗", ""));
		map.put(124, new CarInfo(R.drawable.m4, "哈弗", "", "哈弗", ""));
		map.put(125, new CarInfo(R.drawable.m5, "达西亚", "", "达西亚", ""));
		map.put(126, new CarInfo(R.drawable.m6, "Fisker", "Fisker", "Fisker", ""));
		map.put(127, new CarInfo(R.drawable.m7, "之诺", "", "之诺", ""));
		map.put(128, new CarInfo(R.drawable.m8, "Noble", "Noble", "Noble", ""));
		map.put(129, new CarInfo(R.drawable.m9, "摩根汽车", "", "奔驰摩根汽车", ""));

		map.put(130, new CarInfo(R.drawable.n0, "九龙汽车", "", "九龙汽车", ""));
		map.put(131, new CarInfo(R.drawable.n1, "华骐", "", "华骐", ""));
		map.put(132, new CarInfo(R.drawable.n2, "恒天汽车", "", "恒天汽车", ""));
		map.put(133, new CarInfo(R.drawable.n3, "Hennessey", "Hennessey ", "Hennessey",
				""));
		map.put(134, new CarInfo(R.drawable.n4, "福汽新龙马", "", "福汽新龙马", ""));
		map.put(135, new CarInfo(R.drawable.n5, "沃克斯豪尔", "", "沃克斯豪尔", ""));
		map.put(136, new CarInfo(R.drawable.n6, "斯派朗", "", "斯派朗", ""));
		map.put(137, new CarInfo(R.drawable.n7, "----", "----", "----", ""));
		map.put(138, new CarInfo(R.drawable.n8, "----", "----", "----", ""));
		map.put(139, new CarInfo(R.drawable.n9, "----", "----", "----", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */

	}

}
