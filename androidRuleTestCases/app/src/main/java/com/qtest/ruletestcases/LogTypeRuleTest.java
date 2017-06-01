package com.qtest.ruletestcases;

import android.util.Log;

import java.util.logging.Logger;


/**
 * @author weihao
 * @category 日志类规则测试
 */
public class LogTypeRuleTest {
	/**
	 * LogBlockRule 规则测试 Log中不要输出敏感信息,例如pid、uid、imei号等
	 */
	public void test1() {
		int pid = android.os.Process.myPid();
		Log.i("pid", String.valueOf(pid));// 触发规则
	}

	/**
	 * LogAssignmentRule 规则测试 不要在Log方法中对变量进行赋值操作（部分业务适用）
	 */
	public void test2() {
		int a=0;
		int b1;
		Log.i("a: " ,String.valueOf(a++));// 触发规则
		Log.i("b1: " , String.valueOf(b1 = a));// 触发规则
	}

	/**
	 * 建议使用if(DEBUG)包裹Log方法,日志开关不要封装在日志所在的函数中
	 */
	public void test3() {

		Logger Log = Logger.getLogger("log");
		boolean DEBUG = true; // 开关不用定义在函数内部
		int a = 1;
		int b1;
		if (DEBUG) {
			Log.info("a: " + a);// 触发规则
		}
		Log.info("a: " + a);// 触发规则
	}

}
