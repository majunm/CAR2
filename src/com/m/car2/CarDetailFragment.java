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
	/** ����logo��ʾ */
	private ImageView carLogo;
	/** ������� */
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

	/** ��ʼ�� */
	private View init() {
		View v = View.inflate(getActivity(), R.layout.car_item, null);
		findView(v);
		car200();
		return v;
	}

	/** ��ʼ���ؼ� */
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

	/** ע����� */
	private void registerListener() {
		carStory.setOnClickListener(this);
		carStoryScorll.setOnClickListener(this);
		mainLayout.setOnClickListener(this);
		carLogo.setOnClickListener(this);
		carDetailInfo.setOnClickListener(this);
	}

	/** 200��Ʒ�� */
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

	/** ��ȡfragment */
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
	/** �������� */
	private String storyContent = null;

	private void flip(final int position) {
		runAnim(position);
	}

	/** ִ�ж��� */
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
							Tools.showProgressDialog(getActivity(), "���ڼ���....");
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
	/** ������� */
	private TextView carDescription;
	static {
		map = new LinkedHashMap<Integer, CarInfo>();
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(0, new CarInfo(R.drawable.a0, "��˹��˹", "Rolls-Royce", "��˹��˹", "��˹��˹��һ�������廯����������˾����ȫ��ͬʱҲ��Ŀǰ�������󺽿շ�����������֮һ��"));
		map.put(1, new CarInfo(R.drawable.a1, "����", "Bentley", "����", "����(������������)(Bentley)�γ���־���Թ�˾���ĵ�һ����ĸ��B��Ϊ���壬����һ�Գ������հ������ӥ���˱�־һֱ�������񣬹�ȥ���ù�һ��չ�����ġ�B����־��"));
		map.put(2, new CarInfo(R.drawable.a2, "��ʱ��", "Porsche", "��ʱ��", "��ʱ�ݵ�Ӣ�ĳ��������õ¹���ʱ�ݹ�˾��ʼ�˷ѵ��ϵ¡���ʱ�ݵ����ϡ�ͼ�γ�����ù�˾���ڵ�˹ͼ�����еĶ����лա�"));
		map.put(3, new CarInfo(R.drawable.a3, "������", "", "������", ""));
		map.put(4, new CarInfo(R.drawable.a4, "��ɯ����", "", "��ɯ����", ""));
		map.put(7, new CarInfo(R.drawable.a5, "��������", "", "��������", ""));
		map.put(5, new CarInfo(R.drawable.a6, "����", "", "����", ""));
		map.put(6, new CarInfo(R.drawable.a7, "����", "", "����", ""));
		map.put(8, new CarInfo(R.drawable.a8, "���ͺ�", "", "���ͺ�", ""));
		map.put(9, new CarInfo(R.drawable.a9, "·˹��", "", "·˹��", ""));

		map.put(10, new CarInfo(R.drawable.b0, "�µ�", "", "�µ�", ""));
		map.put(11, new CarInfo(R.drawable.b1, "����", "", "����", ""));
		map.put(12, new CarInfo(R.drawable.b2, "·��", "", "·��", ""));
		map.put(13, new CarInfo(R.drawable.b3, "����", "", "����", ""));
		map.put(14, new CarInfo(R.drawable.b4, "������������ŷ", "", "������������ŷ", ""));
		map.put(15, new CarInfo(R.drawable.b5, "����", "", "����", ""));
		map.put(16, new CarInfo(R.drawable.b6, "Ӣ�����", "", "Ӣ�����", ""));
		map.put(17, new CarInfo(R.drawable.b7, "��ľ", "", "��ľ", ""));
		map.put(18, new CarInfo(R.drawable.b8, "ѩ����", "", "ѩ����", ""));
		map.put(19, new CarInfo(R.drawable.b9, "����", "", "����", ""));

		map.put(20, new CarInfo(R.drawable.c0, "��������", "", "��������", ""));
		map.put(21, new CarInfo(R.drawable.c1, "����", "", "����", ""));
		map.put(22, new CarInfo(R.drawable.c2, "����", "", "����", ""));
		map.put(23, new CarInfo(R.drawable.c3, "����", "", "����", ""));
		map.put(24, new CarInfo(R.drawable.c4, "����", "", "����", ""));
		map.put(25, new CarInfo(R.drawable.c5, "����ȫ��ӥ", "", "����ȫ��ӥ", ""));
		map.put(26, new CarInfo(R.drawable.c6, "�ղ�", "", "�ղ�", ""));
		map.put(27, new CarInfo(R.drawable.c7, "����", "", "����", ""));
		map.put(28, new CarInfo(R.drawable.c8, "ŷ��", "", "ŷ��", ""));
		map.put(29, new CarInfo(R.drawable.c9, "�ݱ�", "", "�ݱ�", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(30, new CarInfo(R.drawable.d0, "����Ұ��", "", "����Ұ��", ""));
		map.put(31, new CarInfo(R.drawable.d1, "���", "", "���", ""));
		map.put(32, new CarInfo(R.drawable.d2, "�ֶ���", "", "�ֶ���", ""));
		map.put(33, new CarInfo(R.drawable.d3, "˹�´�", "", "˹�´�", ""));
		map.put(34, new CarInfo(R.drawable.d4, "����", "", "����", ""));
		map.put(35, new CarInfo(R.drawable.d5, "�����ۺ�", "", "�����ۺ�", ""));
		map.put(36, new CarInfo(R.drawable.d6, "Ӣ��", "", "Ӣ��", ""));
		map.put(37, new CarInfo(R.drawable.d7, "����", "", "���� 	", ""));
		map.put(38, new CarInfo(R.drawable.d8, "˹��³", "", "˹��³", ""));
		map.put(39, new CarInfo(R.drawable.d9, "����", "", "����", ""));

		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(40, new CarInfo(R.drawable.e0, "����", "", "����", ""));
		map.put(41, new CarInfo(R.drawable.e1, "����", "", "����", ""));
		map.put(42, new CarInfo(R.drawable.e2, "����", "", "����", ""));
		map.put(43, new CarInfo(R.drawable.e3, "GMC", "GMC", "GMC", ""));
		map.put(44, new CarInfo(R.drawable.e4, "����Mini", "", "����Mini", ""));
		map.put(45, new CarInfo(R.drawable.e5, "���Դ�", "", "���Դ�", ""));
		map.put(46, new CarInfo(R.drawable.e6, "����", "", "����", ""));
		map.put(47, new CarInfo(R.drawable.e7, "����", "", "����", ""));
		map.put(48, new CarInfo(R.drawable.e8, "����", "", "����", ""));
		map.put(49, new CarInfo(R.drawable.e9, "½��", "", "½��", ""));

		map.put(50, new CarInfo(R.drawable.f0, "ѩ����", "", "ѩ����", ""));
		map.put(51, new CarInfo(R.drawable.f1, "����", "", "����", ""));
		map.put(52, new CarInfo(R.drawable.f2, "����", "", "����", ""));
		map.put(53, new CarInfo(R.drawable.f3, "����", "", "����", ""));
		map.put(54, new CarInfo(R.drawable.f4, "����", "", "����", ""));
		map.put(55, new CarInfo(R.drawable.f5, "�л�", "", "�л�", ""));
		map.put(56, new CarInfo(R.drawable.f6, "����", "", "����", ""));
		map.put(57, new CarInfo(R.drawable.f7, "��˹�١���", "", "��˹�١���", ""));
		map.put(58, new CarInfo(R.drawable.f8, "����", "", "����", ""));
		map.put(59, new CarInfo(R.drawable.f9, "�׿���˹", "", "�׿���˹", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(60, new CarInfo(R.drawable.g0, "�ִ�", "", "�ִ�", ""));
		map.put(61, new CarInfo(R.drawable.g1, "����", "", "����", ""));
		map.put(62, new CarInfo(R.drawable.g2, "������", "", "������", ""));
		map.put(63, new CarInfo(R.drawable.g3, "��������", "", "��������", ""));
		map.put(64, new CarInfo(R.drawable.g4, "��������", "", "��������", ""));
		map.put(65, new CarInfo(R.drawable.g5, "������", "", "������", ""));
		map.put(66, new CarInfo(R.drawable.g6, "������", "", "������", ""));
		map.put(67, new CarInfo(R.drawable.g7, "�ִ��Ͷ�˹", "", "�ִ��Ͷ�˹", ""));
		map.put(68, new CarInfo(R.drawable.g8, "��̩", "", "��̩", ""));
		map.put(69, new CarInfo(R.drawable.g9, "����˹��", "", "����˹��", ""));

		map.put(70, new CarInfo(R.drawable.h0, "����", "", "����", ""));
		map.put(71, new CarInfo(R.drawable.h1, "��ŵ", "", "��ŵ", ""));
		map.put(72, new CarInfo(R.drawable.h2, "��������", "", "��������", ""));
		map.put(73, new CarInfo(R.drawable.h3, "�Ͳ�˹", "", "�Ͳ�˹", ""));
		map.put(74, new CarInfo(R.drawable.h4, "����", "", "����", ""));
		map.put(75, new CarInfo(R.drawable.h5, "��������", "", "��������", ""));
		map.put(76, new CarInfo(R.drawable.h6, "��������", "", "��������", ""));
		map.put(77, new CarInfo(R.drawable.h7, "����", "", "����", ""));
		map.put(78, new CarInfo(R.drawable.h8, "���ǵ�", "", "���ǵ�", ""));
		map.put(79, new CarInfo(R.drawable.h9, "���ӵ�", "", "���ӵ�", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		// map.put(80, new CarInfo(R.drawable.ferrari, "������"));
		// map.put(81, new CarInfo(R.drawable.lamborghini, "��������"));
		// map.put(82, new CarInfo(102));
		// map.put(83, new CarInfo(R.drawable.malassas, "��������"));
		// map.put(84, new CarInfo(R.drawable.maybach, "���ͺ�"));
		//
		// map.put(85, new CarInfo(R.drawable.ferrari, "������"));
		// map.put(86, new CarInfo(R.drawable.lamborghini, "��������"));
		// map.put(87, new CarInfo(102));
		// map.put(88, new CarInfo(R.drawable.malassas, "��������"));
		// map.put(89, new CarInfo(R.drawable.maybach, "���ͺ�"));
		//
		// map.put(90, new CarInfo(R.drawable.ferrari, "������"));
		// map.put(91, new CarInfo(R.drawable.lamborghini, "��������"));
		// map.put(92, new CarInfo(102));
		// map.put(93, new CarInfo(R.drawable.malassas, "��������"));
		// map.put(94, new CarInfo(R.drawable.maybach, "���ͺ�"));
		//
		// map.put(95, new CarInfo(R.drawable.ferrari, "������"));
		// map.put(96, new CarInfo(R.drawable.lamborghini, "��������"));
		// map.put(97, new CarInfo(102));
		// map.put(98, new CarInfo(R.drawable.malassas, "��������"));
		// map.put(99, new CarInfo(R.drawable.maybach, "���ͺ�"));
		map.put(80, new CarInfo(R.drawable.i0, "����", "", "����", ""));
		map.put(81, new CarInfo(R.drawable.i1, "����Ұ��", "", "����Ұ��", ""));
		map.put(82, new CarInfo(R.drawable.i2, "DS", "DS", "DS", ""));
		map.put(83, new CarInfo(R.drawable.i3, "��", "", "��", ""));
		map.put(84, new CarInfo(R.drawable.i4, "����", "", "����", ""));
		map.put(85, new CarInfo(R.drawable.i5, "����", "", "����", ""));
		map.put(86, new CarInfo(R.drawable.i6, "����", "", "����", ""));
		map.put(87, new CarInfo(R.drawable.i7, "����", "", "����", ""));
		map.put(88, new CarInfo(R.drawable.i8, "����", "", "����", ""));
		map.put(89, new CarInfo(R.drawable.i9, "��������", "", "��������", ""));

		map.put(90, new CarInfo(R.drawable.j0, "����", "", "����", ""));
		map.put(91, new CarInfo(R.drawable.j1, "����", "", "����", ""));
		map.put(92, new CarInfo(R.drawable.j2, "����", "", "����", ""));
		map.put(93, new CarInfo(R.drawable.j3, "��̩", "", "��̩", ""));
		map.put(94, new CarInfo(R.drawable.j4, "�ƺ�", "", "�ƺ�", ""));
		map.put(95, new CarInfo(R.drawable.j5, "����", "", "����", ""));
		map.put(96, new CarInfo(R.drawable.j6, "����", "", "����", ""));
		map.put(97, new CarInfo(R.drawable.j7, "��", "", "��", ""));
		map.put(98, new CarInfo(R.drawable.j8, "AC Schnitzer", "AC Schnitzer",
				"AC Schnitzer", ""));
		map.put(99, new CarInfo(R.drawable.j9, "����", "", "����", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(100, new CarInfo(R.drawable.k0, "������", "", "������", ""));
		map.put(101, new CarInfo(R.drawable.k1, "˫��", "", "˫��", ""));
		map.put(102, new CarInfo(R.drawable.k2, "����", "", "����", ""));
		map.put(103, new CarInfo(R.drawable.k3, "����", "", "����", ""));
		map.put(104, new CarInfo(R.drawable.k4, "����", "", "����", ""));
		map.put(105, new CarInfo(R.drawable.k5, "����ɭ", "", "����ɭ", ""));
		map.put(106, new CarInfo(R.drawable.k6, "ک��", "", "ک��", ""));
		map.put(107, new CarInfo(R.drawable.k7, "˼��", "", "˼��", ""));
		map.put(108, new CarInfo(R.drawable.k8, "�Ա�", "", "�Ա�", ""));
		map.put(109, new CarInfo(R.drawable.k9, "˫��", "", "˫��", ""));

		map.put(110, new CarInfo(R.drawable.l0, "Gumpert", "Gumpert", "Gumpert", ""));
		map.put(111, new CarInfo(R.drawable.l1, "���", "", "���", ""));
		map.put(112, new CarInfo(R.drawable.l2, "���ۺ�", "", "���ۺ�", ""));
		map.put(113, new CarInfo(R.drawable.l3, "���ǽ�", "", "���ǽ�", ""));
		map.put(114, new CarInfo(R.drawable.l4, "�ֿ�", "", "�ֿ�", ""));
		map.put(115, new CarInfo(R.drawable.l5, "������", "", "������", ""));
		map.put(116, new CarInfo(R.drawable.l6, "��Դ", "", "��Դ", ""));
		map.put(117, new CarInfo(R.drawable.l7, "����", "", "����", ""));
		map.put(118, new CarInfo(R.drawable.l8, "KTM", "KTM", "KTM", ""));
		map.put(119, new CarInfo(R.drawable.l9, "����Smart", "BenZ-Smart", "����Smart", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */
		map.put(120, new CarInfo(R.drawable.m0, "����", "", "����", ""));
		map.put(121, new CarInfo(R.drawable.m1, "��˹��", "", "��˹��", ""));
		map.put(122, new CarInfo(R.drawable.m2, "һ��", "", "һ��", ""));
		map.put(123, new CarInfo(R.drawable.m3, "ŷ��", "", "ŷ��", ""));
		map.put(124, new CarInfo(R.drawable.m4, "����", "", "����", ""));
		map.put(125, new CarInfo(R.drawable.m5, "������", "", "������", ""));
		map.put(126, new CarInfo(R.drawable.m6, "Fisker", "Fisker", "Fisker", ""));
		map.put(127, new CarInfo(R.drawable.m7, "֮ŵ", "", "֮ŵ", ""));
		map.put(128, new CarInfo(R.drawable.m8, "Noble", "Noble", "Noble", ""));
		map.put(129, new CarInfo(R.drawable.m9, "Ħ������", "", "����Ħ������", ""));

		map.put(130, new CarInfo(R.drawable.n0, "��������", "", "��������", ""));
		map.put(131, new CarInfo(R.drawable.n1, "����", "", "����", ""));
		map.put(132, new CarInfo(R.drawable.n2, "��������", "", "��������", ""));
		map.put(133, new CarInfo(R.drawable.n3, "Hennessey", "Hennessey ", "Hennessey",
				""));
		map.put(134, new CarInfo(R.drawable.n4, "����������", "", "����������", ""));
		map.put(135, new CarInfo(R.drawable.n5, "�ֿ�˹����", "", "�ֿ�˹����", ""));
		map.put(136, new CarInfo(R.drawable.n6, "˹����", "", "˹����", ""));
		map.put(137, new CarInfo(R.drawable.n7, "----", "----", "----", ""));
		map.put(138, new CarInfo(R.drawable.n8, "----", "----", "----", ""));
		map.put(139, new CarInfo(R.drawable.n9, "----", "----", "----", ""));
		/** ++++++++++++++++++++++20++++++++++++++++++++++++ */

	}

}
