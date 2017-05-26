package com.qihoo.ruletestcases;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*
 * Manifest.xml文件-activity导出
 * 如果Intent在使用时未做异常处理，并且该intent所在的activity组件被设置为导出，
 * 且没有对调用者身份进行判断或访问权限控制，这种情况下会导致拒绝服务。
 * */
public class DDosBlockRuleTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.support_simple_spinner_dropdown_item);

		Intent i = getIntent();
		String url = i.getStringExtra("url");// 触发规则

	}
}
