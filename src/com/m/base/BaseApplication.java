package com.m.base;

import com.m.util.Tools;

import android.app.Application;

public class BaseApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		try {
			Tools.setDayChange(getApplicationContext(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
