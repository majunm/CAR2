package com.m.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.m.base.BaseActivity;
import com.m.car2.R;
import com.m.car2.R.id;
import com.m.car2.R.layout;
import com.m.util.Tools;

public class CarFeedBackActivity extends BaseActivity {
	private TextView feedbackDetails;
	private TextView contenTitle;
	private TextView phoneTitle;
	private TextView emailTitle;
	private TextView contactUs;
	private RelativeLayout feedbackBackLayout;
	private EditText feedEmail;
	private EditText feedPhone;
	private EditText feedContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_feedback);
		feedbackDetails = (TextView) findViewById(R.id.car_feedback_details);
		contactUs = (TextView) findViewById(R.id.car_fback_contact_us);
		emailTitle = (TextView) findViewById(R.id.car_fback_email_title);
		phoneTitle = (TextView) findViewById(R.id.car_fback_phone_tite);
		contenTitle = (TextView) findViewById(R.id.car_fback_content_title);
		feedEmail = (EditText) findViewById(R.id.car_feedback_email);
		feedPhone = (EditText) findViewById(R.id.car_feedback_mobile);
		feedContent = (EditText) findViewById(R.id.car_feedback_content);
		feedbackBackLayout = (RelativeLayout) findViewById(R.id.car_feedback_layout);
		feedbackDetails.setSelected(true);
		backButton.setOnClickListener(this);
		Tools.closeSoftKeyboard(feedEmail, this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.car_feedback_details:
			break;
		case R.id.car_copyright_return:
			Tools.beginExit(mContext);
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
		carCommonTitle.setText("用户反馈");
		carCommonMore.setVisibility(View.GONE);
	}

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
		feedbackBackLayout.setBackgroundColor(Color.parseColor("#333333"));
		carCommonTitle.setTextColor(Color.parseColor("#B7B7B7"));
		actionbarLayout.setBackgroundResource(R.drawable.car_night_titlebg);

		contenTitle.setTextColor(Color.parseColor("#21EEFE"));
		phoneTitle.setTextColor(Color.parseColor("#21EEFE"));
		emailTitle.setTextColor(Color.parseColor("#21EEFE"));
		contactUs.setTextColor(Color.parseColor("#21EEFE"));
		feedbackDetails.setTextColor(Color.parseColor("#21EEFE"));

		feedEmail.setBackgroundResource(R.drawable.feedback_edit_night_bg);
		feedContent.setBackgroundResource(R.drawable.feedback_edit_night_bg);
		feedPhone.setBackgroundResource(R.drawable.feedback_edit_night_bg);
	}

	/** 白天 */
	private void day() {
		feedbackBackLayout.setBackgroundColor(Color.parseColor("#FFF4F5F7"));
		carCommonTitle.setTextColor(Color.parseColor("#4C4D4E"));
		actionbarLayout.setBackgroundResource(R.drawable.car_title_bg);

		contenTitle.setTextColor(Color.parseColor("#FF696969"));
		phoneTitle.setTextColor(Color.parseColor("#FF696969"));
		emailTitle.setTextColor(Color.parseColor("#FF696969"));
		contactUs.setTextColor(Color.parseColor("#FF696969"));
		feedbackDetails.setTextColor(Color.parseColor("#FF696969"));

		feedEmail.setBackgroundResource(R.drawable.feedback_edit_bg);
		feedContent.setBackgroundResource(R.drawable.feedback_edit_bg);
		feedPhone.setBackgroundResource(R.drawable.feedback_edit_bg);
	}

}
