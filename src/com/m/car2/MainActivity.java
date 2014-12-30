package com.m.car2;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	private ViewPager m_vp;

	private RadioButton previous;
	private RadioButton next;
	private RadioButton classify;

	private RadioGroup group;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		setActionBarLayout(R.layout.car_actionbar_layout);
		m_vp = (ViewPager) findViewById(R.id.viewpager);
		m_vp.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager()));
		group = (RadioGroup) findViewById(R.id.mini_group);
		previous = (RadioButton) findViewById(R.id.mini_previous);
		next = (RadioButton) findViewById(R.id.mini_next);
		classify = (RadioButton) findViewById(R.id.mini_classify);
		group.setOnCheckedChangeListener(onCheckedChangeListener);
		for (int i = 0; i < group.getChildCount(); i++) {
			if (group.getCheckedRadioButtonId() == group.getChildAt(i).getId()) {
				((RadioButton) group.getChildAt(i)).setTextColor(Color
						.parseColor("#74DCFF"));
			}
		}
	}

	public OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			clearColorStatus();
			switch (checkedId) {
			case R.id.mini_previous:
				Toast.makeText(getApplicationContext(), "@0@@", 0).show();
				previous.setTextColor(Color.parseColor("#74DCFF"));
				break;
			case R.id.mini_next:
				Toast.makeText(getApplicationContext(), "@1@@", 0).show();
				next.setTextColor(Color.parseColor("#74DCFF"));
				break;
			case R.id.mini_classify:
				// classify.setTextColor(Color.parseColor("#1bbc9b"));
				classify.setTextColor(Color.parseColor("#74DCFF"));
				Toast.makeText(getApplicationContext(), "@2@@", 0).show();
				break;
			}
		}

		/** 清空颜色值 */
		private void clearColorStatus() {
			previous.setTextColor(Color.parseColor("#6D6D6F"));
			next.setTextColor(Color.parseColor("#6D6D6F"));
			classify.setTextColor(Color.parseColor("#6D6D6F"));
		}
	};

	/** 标题 */
	private TextView carCommonTitle;

	private long firstTime;

	public class MyViewPagerAdapter extends FragmentPagerAdapter {

		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int currentIndex) {
			// switch (currentIndex) {
			// default:
			// break;
			// }
			return CarDetailFragment.newInstance(currentIndex);
		}

		@Override
		public int getCount() {
			return 200;
		}

		// @Override
		// public CharSequence getPageTitle(int position) {
		// return titleList.get(position);
		// }
	}

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

}
