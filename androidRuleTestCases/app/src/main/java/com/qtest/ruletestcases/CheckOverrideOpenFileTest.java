package com.qtest.ruletestcases;
import android.content.ContentProvider;
import android.net.Uri;
import android.os.ParcelFileDescriptor;


import java.io.File;
import java.io.FileNotFoundException;

/*
 * Manifest.xml文件-应用组件provider导出；
 * 如果扫描到应用provider组件导出，且没有做权限限制，组件重写了ContentProvider.openFile()函数，
 * 却没有调用File.getCanonicalPath()函数进行过滤，存在安全隐患。
 */
public abstract class CheckOverrideOpenFileTest extends ContentProvider{
	// 目录遍历漏洞程序代码片段1：
	private static String IMAGE_DIRECTORY = "";
	@Override
	public ParcelFileDescriptor openFile(Uri paramUri, String paramString) throws FileNotFoundException {// 触发规则
		File file = new File(IMAGE_DIRECTORY, paramUri.getLastPathSegment());
		return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
	}
}
