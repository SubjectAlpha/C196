package com.c196.exam.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class CreateTermDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Create new term")
                .setPositiveButton("Save", (dialog, id) -> {
                    // START THE GAME!
                })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // User cancelled the dialog
                });
        // Create the AlertDialog object and return it
        return builder.create();

    }
}

// THIS IS WRONG DO THIS https://developer.android.com/develop/ui/views/components/dialogs#CustomLayout