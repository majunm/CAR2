package com.m.car2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
						.parseColor("#FF0000"));
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
				previous.setTextColor(Color.parseColor("#FF0000"));
				break;
			case R.id.mini_next:
				Toast.makeText(getApplicationContext(), "@1@@", 0).show();
				next.setTextColor(Color.parseColor("#FF0000"));
				break;
			case R.id.mini_classify:
				// classify.setTextColor(Color.parseColor("#1bbc9b"));
				classify.setTextColor(Color.parseColor("#FF0000"));
				Toast.makeText(getApplicationContext(), "@2@@", 0).show();
				break;
			}
		}

		/** Çå¿ÕÑÕÉ«Öµ */
		private void clearColorStatus() {
			previous.setTextColor(Color.parseColor("#00FF00"));
			next.setTextColor(Color.parseColor("#00FF00"));
			classify.setTextColor(Color.parseColor("#00FF00"));
		}
	};

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

	}

}
