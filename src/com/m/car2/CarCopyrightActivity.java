package com.m.car2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.m.base.BaseActivity;

public class CarCopyrightActivity extends BaseActivity {

	private ImageView copyrightReturn;
	private ImageView copyrightLoading;
	private RotateAnimation animation;

	private static boolean isFirstLoad = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_copyright);
		copyrightReturn = (ImageView) findViewById(R.id.car_copyright_return);
		copyrightLoading = (ImageView) findViewById(R.id.car_copyright_loading);
		copyrightReturn.setOnClickListener(this);
		animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(700);
		animation.setRepeatCount(-1);
		animation.setInterpolator(new LinearInterpolator());
		if(isFirstLoad){
			copyrightLoading.setAnimation(animation);
			animation.start();
			mHandler.sendEmptyMessageDelayed(0, 1200);
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.car_copyright_return:
			Tools.beginExit(mContext);
			break;
		default:
			break;
		}
	}

	@Override
	public void resetNavigation() {
		getActionBar().hide();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Tools.beginExit(mContext);
	}

	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				isFirstLoad = false;
				animation.cancel();
				copyrightLoading.clearAnimation();
				copyrightLoading.setVisibility(View.GONE);
				break;

			default:
				break;
			}
		}

	};
}
