package com.ethero;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService{
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d("Main", message.getData().toString());
        //Toast.makeText(getApplicationContext(), message.getData().toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("update-event");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
