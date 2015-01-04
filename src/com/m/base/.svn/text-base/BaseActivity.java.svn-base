package com.m.base;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.car2.R;

public abstract class BaseActivity extends FragmentActivity implements OnClickListener {
	protected Context mContext;
	/** 重置标题导航条 */
	public abstract void resetNavigation();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setActionBarLayout(R.layout.car_actionbar_layout);
			mContext = this;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 标题 */
	protected TextView carCommonTitle;
	protected ImageView carCommonMore;

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
			carCommonMore = (ImageView) v.findViewById(R.id.car_common_more);
			carCommonMore.setOnClickListener(this);
			carCommonTitle.setOnClickListener(this);
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
		case R.id.car_common_more:

			break;

		default:
			break;
		}
	}

}
