package com.jagdon.menu;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Dialog Fragment")
                .setMessage("This is a dialog fragment")
                .setPositiveButton("Button 1", (dialog, id) -> {
                    // Handle the positive button click event here
                    dialog.dismiss(); // Closes the dialog
                })
                .setNegativeButton("Button 2", (dialog, id) -> {
                    // Handle the negative button click event here
                    dialog.dismiss(); // Closes the dialog
                });
        return builder.create();
    }

}