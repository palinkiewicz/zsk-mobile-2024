package edu.zsk.palinkiewicz;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.content.Intent;
import android.app.PendingIntent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class LoggedInActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "2137";
    private static final String CHANNEL_NAME = "Zadanie podsumowujące";
    private String activeFragment = "first";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_logged_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentHolder, new FirstFragment())
                .commit();

        findViewById(R.id.changeFragmentButton).setOnClickListener(v -> changeFragment());
        findViewById(R.id.showNotificationButton).setOnClickListener(v -> sendNotification());
    }

    private void changeFragment() {
        if (activeFragment.equals("first")) {
            activeFragment = "second";
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, new SecondFragment())
                    .commit();
        } else {
            activeFragment = "first";
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentHolder, new FirstFragment())
                    .commit();
        }
    }

    private void sendNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, getString(R.string.error_no_permissions), Toast.LENGTH_SHORT).show();
        }
        NotificationManagerCompat.from(this).notify(1, builder.build());
    }

    public void openDialog() {
        AppDialogFragment dialog = new AppDialogFragment();
        dialog.setCancelable(true);
        dialog.show(fragmentManager, "AppDialog");
    }
}