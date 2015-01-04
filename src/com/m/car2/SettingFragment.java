package com.m.car2;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingFragment extends Fragment implements OnClickListener {

	private TextView feedbackHint;
	private RelativeLayout feedbackLayout;
	private ImageView feedbackArrow;

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
		feedbackLayout = (RelativeLayout) view.findViewById(R.id.car_feedback_layout);
		registerListener();
	}

	private void registerListener() {
		feedbackHint.setOnClickListener(this);
		feedbackArrow.setOnClickListener(this);
		feedbackLayout.setOnClickListener(this);
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
			addPreferencesFromResource(R.xml.preferences);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_feedback:
		case R.id.car_feedback_layout:
		case R.id.car_feedback_arrow:
			Tools.activityJumpWithAnimation(getActivity(), CarFeedBackActivity.class,
					false);
			break;

		default:
			break;
		}
	}

}
