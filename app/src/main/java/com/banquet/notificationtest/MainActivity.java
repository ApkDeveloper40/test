package com.banquet.notificationtest;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView activityName;
    Button notification;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityName = findViewById(R.id.tv_activityName);
        notification = findViewById(R.id.btn_notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                i++;
                // Create an Intent for the activity you want to start
                Intent resultIntent = new Intent(MainActivity.this, ActivityA.class);
                resultIntent.putExtra("tag","hi hello");
// Create the TaskStackBuilder and add the intent, which inflates the back stack
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
                stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                String CHANNEL_ID = "my_channel_01";// The id of the channel.
                CharSequence name = "my_channel_02";// The user-visible name of the channel.
                int importance = NotificationManager.IMPORTANCE_HIGH;
                String GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL";

                Notification newMessageNotification1 =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Notification 1")
                                .setContentText("You will not believe...")
                                .setContentIntent(resultPendingIntent)
                                .setGroup(GROUP_KEY_WORK_EMAIL)
                                .build();

                Notification newMessageNotification2 =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_launcher_background)
                                .setContentTitle("Notification 2")
                                .setContentText("Please join us to celebrate the...")
                                .setContentIntent(resultPendingIntent)
                                .setGroup(GROUP_KEY_WORK_EMAIL)
                                .build();
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ae);





                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "yu")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("My notification")
                        .setContentText("Hello World! "+i)
                        .setChannelId(CHANNEL_ID)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("SubTitle")
                                .setBigContentTitle("Title"))// Set the intent that will fire when the user taps the notification
                        .setContentIntent(resultPendingIntent)
                        .setLargeIcon(largeIcon)
                        //set this notification as the summary for the group
                        .setAutoCancel(true);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                   /*mNotificationManager.notify(3, newMessageNotification1);
                    mNotificationManager.notify(4, newMessageNotification2);*/
                    mNotificationManager.createNotificationChannel(mChannel);
                    mNotificationManager.notify("app name",i, builder.build());
                }else {

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                    /*notificationManager.notify(3, newMessageNotification1);
                    notificationManager.notify(4, newMessageNotification2);*/

// notificationId is a unique int for each notification that you must define
                    notificationManager.notify("app name",i, builder.build());
                }


            }
        });

        activityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("distance",distance(19.0212489,72.9701638,18.234620,73.4311812)+"");

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
