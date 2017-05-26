package com.qihoo.ruletestcases;

//setJavaScriptEnabled默认值为false；setAllowFileAccess默认为true；
//JELLY_BEAN版本及以后版本，AllowUniversalAccessFromFileURLs和AllowFileAccessFromFileURLs默认为false

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author weihao
 * @category WebViewRule测试demo
 */
public class WebViewRuleTest extends Activity {
	private WebView webview;
	/**
	 * @author weihao
	 * @param savedInstanceState
	 * @category setJavaScriptEnabled 和 setAllowFileAccess全部设置为true
	 */
	// isTriger=true
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webview);
		// 设置WebView属性，能够执行Javascript脚本
		webview.getSettings().setJavaScriptEnabled(true);// 触发规则
		webview.getSettings().setAllowFileAccess(true);// 触发规则
		// 加载需要显示的网页
		webview.loadUrl("http://www.so.com/");
		// 设置Web视图
		webview.setWebViewClient(new HelloWebViewClient());
	}

	class HelloWebViewClient extends WebViewClient{}

	/**
	 * 
	 * @param savedInstanceState
	 * @category setJavaScriptEnabled 和setAllowFileAccess未显示设置值。
	 */
	// isTriger=true
	//@Override
	public void onCreate1(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webview);
		// 设置WebView属性，能够执行Javascript脚本
		// webview.getSettings().setJavaScriptEnabled(false);//触发规则
		// webview.getSettings().setAllowFileAccess(false);//触发规则
		// 加载需要显示的网页
		webview.loadUrl("http://www.so.com/");
		// 设置Web视图
		webview.setWebViewClient(new HelloWebViewClient());
	}
	
	/**
	 * 
	 * @param savedInstanceState
	 * @category setJavaScriptEnabled 为默认值为false 和setAllowFileAccess为false。
	 */
	// isTriger=false
	//@Override
	public void onCreate2(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webview);
		// 设置WebView属性，能够执行Javascript脚本
		// webview.getSettings().setJavaScriptEnabled(false);//触发规则
		 webview.getSettings().setAllowFileAccess(false);//触发规则
		// 加载需要显示的网页
		webview.loadUrl("http://www.so.com/");
		// 设置Web视图
		webview.setWebViewClient(new HelloWebViewClient());
	}

	/**
	 * 
	 * @param savedInstanceState
	 * @category setJavaScriptEnabled 为false； setAllowFileAccess设置为false
	 */
	// isTriger=false
	//@Override
	public void onCreate3(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		webview = (WebView) findViewById(R.id.webview);
		// 设置WebView属性，能够执行Javascript脚本
		webview.getSettings().setJavaScriptEnabled(false);// 触发规则
		webview.getSettings().setAllowFileAccess(false);// 触发规则
		// 加载需要显示的网页
		webview.loadUrl("http://www.so.com/");
		// 设置Web视图
		webview.setWebViewClient(new HelloWebViewClient());
	}

}
