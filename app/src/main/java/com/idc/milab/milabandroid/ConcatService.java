package com.idc.milab.milabandroid;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;

import javax.xml.transform.Result;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class ConcatService extends IntentService {
	private static final String ACTION_CONCAT = "com.idc.milab.milabandroid.action.CONCAT";

	private static final String EXTRA_PARAM_CONCAT_PART1 = "com.idc.milab.milabandroid.extra.EXTRA_PARAM_CONCAT_PART1";
	private static final String EXTRA_PARAM_CONCAT_PART2 = "com.idc.milab.milabandroid.extra.EXTRA_PARAM_CONCAT_PART2";
	private static final String EXTRA_PARAM_CONCAT_RECEIVER = "com.idc.milab.milabandroid.extra.EXTRA_PARAM_CONCAT_RECEIVER";
	public static final String EXTRA_RESULT_CONCAT = "com.idc.milab.milabandroid.extra.EXTRA_RESULT_CONCAT";
	private static final int RESULT_CODE_SUCCESS = 0;


	public ConcatService() {
		super("ConcatService");
	}

	public static void doActionConcat(Context context, ResultReceiver receiver, String param1, String param2) {
		Intent intent = new Intent(context, ConcatService.class);
		intent.setAction(ACTION_CONCAT);
		intent.putExtra(EXTRA_PARAM_CONCAT_PART1, param1);
		intent.putExtra(EXTRA_PARAM_CONCAT_PART2, param2);
		intent.putExtra(EXTRA_PARAM_CONCAT_RECEIVER, receiver);
		context.startService(intent);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if (intent != null) {
			final String action = intent.getAction();
			if (ACTION_CONCAT.equals(action)) {
				final String param1 = intent.getStringExtra(EXTRA_PARAM_CONCAT_PART1);
				final String param2 = intent.getStringExtra(EXTRA_PARAM_CONCAT_PART2);
				final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_PARAM_CONCAT_RECEIVER);
				handleActionConcat(receiver, param1, param2);
			} else {
				throw new RuntimeException("Unknown action provided");
			}
		}
	}

	private void handleActionConcat(ResultReceiver receiver, String param1, String param2) {
		final String result = param1 + param2;
		final Bundle bundle = new Bundle();
		bundle.putString(EXTRA_RESULT_CONCAT, result);
		receiver.send(RESULT_CODE_SUCCESS, bundle);
	}
}
