package com.ethero;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        Log.d("Main", message.getData().toString());

        Intent intent = new Intent("update-event");

        String url = message.getData().get("url");
        intent.putExtra("url", url);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("image", "https://c.tenor.com/M4ORXivVGfIAAAAd/azur-lane-le-malin.gif");
        editor.putString("text", url);

        editor.apply();

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
