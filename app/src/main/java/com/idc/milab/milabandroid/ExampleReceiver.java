package com.idc.milab.milabandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ExampleReceiver extends BroadcastReceiver {
	private static final String TAG = "ExampleReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "notification was tapped");
	}
}
