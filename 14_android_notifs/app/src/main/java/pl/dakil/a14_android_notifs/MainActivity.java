package pl.dakil.a14_android_notifs;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    final String CHANNEL_ID_HIGH = "AND_NOT_14_HIGH";
    final String CHANNEL_ID_LOW = "AND_NOT_14_LOW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        createNotificationChannel(CHANNEL_ID_HIGH, NotificationManager.IMPORTANCE_HIGH);
        createNotificationChannel(CHANNEL_ID_LOW, NotificationManager.IMPORTANCE_LOW);

        Button btn1 = findViewById(R.id.button1);

        btn1.setOnClickListener(l -> {
            Intent intent = new Intent(this, OneActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID_HIGH)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(getString(R.string.btn1))
                    .setContentText(getString(R.string.btn1_notif))
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (nm != null) {
                nm.notify(0, builder.build());
            }
        });

        Button btn2 = findViewById(R.id.button2);

        btn2.setOnClickListener(l -> {
            Intent intent = new Intent(this, TwoActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID_LOW)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(getString(R.string.btn2))
                    .setContentText(getString(R.string.btn2_notif))
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (nm != null) {
                nm.notify(0, builder.build());
            }
        });
    }

    private void createNotificationChannel(String channelId, int priority) {
        NotificationChannel nc = new NotificationChannel(
                channelId,
                getString(R.string.app_name),
                priority
        );

        NotificationManager nm = getSystemService(NotificationManager.class);

        if (nm != null) {
            nm.createNotificationChannel(nc);
        }
    }
}