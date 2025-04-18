package pl.dakil.Exam1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        Button btn = findViewById(R.id.button);
        TextView output = findViewById(R.id.textView7);

        btn.setOnClickListener(l -> {
            String email = ((EditText) findViewById(R.id.editTextTextEmailAddress)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
            String confirmPassword = ((EditText) findViewById(R.id.editTextTextPassword2)).getText().toString();

            if (!email.contains("@")) {
                output.setText("Nieprawidłowy adres e-mail");
            } else if (!password.equals(confirmPassword)) {
                output.setText("Hasła się różnią");
            } else {
                output.setText(getString(R.string.welcome, email));
            }
        });
    }
}