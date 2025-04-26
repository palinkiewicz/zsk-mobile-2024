package edu.zsk.palinkiewicz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;

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

        initDb();

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            String email = ((EditText) findViewById(R.id.emailInput)).getText().toString().trim();
            String password = ((EditText) findViewById(R.id.passwordInput)).getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_input_all), Toast.LENGTH_LONG).show();
            } else if (!checkCredentials(email, password)) {
                Toast.makeText(this, getString(R.string.error_incorrect), Toast.LENGTH_LONG).show();
                ((EditText) findViewById(R.id.passwordInput)).setText("");
            } else {
                Toast.makeText(this, getString(R.string.success_logged), Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoggedInActivity.class));
            }
        });
    }

    private void initDb() {
        db = openOrCreateDatabase("users.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT, " +
                "password TEXT)");

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) == 0) {
                db.execSQL("INSERT INTO users (email, password) VALUES ('admin@example.com', 'admin')");
                db.execSQL("INSERT INTO users (email, password) VALUES ('user1@example.com', 'user1')");
                db.execSQL("INSERT INTO users (email, password) VALUES ('user2@example.com', 'user2')");
                db.execSQL("INSERT INTO users (email, password) VALUES ('user3@example.com', 'user3')");
            }
        }
        cursor.close();
    }

    private boolean checkCredentials(String email, String password) {
        Cursor cursor = db.rawQuery("SELECT password FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            String dbPassword = cursor.getString(0);
            cursor.close();
            return dbPassword.equals(password);
        }
        cursor.close();
        return false;
    }
}