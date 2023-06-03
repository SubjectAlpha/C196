package com.c196.exam.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.c196.exam.R;

public class CreateTermDialogFragment extends DialogFragment {
    public static String TAG = "CreateTermDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_create_term, null))
                // Add action buttons
                .setPositiveButton("Create", (dialog, id) -> {
                    // sign in the user ...
                })
                .setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, id) -> {
                    CreateTermDialogFragment.this.getDialog().cancel();
                });
        return builder.create();
    }
}

// THIS IS WRONG DO THIS https://developer.android.com/develop/ui/views/components/dialogs#CustomLayout