package com.example.alertonmessage;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MainActivity extends AppCompatActivity
{
    SharedPreferences sharedpreferences;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        sharedpreferences = getSharedPreferences("MyNotifications", Context.MODE_PRIVATE);
        createNotificationChannel();
        Button button = findViewById(R.id.button);
        EditText mytext = findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("messageKey");
                editor.commit();
                addNotification(mytext.getText().toString());
            }
        });
    }

    private void addNotification(String Notifymessage)
    {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("messageKey", Notifymessage);
        editor.commit();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "MyNotifications")
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("New Message")
                .setContentText(Notifymessage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(100, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel()
    {
        CharSequence name = "myChannel";
        String description = "Channel for learning about notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel("MyNotifications", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
