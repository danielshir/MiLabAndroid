package com.idc.milab.milabandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.ResultReceiver;
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

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";
	private static final String CHANNEL_ID = "NotifyChannel";

	protected int mNotificationId = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.i(TAG, "Activity has been launched!");

		registerNotificationChannel();

		Button notifyButton = (Button)findViewById(R.id.btnNotify);
		notifyButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// action for when the notification is clicked
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);

				NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle("This is the title")
						.setContentText("This is the text")
						.setContentIntent(pendingIntent)
						.setPriority(NotificationCompat.PRIORITY_DEFAULT)
						.setAutoCancel(true); // remove after tap

				NotificationManager nm = (NotificationManager)MainActivity.this.getSystemService(NOTIFICATION_SERVICE);
				nm.notify(mNotificationId++, builder.build());
			}
		});
	}

	protected void registerNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
			nm.createNotificationChannel(new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT));
		}
	}
}
