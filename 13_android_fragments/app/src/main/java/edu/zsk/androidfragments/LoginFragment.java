package edu.zsk.androidfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        Button btn = view.findViewById(R.id.button);

        btn.setOnClickListener(l -> {
            String email = ((EditText) view.findViewById(R.id.email)).getText().toString();
            String name = ((EditText) view.findViewById(R.id.name)).getText().toString();
            String surname = ((EditText) view.findViewById(R.id.surname)).getText().toString();

            if (!email.isEmpty() && !name.isEmpty() && !surname.isEmpty() && email.matches("^[\\w-.]+@([\\w-]+.)+[\\w-]{2,4}$")) {
                Bundle bundle = new Bundle();
                bundle.putString("email", email);
                bundle.putString("name", name);
                bundle.putString("surname", surname);

                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container, LoggedFragment.class, bundle)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
