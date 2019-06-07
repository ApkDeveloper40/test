package com.banquet.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateUtils;
import android.widget.RemoteViews;

public class NotificationUtil {

    private Context context;
    private String CHANNEL_ID = "my_channel_01";// The id of the channel.

    public NotificationUtil(Context context) {
        this.context = context;
    }

    private RemoteViews createRemoteViews(int layout, int iconResource,
                                          String title, String message) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), layout);
        remoteViews.setImageViewResource(R.id.image_icon, iconResource);
        remoteViews.setTextViewText(R.id.text_title, title);
        remoteViews.setTextViewText(R.id.text_message, message);
        remoteViews.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME));
        return remoteViews;
    }

    public NotificationCompat.Builder createNotificationBuilder(String title, String message, PendingIntent resultPendingIntent) {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher_background);
        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setChannelId(CHANNEL_ID)
                .setLargeIcon(largeIcon)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(resultPendingIntent)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setAutoCancel(true);
    }

    public NotificationCompat.Builder createCustomNotificationBuilder(String title, String message, PendingIntent resultPendingIntent) {

        RemoteViews remoteViews = createRemoteViews(R.layout.custom_notification, R.drawable.ic_launcher_background,
                title, message);

        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setCustomContentView(remoteViews)
                .setChannelId(CHANNEL_ID)
                .setContentIntent(resultPendingIntent)
                .setContentTitle(title)
                .setContentText(message)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[0]);
    }

    public NotificationCompat.Builder createSummaryBuilder() {
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_launcher_background);
        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setChannelId(CHANNEL_ID)
                .setLargeIcon(largeIcon)
                .setGroupSummary(true)
                .setShowWhen(true)
                .setWhen(System.currentTimeMillis())
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setAutoCancel(true);
    }

    public void showNotification(Notification notification, int id) {
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "my_channel_02";// The user-visible name of the channel.
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mNotificationManager.createNotificationChannel(mChannel);
        }
        mNotificationManager.notify(id, notification);
    }
}
