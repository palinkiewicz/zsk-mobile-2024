package edu.zsk.palinkiewicz;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NotificationActivity extends AppCompatActivity {

    private static final String[] LIST_ITEMS = {
            "Programowanie obiektowe",
            "Programowanie Aplikacji Mobilnych",
            "Programowanie Aplikacji Internetowych",
            "Programowanie Aplikacji Webowych",
            "Programowanie Aplikacji Desktopowych"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, LIST_ITEMS);
        ((ListView) findViewById(R.id.list)).setAdapter(adapter);
    }
}
