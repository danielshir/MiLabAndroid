package com.idc.milab.milabandroid;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	private class ConcatResultReceiver extends ResultReceiver {
		public ConcatResultReceiver(Handler handler) {
			super(handler);
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			final String result = resultData.getString(ConcatService.EXTRA_RESULT_CONCAT);
			((EditText)findViewById(R.id.result)).setText(result);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button concatButton = (Button)findViewById(R.id.btnConcat);
		concatButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String part1 = ((EditText)findViewById(R.id.part1)).getText().toString();
				String part2 = ((EditText)findViewById(R.id.part2)).getText().toString();
				ConcatService.doActionConcat(MainActivity.this, new ConcatResultReceiver(new Handler()), part1, part2);
			}
		});
	}
}
