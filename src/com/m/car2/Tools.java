package com.m.car2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Tools {
	/** 获取故事 position 对应品牌 0 = 劳斯莱斯....*/
	public static String loadData(Context context, int position) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader inputReader = new InputStreamReader(context.getAssets()
					.open((position + ".txt")));
			BufferedReader buffer = new BufferedReader(inputReader);
			String result = null;
			while ((result = buffer.readLine()) != null) {
				sb.append(result);
			}
		} catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			pw.close();
			sb.append(writer.toString());
		}
		Log.e("car", "sb.toString()=" + sb.toString());
		return sb.toString();
	}

	private static ProgressDialog mProgressDialog;

	/**
	 * 显示ProgressDialog
	 * 
	 * @param context
	 * @param message
	 */
	public static void showProgressDialog(Context context, CharSequence message) {
		if (mProgressDialog == null) {
			mProgressDialog = ProgressDialog.show(context, "", message);
		} else {
			mProgressDialog.show();
		}
	}

	/**
	 * 关闭ProgressDialog
	 */
	public static void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	/** 跳转不带动画 */
	public static void activityJump(Context context, Class<?> clazz) {
		activityJump(context, clazz, true);
	}

	/** 跳转关闭自己带动画 */
	public static void activityJumpWithAnimation(Context context, Class<?> clazz) {
		activityJumpWithAnimation(context, clazz, true);
	}

	/** 跳转带动画 ,变量控制关闭自己 true = 关闭 false = 不关闭 */
	public static void activityJumpWithAnimation(Context context, Class<?> clazz,
			boolean isClosed) {
		activityJumping(context, clazz, isClosed, null);
	}

	/** 跳转带动画 ,变量控制关闭自己 true = 关闭 false = 不关闭 ,佩带bundle对象 */
	public static void activityJumping(Context context, Class<?> clazz, boolean isClosed,
			Bundle bundle) {
		Intent intent = new Intent(context, clazz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		// intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
		context.startActivity(intent);
		if (isClosed) {
			((Activity) context).finish();
		}
		((Activity) context).overridePendingTransition(R.anim.slide_right_in,
				R.anim.mini_hold);
	}

	/** 关闭自己动画 */
	public static void beginExit(Context context) {
		((Activity) context).finish();
		((Activity) context).overridePendingTransition(R.anim.mini_hold,
				R.anim.slide_right_out);
	}

	/** 跳转,是否关闭自己，变量控制,不带动画 */
	public static void activityJump(Context context, Class<?> clazz, boolean isClose) {
		Intent intent = new Intent(context, clazz);
		context.startActivity(intent);
		if (isClose) {
			((Activity) context).finish();
		}
	}

	/**
	 * 关闭软键盘
	 */
	public static void closeSoftKeyboard(View v, Activity context) {
		Log.e("majun95598", "close soft keyboard.....");
		InputMethodManager inputManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputManager.isActive()) {
			inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
		}
	}

	/**
	 * 开启或者关闭软键盘
	 */
	public static void openOrClosedSoftKeyboard(View v) {
		InputMethodManager inputManager = (InputMethodManager) v.getContext()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// inputManager.showSoftInput(v, 0); // 开启软键盘
		inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}
