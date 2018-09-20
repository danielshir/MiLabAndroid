package com.idc.milab.milabandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	private class ExampleThread implements Runnable {
		@Override
		public void run() {
			Log.i(TAG, "Thread is now starting to run");

			int sum = 0;
			for (int i = 0; i < 1000; i++) {
				sum += i;
			}

			Log.i(TAG, "Finished operation, sum is - " + sum);
		}
	}

	private class LongOperation implements Runnable {
		@Override
		public void run() {
			Log.i(TAG, "Starting very long operation");

			int sum = 0;
			for (int i = 0; i < 1000; i++) {
				SystemClock.sleep(200);
				sum += i;
			}

			Log.i(TAG, "Very long operation is now over");
		}
	}

	private class ExampleTask extends AsyncTask<Integer, Integer, Integer> {
		@Override
		protected Integer doInBackground(Integer... params) {
			int sum = 0;
			for (int i = 0; i < params[0]; i++) {
				SystemClock.sleep(200);
				sum += i;

				if (i % 10 == 0) {
					float percentage = (float) i / (float) params[0];
					publishProgress((int) (percentage * 100));
				}
			}

			return sum;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.i(TAG, "[" + this.hashCode() + "] Operation progress at " + values[0] + "%");
		}

		@Override
		protected void onPostExecute(Integer integer) {
			Log.i(TAG, "Task is done");
			Toast.makeText(MainActivity.this, "Operation is now complete!", Toast.LENGTH_SHORT).show();
		}
	}

	private class ThreadPerTaskExecutor implements Executor {
		public void execute(Runnable r) {
			new Thread(r).start();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Example 1: Running in the main thread
//		ExampleThread th = new ExampleThread();
//		th.run();

		// Example 2: Running in a new thread
//		ExampleThread th = new ExampleThread();
//		new Thread(new ExampleThread()).start();

		// Example 3: Running long operation in main thread
//		new LongOperation().run();

		// Example 4: Using an AsyncTask to do the work
//		ExampleTask task = new ExampleTask();
//		task.execute(50);

		// Example 5: Running the async task after clicking the button (tasks run sequentially)
//		((Button)(findViewById(R.id.btnRun))).setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				ExampleTask task = new ExampleTask();
//				task.execute(50);
//			}
//		});

		// Example 6: Running the async task after clicking the button (tasks run in parallel)
//		((Button)(findViewById(R.id.btnRun))).setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				ExampleTask task = new ExampleTask();
//				task.executeOnExecutor(new ThreadPerTaskExecutor(), 50);
//			}
//		});

		// Example 7: Running a simple network request with Volley
//		((Button)findViewById(R.id.btnNetwork)).setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				doNetworkRequest();
//			}
//		});
	}

	private void doNetworkRequest() {
		final RequestQueue queue = Volley.newRequestQueue(this);
		final String url = "http://www.google.com";

		StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Log.d(TAG, "Response back from server - " + response);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.e(TAG, "Errpr - " + error);
			}
		});

		queue.add(req);
	}
}
