package pl.dakil.androt;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int clickCounter = 0;

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

        Button btn = findViewById(R.id.button);
        EditText nameInput = findViewById(R.id.name);
        EditText emailInput = findViewById(R.id.email);
        TextView welcome = findViewById(R.id.txt1);
        TextView clickCnt = findViewById(R.id.txt2);

        clickCnt.setText(getString(R.string.clicked, clickCounter));

        btn.setOnClickListener(l -> {
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, getString(R.string.reminder), Toast.LENGTH_LONG).show();
                return;
            }

            clickCounter++;

            welcome.setText(getString(R.string.welcome, name, email));
            clickCnt.setText(getString(R.string.clicked, clickCounter));
        });
    }
}