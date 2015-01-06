package com.m.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.m.base.BaseActivity;
import com.m.car2.R;
import com.m.util.Tools;

public class MaJunTestWebViewActivity extends BaseActivity {

	@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mini_web);

		WebView content_wv = (WebView) findViewById(R.id.mini_webview);
		WebSettings ws = content_wv.getSettings();
		ws.setJavaScriptEnabled(true); // 设置支持javascript脚本
		ws.setAllowFileAccess(true); // 允许访问文件
		ws.setBuiltInZoomControls(true); // 设置显示缩放按钮
		ws.setSupportZoom(true); // 支持缩放

		/**
		 * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
		 * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
		 */
		ws.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
		ws.setDefaultTextEncodingName("utf-8"); // 设置文本编码
		ws.setAppCacheEnabled(true);
		ws.setCacheMode(WebSettings.LOAD_DEFAULT);// 设置缓存模式

		// 添加Javascript调用java对象
		content_wv.addJavascriptInterface(this, "java2js");
		content_wv.setWebViewClient(new WebViewClientDemo());
		content_wv.setWebChromeClient(new WebViewChromeClientDemo());
		// 设置打开的网页
		// content_wv.loadUrl("http://orgcent.com");
		// content_wv.loadUrl("http://www.baidu.com");
		// content_wv.loadUrl("http://apk.hiapk.com/");
		content_wv.loadUrl("http://m.apk.hiapk.com");
		// // 使用WebView来显示图片
		// content_wv.loadData("<img src='http://orgcent.com'/>", "text/html",
		// "utf8");
		backButton.setOnClickListener(this);
	}

	private class WebViewClientDemo extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
			return true;
		}
	}

	private class WebViewChromeClientDemo extends WebChromeClient {
		// 设置网页加载的进度条
		public void onProgressChanged(WebView view, int newProgress) {
		}

		// 获取网页的标题
		public void onReceivedTitle(WebView view, String title) {
		}

		// JavaScript弹出框
		@Override
		public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
			return super.onJsAlert(view, url, message, result);
		}

		// JavaScript输入框
		@Override
		public boolean onJsPrompt(WebView view, String url, String message,
				String defaultValue, JsPromptResult result) {
			return super.onJsPrompt(view, url, message, defaultValue, result);
		}

		// JavaScript确认框
		@Override
		public boolean onJsConfirm(WebView view, String url, String message,
				JsResult result) {
			return super.onJsConfirm(view, url, message, result);
		}
	}

	@Override
	public void resetNavigation() {
		carCommonMore.setVisibility(View.GONE);
		carCommonTitle.setText("评分");
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.car_copyright_return:
			Tools.beginExit(mContext);
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Tools.beginExit(mContext);
			return false;
		}
		return super.onKeyDown(keyCode, event);
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
		carCommonTitle.setTextColor(Color.parseColor("#B7B7B7"));
		actionbarLayout.setBackgroundResource(R.drawable.car_night_titlebg);
	}

	/** 白天 */
	private void day() {
		carCommonTitle.setTextColor(Color.parseColor("#4C4D4E"));
		actionbarLayout.setBackgroundResource(R.drawable.car_title_bg);
	}

}