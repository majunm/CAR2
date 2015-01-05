package com.m.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.m.base.BaseActivity;
import com.m.car2.R;
import com.m.fragment.CarFragment;
import com.m.fragment.SettingFragment;
import com.m.fragment.TestFragment;
import com.m.util.Tools;

public class MainActivity extends BaseActivity {

	private RadioButton carBrand;
	private RadioButton setting;
	private RadioButton classify;

	private RadioGroup group;
	private int currentIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		container = (FrameLayout) findViewById(R.id.car_cotainer);
		group = (RadioGroup) findViewById(R.id.mini_group);
		carBrand = (RadioButton) findViewById(R.id.car_brand);
		setting = (RadioButton) findViewById(R.id.car_setting);
		classify = (RadioButton) findViewById(R.id.mini_classify);
		group.setOnCheckedChangeListener(onCheckedChangeListener);
		for (int i = 0; i < group.getChildCount(); i++) {
			if (group.getCheckedRadioButtonId() == group.getChildAt(i).getId()) {
				((RadioButton) group.getChildAt(i)).setTextColor(Color
						.parseColor("#74DCFF"));
			}
		}
		carFragment = new CarFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.car_cotainer, carFragment).commit();
		// group.check(currentIndex);
	}

	public OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			clearColorStatus();
			hideFragments(ft);
			switch (checkedId) {
			case R.id.car_brand:
				currentIndex = 0;
				if (!isNight) {
					carBrand.setTextColor(Color.parseColor("#74DCFF"));
				} else {
					carBrand.setTextColor(Color.parseColor("#21EEFE"));
				}
				if (carFragment == null) {
					carFragment = new CarFragment();
					ft.add(R.id.car_cotainer, carFragment);
				} else {
					ft.show(carFragment);
				}
				break;
			case R.id.mini_classify:
				currentIndex = 1;
				if (!isNight) {
					classify.setTextColor(Color.parseColor("#74DCFF"));
				} else {
					classify.setTextColor(Color.parseColor("#21EEFE"));
				}
				if (testFragment == null) {
					testFragment = new TestFragment();
					ft.add(R.id.car_cotainer, testFragment);
				} else {
					ft.show(testFragment);
				}
				break;
			case R.id.car_setting:
				currentIndex = 2;
				if (!isNight) {
					setting.setTextColor(Color.parseColor("#74DCFF"));
				} else {
					setting.setTextColor(Color.parseColor("#21EEFE"));
				}
				if (settingFragment == null) {
					settingFragment = new SettingFragment();
					ft.add(R.id.car_cotainer, settingFragment);
				} else {
					ft.show(settingFragment);
				}
				break;
			}
			ft.commit();
			// Fragment fragment = (Fragment)
			// mFragmentPagerAdapter.instantiateItem(
			// container, index);
			// Log.e("debug", "index=" + index);
			// mFragmentPagerAdapter.setPrimaryItem(container, 0, fragment);
			// mFragmentPagerAdapter.finishUpdate(container);
			resetNavigation();
		}

	};

	/** 清空颜色值 */
	private void clearColorStatus() {
		if (isNight) {
			carBrand.setTextColor(Color.parseColor("#747474"));
			setting.setTextColor(Color.parseColor("#747474"));
			classify.setTextColor(Color.parseColor("#747474"));
		} else {
			carBrand.setTextColor(Color.parseColor("#6D6D6F"));
			setting.setTextColor(Color.parseColor("#6D6D6F"));
			classify.setTextColor(Color.parseColor("#6D6D6F"));
		}
	}

	private long firstTime;

	private FrameLayout container;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_common_title:
		case R.id.car_common_more:

			break;

		default:
			break;
		}
	}

	public FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(
			getSupportFragmentManager()) {

		@Override
		public Fragment getItem(int position) {
			Fragment f = null;
			switch (position) {
			case 0:
				if (!isNight) {
					carBrand.setTextColor(Color.parseColor("#74DCFF"));
				} else {
					carBrand.setTextColor(Color.parseColor("#21EEFE"));
				}
				f = new CarFragment();
			case 2:
				if (!isNight) {
					setting.setTextColor(Color.parseColor("#74DCFF"));
				} else {
					setting.setTextColor(Color.parseColor("#21EEFE"));
				}
				f = new SettingFragment();
			case 1:
				if (!isNight) {
					classify.setTextColor(Color.parseColor("#74DCFF"));
				} else {
					classify.setTextColor(Color.parseColor("#21EEFE"));
				}
				f = new TestFragment();
			}
			return f;
		}

		@Override
		public int getCount() {
			return 3;
		}
	};
	private CarFragment carFragment;
	private TestFragment testFragment;
	private SettingFragment settingFragment;

	/**
	 * 按两次退出应用~~
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 1000) {// 如果两次按键时间间隔大于800毫秒，则不退出
				firstTime = secondTime;// 更新firstTime
				Toast.makeText(this, "再按一次退出", 0).show();
				try {
				} catch (Exception e) {
					e.printStackTrace();
				}
				return true;
			} else {
				finish();
				Tools.setDayChange(mContext, false);
				System.exit(0);
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		}
		return super.onKeyUp(keyCode, event);
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (carFragment != null) {
			transaction.hide(carFragment);
		}
		if (testFragment != null) {
			transaction.hide(testFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}

	@Override
	public void resetNavigation() {
		try {
			switch (currentIndex) {
			case 0:
			case 1:
				carCommonMore.setVisibility(View.VISIBLE);
				break;
			case 2:
				carCommonMore.setVisibility(View.GONE);
				break;
			}
			if (isNight) {
				actionbarLayout.setBackgroundResource(R.drawable.car_night_titlebg);
				carCommonTitle.setTextColor(Color.parseColor("#B7B7B7"));
			} else {
				actionbarLayout.setBackgroundResource(R.drawable.car_title_bg);
				carCommonTitle.setTextColor(Color.parseColor("#4C4D4E"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void receiver(Context context, Intent intent) {
		try {
			if (intent.getAction().equals("chage_status")) {
				if (intent.getStringExtra("dayornight").equals("night")) {
					Log.e("car", "MainActivity NIGHT");
					carBrand.setBackgroundResource(R.drawable.car_night);
					setting.setBackgroundResource(R.drawable.car_night);
					classify.setBackgroundResource(R.drawable.car_night);
					isNight = true;
					switch (currentIndex) {
					case 0:
						carBrand.setTextColor(Color.parseColor("#21EEFE"));
						break;
					case 1:
						classify.setTextColor(Color.parseColor("#21EEFE"));
						break;
					case 2:
						setting.setTextColor(Color.parseColor("#21EEFE"));
						break;
					}
				} else if (intent.getStringExtra("dayornight").equals("day")) {
					isNight = false;
					Log.e("car", "MainActivity DAY");
					carBrand.setBackgroundResource(R.drawable.car_day);
					setting.setBackgroundResource(R.drawable.car_day);
					classify.setBackgroundResource(R.drawable.car_day);
					switch (currentIndex) {
					case 0:
						carBrand.setTextColor(Color.parseColor("#74DCFF"));
						break;
					case 1:
						classify.setTextColor(Color.parseColor("#74DCFF"));
						break;
					case 2:
						setting.setTextColor(Color.parseColor("#74DCFF"));
						break;
					}
				} else {
					Log.e("car", "MainActivity UNKNOW WHY!");
				}
				resetNavigation();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("car", "UNKNOW WHY! EXCEPTION ");
		}
	}

}