package com.m.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.activity.CarCopyrightActivity;
import com.m.activity.CarFeedBackActivity;
import com.m.activity.MaJunTestWebViewActivity;
import com.m.base.BaseFragment;
import com.m.car2.R;
import com.m.util.Tools;

public class SettingFragment extends BaseFragment {

	private TextView feedbackHint;
	private RelativeLayout feedbackLayout;
	private RelativeLayout copyrightLayout;
	private RelativeLayout goGradeLayout;
	private RelativeLayout carChangeNight;
	private RelativeLayout settingLayout;
	private ImageView feedbackArrow;
	private ImageView openSwitch;
	private TextView versionNumber;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = View.inflate(getActivity(), R.layout.car_settings, null);
		findView(view);
		return view;
	}

	private void findView(View view) {
		feedbackHint = (TextView) view.findViewById(R.id.car_feedback);
		feedbackArrow = (ImageView) view.findViewById(R.id.car_feedback_arrow);
		feedbackLayout = (RelativeLayout) view
				.findViewById(R.id.car_feedback_layout);
		copyrightLayout = (RelativeLayout) view
				.findViewById(R.id.car_copyright_layout);
		goGradeLayout = (RelativeLayout) view
				.findViewById(R.id.car_gograde_layout);
		carChangeNight = (RelativeLayout) view
				.findViewById(R.id.car_night_model_layout);
		settingLayout = (RelativeLayout) view.findViewById(R.id.setting_layout);
		openSwitch = (ImageView) view
				.findViewById(R.id.car_night_model_open_icon);
		versionNumber = (TextView) view.findViewById(R.id.car_version);
		registerListener();
		versionNumber.setText(Tools.getVersionInfo(getActivity()));
	}

	private void registerListener() {
		feedbackHint.setOnClickListener(this);
		feedbackArrow.setOnClickListener(this);
		feedbackLayout.setOnClickListener(this);
		copyrightLayout.setOnClickListener(this);
		goGradeLayout.setOnClickListener(this);
		carChangeNight.setOnClickListener(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// addPreferencesFromResource(R.xml.preferences);
	}

	public static class PrefsFragment extends PreferenceFragment {
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// addPreferencesFromResource(R.xml.preferences);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_feedback:
		case R.id.car_feedback_layout:
		case R.id.car_feedback_arrow:
			Tools.activityJumpWithAnimation(getActivity(),
					CarFeedBackActivity.class, false);
			break;
		case R.id.car_copyright_layout:
			Tools.activityJumpWithAnimation(getActivity(),
					CarCopyrightActivity.class, false);
			break;
		case R.id.car_gograde_layout:
			Tools.activityJumpWithAnimation(getActivity(),
					MaJunTestWebViewActivity.class, false);
			break;
		case R.id.car_night_model_layout:
			Intent intent = new Intent("chage_status");
			if (openSwitch.getVisibility() == View.GONE) {
				openSwitch.setVisibility(View.VISIBLE);
				intent.putExtra("dayornight", "night");
			} else {
				openSwitch.setVisibility(View.GONE);
				intent.putExtra("dayornight", "day");
			}
			getActivity().sendBroadcast(intent);
			break;
		default:
			break;
		}
	}

	@Override
	public void receiver(Context context, Intent intent) {
		try {
			if (isNight) {
				// settingLayout.setBackgroundColor(Color.parseColor("#33333333"));
				Tools.setDayChange(getActivity(), true);
				settingLayout.setBackgroundColor(Color.parseColor("#333333"));
			} else {
				Tools.setDayChange(getActivity(), false);
				settingLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
