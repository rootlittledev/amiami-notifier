package com.ethero;

import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        Log.d("main", message.getData().toString());

        Intent intent = new Intent("update-event");

        String url = message.getData().get("url");
        intent.putExtra("url", url);

        String image = message.getData().get("image");
        intent.putExtra("image", image);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
