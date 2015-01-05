package com.m.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseFragment extends Fragment implements OnClickListener {
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

	protected BroadcastReceiver dayStatusChangeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			try {
				if (intent.getAction().equals("chage_status")) {
					if (intent.getStringExtra("dayornight").equals("night")) {
						Log.e("car", "BASEFRAGMENT NIGHT");
					} else if (intent.getStringExtra("dayornight").equals("day")) {
						Log.e("car", "BASEFRAGMENT DAY");
					} else {
						Log.e("car", "BaseFragment UNKNOW WHY!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("car", "BaseFragment UNKNOW WHY! EXCEPTION ");
			}
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

	@Override
	public void onClick(View v) {
		
	}

}
