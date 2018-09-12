package com.idc.milab.milabandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart called");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume called");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button pushButton = (Button)findViewById(R.id.button);
		pushButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d(TAG, "Pushed button");
				Toast.makeText(view.getContext(), "Hello World!", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
