package com.qihoo.ruletestcases;
/*
 * Manifest.xml-activity导出
 *  导出的Activity继承PreferenceActivity,且未重写PreferenceActivity.isValidFragment方法，
 *  可能导致Fragment注入漏洞
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import java.util.ArrayList;

import static android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT;
import static android.preference.PreferenceActivity.EXTRA_SHOW_FRAGMENT_ARGUMENTS;

public class FragmentRuleTest extends PreferenceActivity {
	private static final int BACK_STACK_PREFS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String initialFragment = getIntent().getStringExtra(EXTRA_SHOW_FRAGMENT);
		Bundle initialArguments = getIntent().getBundleExtra(EXTRA_SHOW_FRAGMENT_ARGUMENTS);

		if (savedInstanceState != null) {
		} else {
			boolean mSinglePane=false;
			if (initialFragment != null && mSinglePane) {
				switchToHeader(initialFragment, initialArguments);
				} else {
				ArrayList mHeaders= new ArrayList();
				if (mHeaders.size() > 0) {
						if (!mSinglePane) {
							if (initialFragment == null) {
							} else {
								switchToHeader(initialFragment, initialArguments);
							}
						}
					}
				}
			}
		}

	public void switchToHeader(String fragmentName, Bundle args) {

	}




}
