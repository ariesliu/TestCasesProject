package com.qihoo.ruletestcases;
/*
 * 对Content Provider进行增删改查操作时，程序没有对用户的输入进行过滤，
 * 未采用参数化查询的方式，可能导致sql注入攻击。
 */

import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author weihao
 * @category 测试SQL注入
 */
public class CheckSqlRawqueryRuleTest {
	private SQLiteDatabase db;
	public void test() {
		db.rawQuery("select * from person", null);// 触发规则
	}

	public void test2() {
		SQLiteStatement sqLiteStatement = db.compileStatement("insert into msgTable(uid, msg) values(?, ?)");
		sqLiteStatement.bindLong(1, 12);
		sqLiteStatement.bindString(3, "text");
		long newRowId = sqLiteStatement.executeInsert();
	}
}
