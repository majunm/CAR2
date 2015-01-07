package com.m.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.m.base.BaseFragment;
import com.m.car2.R;
import com.m.util.Tools;

public class CarFragment extends BaseFragment {
	private ViewPager m_vp;
	private LinearLayout lLayout;
	private MyViewPagerAdapter myViewPagerAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.activity_main2, null);
		m_vp = (ViewPager) view.findViewById(R.id.viewpager);
		lLayout = (LinearLayout) view.findViewById(R.id.car_fragment_layout);
		myViewPagerAdapter = new MyViewPagerAdapter(getActivity()
				.getSupportFragmentManager());
		m_vp.setAdapter(myViewPagerAdapter);
		return view;
	}

	// public class MyViewPagerAdapter extends FragmentPagerAdapter {
	/** 没用的赶紧销毁,别浪费资源 */
	public class MyViewPagerAdapter extends FragmentStatePagerAdapter {
		public MyViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int currentIndex) {
			// switch (currentIndex) {
			// default:
			// break;
			// }
			CarDetailFragment newInstance = CarDetailFragment
					.newInstance(currentIndex);
			return newInstance;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			CarDetailFragment f = (CarDetailFragment) super.instantiateItem(
					container, position);
			return f;
		}

		@Override
		public int getCount() {
			return 140;
		}

		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
	}

	@Override
	public void receiver(Context context, Intent intent) {
		if (Tools.isDayChange(getActivity()) || isNight) {
			lLayout.setBackgroundColor(Color.parseColor("#333333"));
		} else {
			lLayout.setBackgroundColor(Color.parseColor("#F4F5F7"));
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		if (Tools.isDayChange(getActivity()) || isNight) {
			lLayout.setBackgroundColor(Color.parseColor("#333333"));
		} else {
			lLayout.setBackgroundColor(Color.parseColor("#F4F5F7"));
		}
		try {
			if (myViewPagerAdapter != null) {
				if (myViewPagerAdapter.getCount() == 0) {
					myViewPagerAdapter = new MyViewPagerAdapter(getActivity()
							.getSupportFragmentManager());
					m_vp.setAdapter(myViewPagerAdapter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
