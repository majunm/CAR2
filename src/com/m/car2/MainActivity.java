package com.m.car2;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {

	private RadioButton carBrand;
	private RadioButton setting;
	private RadioButton classify;

	private RadioGroup group;
	private int currentIndex = R.id.car_brand;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setActionBarLayout(R.layout.car_actionbar_layout);
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

				carBrand.setTextColor(Color.parseColor("#74DCFF"));
				if (carFragment == null) {
					carFragment = new CarFragment();
					ft.add(R.id.car_cotainer, carFragment);
				} else {
					ft.show(carFragment);
				}
				break;
			case R.id.mini_classify:
				classify.setTextColor(Color.parseColor("#74DCFF"));
				if (testFragment == null) {
					testFragment = new TestFragment();
					ft.add(R.id.car_cotainer, testFragment);
				} else {
					ft.show(testFragment);
				}
				break;
			case R.id.car_setting:
				setting.setTextColor(Color.parseColor("#74DCFF"));
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
		}

	};

	/** 清空颜色值 */
	private void clearColorStatus() {
		carBrand.setTextColor(Color.parseColor("#6D6D6F"));
		setting.setTextColor(Color.parseColor("#6D6D6F"));
		classify.setTextColor(Color.parseColor("#6D6D6F"));
	}

	/** 标题 */
	private TextView carCommonTitle;

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

	private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(
			getSupportFragmentManager()) {

		@Override
		public Fragment getItem(int position) {
			Fragment f = null;
			switch (position) {
			case 0:
				carBrand.setTextColor(Color.parseColor("#74DCFF"));
				f = new CarFragment();
			case 2:
				setting.setTextColor(Color.parseColor("#74DCFF"));
				f = new SettingFragment();
			case 1:
				classify.setTextColor(Color.parseColor("#74DCFF"));
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

	/** 自定义actionbar */
	public void setActionBarLayout(int layoutId) {
		ActionBar actionBar = getActionBar();
		if (null != actionBar) {
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowCustomEnabled(true);
			LayoutInflater inflator = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(layoutId, null);
			carCommonTitle = (TextView) v.findViewById(R.id.car_common_title);
			ImageView carCommonMore = (ImageView) v.findViewById(R.id.car_common_more);
			carCommonMore.setOnClickListener(this);
			carCommonTitle.setOnClickListener(this);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			actionBar.setCustomView(v, layout);
		}
	}

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

}