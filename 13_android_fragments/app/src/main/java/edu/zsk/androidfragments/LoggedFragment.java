package edu.zsk.androidfragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class LoggedFragment extends Fragment {
    public LoggedFragment() {
        super(R.layout.fragment_logged);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        String email = requireArguments().getString("email");
        String name = requireArguments().getString("name");
        String surname = requireArguments().getString("surname");

        Button btn = view.findViewById(R.id.button);

        btn.setOnClickListener(l -> {

        });
    }
}
