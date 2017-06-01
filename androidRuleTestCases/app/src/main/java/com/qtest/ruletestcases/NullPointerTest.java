package com.qtest.ruletestcases;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class NullPointerTest {
	private String st1;

	public  void test1() {
		List<String> list = null;
		if (list.contains("test")) {
			System.out.println("test return");
			return;
		}

		if (st1.equals("test") && st1 != null) {
		}
	}

	public String bar(String string) {
		// should be &&
		if (string != null || !string.equals(""))
			return string;
		// should be ||
		if (string == null && string.equals(""))
			return string;
		return null;
	}

	public  void test2(String str) {
		str.replace("a", "b");
	}

	public  void test3() {
		File file = new File("");
		try {
			FileInputStream in = new FileInputStream(file);
			in.close();
			in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
