package com.banquet.notificationtest;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView activityName;
    Button notification;
    int i = 0;
    PendingIntent resultPendingIntent;
    private static final String KEY_NOTIFICATION_GROUP = "KEY_NOTIFICATION_GROUP";
    NotificationUtil notificationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityName = findViewById(R.id.tv_activityName);
        notification = findViewById(R.id.btn_notification);

        // Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(MainActivity.this, ActivityA.class);
        resultIntent.putExtra("tag", "hi hello");
        // Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
        // Get the PendingIntent containing the entire back stack
        resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationUtil = new NotificationUtil(this);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                NotificationCompat.Builder notification;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    notification = notificationUtil.createNotificationBuilder("First notification", "This is the first bundled notification", resultPendingIntent);
                } else {
                    notification = notificationUtil.createCustomNotificationBuilder("Hie", "Hello", resultPendingIntent);

                }
                notification.setGroup(KEY_NOTIFICATION_GROUP);
                notificationUtil.showNotification(notification.build(), i);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    NotificationCompat.Builder summaryBuilder = notificationUtil.createSummaryBuilder();
                    summaryBuilder.setGroup(KEY_NOTIFICATION_GROUP);
                    notificationUtil.showNotification(summaryBuilder.build(), 0);
                }
            }
        });

        activityName.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Log.d("distance", distance(19.0212489, 72.9701638, 18.234620, 73.4311812) + "");
            }
        });
    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityName.setText("Activity Main");
    }
}
