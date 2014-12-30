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
		if (currentIndex != -1) {
			if (map.get(currentIndex) == null) {
				map.put(currentIndex, new CarInfo(currentIndex));
			}
			carInfo = map.get(currentIndex);
			carLogo.setImageResource(carInfo.getResId());
			carDetailInfo.setText(carInfo.getText());
			carDetailInfo.setTextSize(25);
			carDetailInfo.setSelected(true);
			carDetailInfo.setTextColor(Color.parseColor("#74DCFF"));
			englishName.setText("Rolls-Royce");
			chinaName.setText("劳斯莱斯");
			carDescription.setText("劳斯莱斯以一个“贵族化”的汽车公司享誉全球，同时也是目前世界三大航空发动机生产商之一。");
		}
	}

	private static LinkedHashMap<Integer, CarInfo> map;

	// private static List<CarInfo> list;

	/** 获取fragment */
	public static Fragment newInstance(int currentIndex) {
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

	public static String PARCELABLE_KEY = "hehe";
	private CarInfo carInfo;
	private TextView englishName;
	private TextView chinaName;
	/** 汽车简介 */
	private TextView carDescription;
	static {
		map = new LinkedHashMap<Integer, CarInfo>();
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(0, new CarInfo(R.drawable.rolls_royce, "劳斯莱斯"));
		map.put(1, new CarInfo(R.drawable.porsche, "保时捷"));
		map.put(2, new CarInfo(R.drawable.lotus, "路特斯"));
		map.put(3, new CarInfo(R.drawable.malassas, "马萨拉蒂"));
		map.put(4, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(5, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(6, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(7, new CarInfo(R.drawable.audi, "奥迪"));
		map.put(8, new CarInfo(R.drawable.bmw, "宝马"));
		map.put(9, new CarInfo(R.drawable.bentley, "宾利"));

		map.put(10, new CarInfo(R.drawable.volvo, "沃尔沃"));
		map.put(11, new CarInfo(R.drawable.landrover, "路虎"));
		map.put(12, new CarInfo(R.drawable.jaguar, "捷豹"));
		map.put(13, new CarInfo(R.drawable.jeep, "吉普"));
		map.put(14, new CarInfo(R.drawable.benz, "奔驰"));

		map.put(15, new CarInfo(R.drawable.maybach, "迈巴赫"));
		map.put(16, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(17, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(18, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(19, new CarInfo(R.drawable.maybach, "迈巴赫"));

		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(20, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(21, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(22, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(23, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(24, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(25, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(26, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(27, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(28, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(29, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(30, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(31, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(32, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(33, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(34, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(35, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(36, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(37, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(38, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(39, new CarInfo(R.drawable.maybach, "迈巴赫"));

		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(40, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(41, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(42, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(43, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(44, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(45, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(46, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(47, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(48, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(49, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(50, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(51, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(52, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(53, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(54, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(55, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(56, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(57, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(58, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(59, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(60, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(61, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(62, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(63, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(64, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(65, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(66, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(67, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(68, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(69, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(70, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(71, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(72, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(73, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(74, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(75, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(76, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(77, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(78, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(79, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(80, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(81, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(82, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(83, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(84, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(85, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(86, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(87, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(88, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(89, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(90, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(91, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(92, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(93, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(94, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(95, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(96, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(97, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(98, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(99, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(100, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(101, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(102, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(103, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(104, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(105, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(106, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(107, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(108, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(109, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(110, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(111, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(112, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(113, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(114, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(115, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(116, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(117, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(118, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(119, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(120, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(121, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(122, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(123, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(124, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(125, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(126, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(127, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(128, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(129, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(130, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(131, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(132, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(133, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(134, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(135, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(136, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(137, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(138, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(139, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(140, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(141, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(142, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(143, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(144, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(145, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(146, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(147, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(148, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(149, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(150, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(151, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(152, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(153, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(154, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(155, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(156, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(157, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(158, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(159, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(160, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(161, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(162, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(163, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(164, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(165, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(166, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(167, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(168, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(169, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(170, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(171, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(172, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(173, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(174, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(175, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(176, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(177, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(178, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(179, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(180, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(181, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(182, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(183, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(184, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(185, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(186, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(187, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(188, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(189, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(190, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(191, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(192, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(193, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(194, new CarInfo(R.drawable.maybach, "迈巴赫"));

		map.put(195, new CarInfo(R.drawable.ferrari, "法拉利"));
		map.put(196, new CarInfo(R.drawable.lamborghini, "兰博基尼"));
		map.put(197, new CarInfo(R.drawable.lotus, "路特事特"));
		map.put(198, new CarInfo(R.drawable.malassas, "马拉萨蒂"));
		map.put(199, new CarInfo(R.drawable.maybach, "迈巴赫"));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
	}

}
