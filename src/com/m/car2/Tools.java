package com.m.car2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class Tools {
	/** ��ȡ���� position ��ӦƷ�� 0 = ��˹��˹....*/
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
			e.printStackTrace();
		}
		Log.e("car", "sb.toString()=" + sb.toString());
		return sb.toString();
	}

	private static ProgressDialog mProgressDialog;

	/**
	 * ��ʾProgressDialog
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
	 * �ر�ProgressDialog
	 */
	public static void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

}
