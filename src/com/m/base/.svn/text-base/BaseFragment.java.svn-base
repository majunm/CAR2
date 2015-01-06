package com.m.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseFragment extends Fragment implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			filter = new IntentFilter("chage_status");
			getActivity().registerReceiver(dayStatusChangeReceiver, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 是白天黑夜? */
	protected static boolean isNight = false;
	protected BroadcastReceiver dayStatusChangeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				if (intent.getAction().equals("chage_status")) {
					if (intent.getStringExtra("dayornight").equals("night")) {
						isNight = true;
					} else if (intent.getStringExtra("dayornight")
							.equals("day")) {
						isNight = false;
					} else {
						isNight = false;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				isNight = false;
			}
			receiver(context, intent);
		}
	};
	private IntentFilter filter;

	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			getActivity().unregisterReceiver(dayStatusChangeReceiver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void receiver(Context context, Intent intent);

	@Override
	public void onClick(View v) {

	}

}
