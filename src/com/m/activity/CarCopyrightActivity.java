package com.m.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.base.BaseActivity;
import com.m.car2.R;
import com.m.util.Tools;

public class CarCopyrightActivity extends BaseActivity {

	private ImageView copyrightLoading;
	private TextView copyrightDetail;
	private RelativeLayout copyrightLayout;
	private RotateAnimation animation;

	private static boolean isFirstLoad = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_copyright);
		copyrightLoading = (ImageView) findViewById(R.id.car_copyright_loading);
		copyrightLayout = (RelativeLayout) findViewById(R.id.car_copyright_layout);
		copyrightDetail = (TextView) findViewById(R.id.car_copyright_details);
		animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(700);
		animation.setRepeatCount(-1);
		backButton.setOnClickListener(this);
		animation.setInterpolator(new LinearInterpolator());
		if (isFirstLoad) {
			copyrightLoading.setAnimation(animation);
			animation.start();
			mHandler.sendEmptyMessageDelayed(0, 1200);
		} else {
			copyrightLoading.setVisibility(View.GONE);
			showCopyright();
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

	public void showCopyright() {
		String source = "<B>出品人/监制:</B>&nbsp;&nbsp;胡帅兵<br/><br/>"
				+ "<B>执行主编:</B>&nbsp;&nbsp;马小俊<br/>"
				+ "<B>副主编:</B>&nbsp;&nbsp;韩小冬<br/><br/>"
				+ "<B>执行编辑:</B>&nbsp;&nbsp;聂小战、胡小兵、胡小帅<br/><br/>"
				+ "<B>美术编辑:</B>&nbsp;&nbsp;张小富<br/><br/>"
				+ "<B>商务助理:</B>&nbsp;&nbsp;胡乒乓<br/><br/>"
				+ "<B>流程监督:</B>&nbsp;&nbsp;胡乒乒<br/><br/>"
				+ "<B>汽车品牌投稿:</B>&nbsp;&nbsp;car2@163.com<br/>"
				+ "<B>问题咨询:</B>&nbsp;&nbsp;car2@163.com<br/>"
				+ "<B>东西推荐:</B>&nbsp;&nbsp;car2@163.com<br/><br/>"
				+ "<B>商业合作:</B>&nbsp;&nbsp;747673016@qq.com";
		copyrightDetail.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		copyrightDetail.setText(Html.fromHtml(source));
		copyrightDetail.setGravity(Gravity.CENTER | Gravity.TOP);
	}

	@Override
	public void resetNavigation() {
		carCommonTitle.setText("制作团队");
		carCommonMore.setVisibility(View.GONE);
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
				showCopyright();
				break;
			default:
				break;
			}
		}

	};

	@Override
	public void receiver(Context context, Intent intent) {

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.e("car", "isNight=" + isNight);
		if (isNight) {
			night();
		} else {
			day();
		}
	}

	/** 晚上 */
	private void night() {
		copyrightLayout.setBackgroundColor(Color.parseColor("#333333"));
		carCommonTitle.setTextColor(Color.parseColor("#B7B7B7"));
		actionbarLayout.setBackgroundResource(R.drawable.car_night_titlebg);
		copyrightDetail.setTextColor(Color.parseColor("#21EEFE"));
	}

	/** 白天 */
	private void day() {
		copyrightLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
		carCommonTitle.setTextColor(Color.parseColor("#4C4D4E"));
		actionbarLayout.setBackgroundResource(R.drawable.car_title_bg);
		copyrightDetail.setTextColor(Color.parseColor("#6D6D6F"));
	}

}
