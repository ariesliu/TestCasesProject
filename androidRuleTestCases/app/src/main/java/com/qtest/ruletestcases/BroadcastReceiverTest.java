package com.qtest.ruletestcases;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by liujun-iri on 17/5/17.
 */

public class BroadcastReceiverTest extends BroadcastReceiver {

    NotificationManager mn=null;
    Notification notification=null;
    Context ct=null;
    BroadcastReceiverTest receiver;
    public BroadcastReceiverTest(){}

    public BroadcastReceiverTest(Context c){
        ct=c;
        receiver=this;
    }

    //注册
    public void registerAction(String action){
        IntentFilter filter=new IntentFilter();
        filter.addAction(action);
        ct.registerReceiver(receiver, filter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
            mn.notify(0, notification);
        }
}
