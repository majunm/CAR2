package com.m.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import net.youmi.android.spot.SpotDialogListener;
import net.youmi.android.spot.SpotManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.m.car2.R;

public class Tools {
	/** 获取故事 position 对应品牌 0 = 劳斯莱斯.... */
	public static String loadData(Context context, int position) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStreamReader inputReader = new InputStreamReader(context
					.getAssets().open((position + ".txt")));
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
	public static void activityJumpWithAnimation(Context context,
			Class<?> clazz, boolean isClosed) {
		activityJumping(context, clazz, isClosed, null);
	}

	/** 跳转带动画 ,变量控制关闭自己 true = 关闭 false = 不关闭 ,佩带bundle对象 */
	public static void activityJumping(Context context, Class<?> clazz,
			boolean isClosed, Bundle bundle) {
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
	public static void activityJump(Context context, Class<?> clazz,
			boolean isClose) {
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

	/**
	 * @description :获取版本号
	 */
	public static String getVersionInfo(Context context) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			Log.e("majunjun", "can't reach........");
			return "";
		}
	}

	/**
	 * @description :获取应用名称
	 */
	public static String getApplicationName(Activity context) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(
					context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			applicationInfo = null;
		}
		return (String) packageManager.getApplicationLabel(applicationInfo);
	}

	/** 非空判断 */
	public static boolean isEmpty(String str) {
		return TextUtils.isEmpty(str);
	}

	/** 开启浏览器 */
	public static void openBrowser(Context context, Uri uri) {
		Uri mUri = Uri.parse("http://www.mumayi.com/android-680519.html");
		Intent in = new Intent(Intent.ACTION_VIEW, mUri);
		((Activity) context).startActivity(in);
	}

	/** 获取控件 */
	@SuppressWarnings("unchecked")
	public static <T extends View> T getWidget(View view, int id) {
		SparseArray<View> hashMap = (SparseArray<View>) view.getTag();
		if (hashMap == null) {
			hashMap = new SparseArray<View>();
			view.setTag(hashMap);
		}
		View childView = hashMap.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			hashMap.put(id, childView);
		}
		return (T) childView;
	}

	private static SharedPreferences mDefPref;

	public static boolean isDayChange(Context context) {
		return getDefPref(context).getBoolean(DAY_CHANGE, false);
	}

	public static void setDayChange(Context context, boolean isDayChange) {
		getDefPref(context).edit().putBoolean(DAY_CHANGE, isDayChange).commit();
	}

	public static String DAY_CHANGE = "day_change";

	public static SharedPreferences getDefPref(Context context) {
		if (mDefPref == null) {
			mDefPref = PreferenceManager.getDefaultSharedPreferences(context);
		}
		return mDefPref;
	}

	/**
	 * @data :2014年9月18日下午3:26:44
	 * @param context
	 * @return
	 * @description :获取屏幕宽度
	 */
	public static int getScreenWidth(Activity context) {
		return getMetrics(context).widthPixels;
	}

	public static DisplayMetrics getMetrics(Activity context) {
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics;
	}

	/**
	 * @data :2014年9月18日下午3:26:44
	 * @param context
	 * @return
	 * @description :获取屏幕高度
	 */
	public static int getScreenHeight(Activity context) {
		return getMetrics(context).heightPixels;
	}

	/** ------------------------- */
	/**
	 * 获取手机是否链接网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnection(Context context) {
		ConnectivityManager manager = getConnectivityManager(context);
		if (manager == null) {
			return false;
		}
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		boolean isAvailable;
		if (networkInfo != null) {
			isAvailable = networkInfo.isAvailable();
		} else {
			isAvailable = false;
		}
		Log.i("ConnectionVerdict", isAvailable + "");
		return isAvailable;
	}

	/**
	 * 获取手机联网的类型
	 * 
	 * @param context
	 */
	public static String getConnectionType(Context context) {
		boolean connection = isConnection(context);
		if (connection) {
			ConnectivityManager manager = getConnectivityManager(context);
			NetworkInfo networkInfo = manager.getActiveNetworkInfo();
			String typeName = networkInfo.getTypeName();
			Log.i("ConnectionVerdict", typeName);
			return typeName;
		} else {
			return null;
		}
	}

	/**
	 * 判断WiFi开关是否打开
	 * 
	 * @return
	 */
	public boolean isWifiEnabled(Context context) {
		ConnectivityManager mgrConn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager mgrTel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		NetworkInfo info = mgrConn.getActiveNetworkInfo();
		return ((info != null && info.getState() == NetworkInfo.State.CONNECTED) || mgrTel
				.getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
	}

	/**
	 * 判断当前使用的网络是否WiFi
	 * 
	 * @return
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager manager = getConnectivityManager(context);
		NetworkInfo networkINfo = manager.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 判断当前链接的网络是否是手机流量网络
	 * 
	 * @return
	 */
	public static boolean isMobile(Context context) {
		ConnectivityManager manager = getConnectivityManager(context);
		NetworkInfo networkINfo = manager.getActiveNetworkInfo();
		if (networkINfo != null
				&& networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/**
	 * 获取联网的Manager
	 * 
	 * @param context
	 * @return
	 */
	private static ConnectivityManager getConnectivityManager(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (mConnectivityManager == null) {
			return null;
		}
		return mConnectivityManager;
	}

	/** ------------------------- */
	/**
	 * 判断网络是否连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnect(Context context) {
		boolean isConnected = false;
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (mobile == State.CONNECTED || mobile == State.CONNECTING) {
			isConnected = true;
		} else if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
			isConnected = true;
		}
		return isConnected;
	}

	/** 显示toast提示 */
	public static void showToast(Context context, String content) {
		Toast.makeText(context, content, 0).show();
	}

	/** 网络已死 */
	public static void showNetisDead(Context context) {
		Toast.makeText(context, "网络不给力~", 0).show();
	}

	public static void money(final Context context, boolean needTell) {
		if (isConnection(context)) {
			SpotManager.getInstance(context).showSpotAds(context,
					new SpotDialogListener() {
						@Override
						public void onShowSuccess() {
							Log.i("majunm", "onShowSuccess.............");
							// ISSUCCESS = true;
						}

						@Override
						public void onShowFailed() {
							try {
								Toast.makeText(context, "加载广告失败鸟.........", 0)
										.show();
								Log.i("majunm", "onShowFailed.................");
							} catch (Exception e) {
								e.printStackTrace();
								Log.i("majunm", "unknown....." + e.getMessage());
							}
						}

						@Override
						public void onSpotClosed() {

						}
					});
		}
		if (!isConnection(context) && needTell) {
			Toast.makeText(context, "当前无网络，感谢您的支持...", 0).show();
		}
	}

}
