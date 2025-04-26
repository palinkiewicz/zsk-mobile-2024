package edu.zsk.palinkiewicz;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AppDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireActivity())
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_content))
                .setIcon(R.drawable.baseline_warning_24)
                .setCancelable(true)
                .create();
    }
}