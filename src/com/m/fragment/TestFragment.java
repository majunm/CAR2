package com.m.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.m.base.BaseFragment;
import com.m.car2.R;
import com.m.util.Tools;

public class TestFragment extends BaseFragment {
	private LinearLayout testLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.car_test, null);
		testLayout = (LinearLayout) view.findViewById(R.id.test_layout);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		if (Tools.isDayChange(getActivity()) || isNight) {
			testLayout.setBackgroundColor(Color.parseColor("#333333"));
		} else {
			testLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
		}
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void receiver(Context context, Intent intent) {
		try {
			if (isNight) {
				testLayout.setBackgroundColor(Color.parseColor("#333333"));
			} else {
				testLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
