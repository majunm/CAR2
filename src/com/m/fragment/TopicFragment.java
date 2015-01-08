package com.m.fragment;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.m.base.BaseFragment;
import com.m.base.CheckView;
import com.m.base.CheckView.CheckListener;
import com.m.car2.R;
import com.m.domain.CarInfo;
import com.m.util.Tools;
import com.umeng.analytics.MobclickAgent;

public class TopicFragment extends BaseFragment implements CheckListener,
		Callback {
	private LinearLayout testLayout;
	private CheckView check;
	private Handler mHandler;
	private final static int OK = 0;
	private final static int CANCEL = 1;
	public static int ERRORCOUNT = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.car_test, null);
		testLayout = (LinearLayout) view.findViewById(R.id.test_layout);
		return view;
	}

	private ArrayList<CarInfo> carInfos;
	private ImageView carImage;
	private int answer = 0;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		check = (CheckView) view.findViewById(R.id.check);
		carImage = (ImageView) view.findViewById(R.id.car_image);
		check.setCheckListener(this);
		carInfos = new ArrayList<CarInfo>();
		mHandler = new Handler(getActivity().getMainLooper(), this);
		initData();
	}

	private void initData() {
		for (int i = 0; i < 4; i++) {
			CarInfo carInfo = CarDetailFragment.map.get(getRondmNumber(139));
			carInfos.add(carInfo);
		}
		Random rand = new Random();
		answer = rand.nextInt(3);

		carImage.setImageResource(carInfos.get(answer).getResId());
		check.setData(carInfos);
	}

	private int getRondmNumber(int maxNumber) {
		Random rand = new Random();
		int nextInt = rand.nextInt(maxNumber) + 1;
		return nextInt;
	}

	@Override
	public void onStart() {
		super.onStart();
		if (isNight) {
			testLayout.setBackgroundColor(Color.parseColor("#202022"));
			check.setBackgroundColor(Color.parseColor("#202022"));
		} else {
			testLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
			check.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
		}
		if (ERRORCOUNT > 0 && ERRORCOUNT % 10 == 0) {
			Tools.money(getActivity(), false);
		}
	}

	@Override
	public void receiver(Context context, Intent intent) {
		try {
			if (isNight) {
				testLayout.setBackgroundColor(Color.parseColor("#202022"));
				check.setBackgroundColor(Color.parseColor("#202022"));
			} else {
				testLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
				check.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void onClivk(int position) {
		if (position == answer) {
			Toast.makeText(getActivity(), "恭喜回答正确", 0).show();
			sendMessage(OK, 1300);
		} else {
			Toast.makeText(getActivity(), "不好意思，回答错误!", 0).show();
			ERRORCOUNT += 1;
			sendMessage(CANCEL, 600);
		}
	}

	private void sendMessage(int what, int time) {
		Message message = mHandler.obtainMessage();
		message.what = what;
		mHandler.sendMessageDelayed(message, time);
	}

	@Override
	public boolean handleMessage(Message arg0) {
		switch (arg0.what) {
		case OK:
			carInfos.clear();
			check.clear(answer);
			initData();
			break;
		case CANCEL:
			check.clear(answer);
			if (ERRORCOUNT > 0 && ERRORCOUNT % 10 == 0) {
				Tools.money(getActivity(), false);
			}
			break;
		}
		return false;
	}

	@Override
	public void onResume() {
		super.onResume();
		MobclickAgent.onPageStart("TopicFragment"); // 统计页面
	}

	@Override
	public void onPause() {
		super.onPause();
		MobclickAgent.onPageEnd("TopicFragment");
	}
	
}
