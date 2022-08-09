package com.ethero;

import android.content.*;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private String imageUrl = "https://c.tenor.com/gPUlRZ1w3fUAAAAC/akairo-azur-lane.gif";
    private String activityText = "Not yet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView imageView = findViewById(R.id.web_image);
        imageView.getSettings().setLoadWithOverviewMode(true);
        imageView.getSettings().setUseWideViewPort(true);

        TextView text = findViewById(R.id.text_notification);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setLinkTextColor(Color.BLUE);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        String url = sharedPref.getString("image", imageUrl);
        imageView.loadUrl(url);

        String savedText = sharedPref.getString("text", activityText);
        text.setText(savedText);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("update-event"));
    }


    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("Broadcast", "Broadcast receiver");


            String url = intent.getStringExtra("url");
            String image = "https://c.tenor.com/M4ORXivVGfIAAAAd/azur-lane-le-malin.gif";

            TextView text = findViewById(R.id.text_notification);
            text.setMovementMethod(LinkMovementMethod.getInstance());
            text.setLinkTextColor(Color.BLUE);
            text.setText(url);

            WebView imageView = findViewById(R.id.web_image);
            imageView.getSettings().setLoadWithOverviewMode(true);
            imageView.getSettings().setUseWideViewPort(true);
            imageView.loadUrl(image);
        }
    };

    public void onRefresh(View view) {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        TextView text = findViewById(R.id.text_notification);
        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setLinkTextColor(Color.BLUE);
        text.setText(activityText);

        WebView imageView = findViewById(R.id.web_image);
        imageView.getSettings().setLoadWithOverviewMode(true);
        imageView.getSettings().setUseWideViewPort(true);
        imageView.loadUrl(imageUrl);

        editor.clear();
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }
}

