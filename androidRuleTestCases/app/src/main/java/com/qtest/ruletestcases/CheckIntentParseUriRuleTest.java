package com.qtest.ruletestcases;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.net.URISyntaxException;
/*
 * Android的Activity通过intent进行应用内部或应用之间的数据传输，
 * 如果组件没有正确的处理数据来源，可能会导致安全问题。
 */

public class CheckIntentParseUriRuleTest extends Activity {
public void test() throws URISyntaxException{
	Uri uri  = getIntent().getData();
    Intent intent = Intent.parseUri(uri.toString(),0);
}
public void test1() throws URISyntaxException{
	Uri uri  = getIntent().getData();
    Intent intent = Intent.parseUri(uri.toString(),0);
    intent.addCategory("android.intent.category.BROWSABLE");//如果调用该方法的组件没有对外暴露，可以忽略该设置。
    intent.setComponent(null);
    intent.setSelector(null);
}
}
