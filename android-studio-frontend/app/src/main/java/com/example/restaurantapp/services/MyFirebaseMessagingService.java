package com.example.restaurantapp.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.restaurantapp.R;
import com.example.restaurantapp.activities.LoginActivity;
import com.example.restaurantapp.activities.OrdersWaiterActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.logging.Handler;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("Firebase", "From: " + remoteMessage.getFrom());


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("Firebase", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    private void sendNotification(String from, String body) {
        // Create an intent for the activity you want to open when the notification is clicked
        Intent intent = new Intent(this, OrdersWaiterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Create a notification builder
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getDefaultChannelId())
                .setSmallIcon(R.drawable.app_icon)
                .setContentTitle(from)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(0, notificationBuilder.build());
        }
    }

    private String getDefaultChannelId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a default channel if it doesn't exist
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                String channelId = getString(R.string.default_notification_channel_id);
                NotificationChannel channel = new NotificationChannel(channelId, "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                return channelId;
            }
        }
        return "";
    }

}
