package com.m.car2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CarFragment extends Fragment {
	private ViewPager m_vp;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.activity_main2, null);
		m_vp = (ViewPager) view.findViewById(R.id.viewpager);
		m_vp.setAdapter(new MyViewPagerAdapter(getActivity()
				.getSupportFragmentManager()));
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

}
