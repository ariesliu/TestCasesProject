package com.qtest.ruletestcases;

/*  
 * 用SharedPreference来保存数据时，涉及敏感信息的key在命名时需使用缩写或者其他不易知其意的命名。
 */

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * @author weihao
 * @category SharedPerferenceDataRuleTest规则测试用例
 */
public class SharedPerferenceDataRuleTest extends Activity{
	/**
	 * 
	 */
	//isTriger=true
	public void test() {
		SharedPreferences sharedPreferences = getSharedPreferences("ljq123", CONTEXT_IGNORE_SECURITY);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		int age=0;
		editor.putInt("KEY_PHONENUMBER", age);// 触发规则
		editor.putString("KEY_SERVER_IP_ADDRESS", String.valueOf(age));// 触发规则
	}

}
