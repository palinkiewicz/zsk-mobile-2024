package pl.dakil.onclick;

import android.os.Bundle;
import android.view.View;
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

        // Part 1
        Button button1 = findViewById(R.id.button);
        EditText edit1 = findViewById(R.id.input1);
        TextView textview1 = findViewById(R.id.textView2);

        button1.setOnClickListener(v -> {
            textview1.setText(edit1.getText().toString());
        });

        // Part 2
        Button button2 = findViewById(R.id.button2);
        EditText edit2 = findViewById(R.id.input2);

        button2.setOnClickListener(v -> {
            Toast.makeText(this, edit2.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }
}