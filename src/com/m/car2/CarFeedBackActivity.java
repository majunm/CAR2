package com.m.car2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.m.base.BaseActivity;

public class CarFeedBackActivity extends BaseActivity {
	private TextView feedbackDetails;
	private ImageView feedbackBack;
	private EditText feedEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_feedback);
		feedbackDetails = (TextView) findViewById(R.id.car_feedback_details);
		feedbackBack = (ImageView) findViewById(R.id.feedback_back);
		feedEmail = (EditText) findViewById(R.id.car_feedback_email);
		feedbackDetails.setSelected(true);
		feedbackBack.setOnClickListener(this);
		Tools.closeSoftKeyboard(feedEmail, this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.car_feedback_details:
			break;
		case R.id.feedback_back:
			Tools.beginExit(mContext);
			break;

		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Tools.beginExit(this);
	}

	@Override
	public void resetNavigation() {
		getActionBar().hide();
	}

}
