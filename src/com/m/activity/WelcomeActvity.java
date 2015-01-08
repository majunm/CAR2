package com.m.activity;

import com.m.car2.R;

import u.aly.ar;
import u.aly.r;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Window;

public class WelcomeActvity extends Activity implements Callback {
	
	private Handler handler;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_welcome);
		handler = new Handler(getMainLooper() , this);
		
		Message message = handler.obtainMessage();
		message.what = 1 ;
		handler.sendMessageDelayed(message, 2000);
	}
	
	
	@Override
	public void onBackPressed() {
	}


	@Override
	public boolean handleMessage(Message arg0) {
		switch (arg0.what) {
			case 1:
				Intent intent = new Intent(WelcomeActvity.this , MainActivity.class);
				startActivity(intent);
				finish();
				break;
		}
		return true;
	}

}
