package com.m.base;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.car2.R;
import com.umeng.analytics.MobclickAgent;

public abstract class BaseActivity extends FragmentActivity implements
		OnClickListener {
	protected Context mContext;
	/** 是白天黑夜? */
	protected static boolean isNight = false;

	/** 重置标题导航条 */
	public abstract void resetNavigation();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setActionBarLayout(R.layout.car_actionbar_layout);
			mContext = this;
			filter = new IntentFilter("chage_status");
			registerReceiver(dayStatusChangeReceiver, filter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 标题 */
	protected TextView carCommonTitle;
	protected ImageView carCommonMore;
	protected ImageView backButton;

	/** 自定义actionbar */
	public void setActionBarLayout(int layoutId) {
		ActionBar actionBar = getActionBar();
		if (null != actionBar) {
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowCustomEnabled(true);
			LayoutInflater inflator = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(layoutId, null);
			actionbarLayout = (RelativeLayout) v
					.findViewById(R.id.car_actionbarLayout);
			carCommonTitle = (TextView) v.findViewById(R.id.car_common_title);
			carCommonMore = (ImageView) v.findViewById(R.id.car_common_more);
			backButton = (ImageView) v.findViewById(R.id.car_copyright_return);
			carCommonMore.setOnClickListener(this);
			carCommonTitle.setOnClickListener(this);
			backButton.setOnClickListener(this);
			ActionBar.LayoutParams layout = new ActionBar.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			actionBar.setCustomView(v, layout);
			resetNavigation();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_common_title:
			break;
		case R.id.car_common_more:
			break;
		case R.id.car_copyright_return:
			break;
		}
	}

	protected BroadcastReceiver dayStatusChangeReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			receiver(context, intent);
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
		}
	};
	protected IntentFilter filter;
	protected RelativeLayout actionbarLayout;

	public abstract void receiver(Context context, Intent intent);

	protected void onDestroy() {
		try {
			super.onDestroy();
			unregisterReceiver(dayStatusChangeReceiver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		}
		public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
		}

}
