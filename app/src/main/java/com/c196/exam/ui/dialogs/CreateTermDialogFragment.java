package com.c196.exam.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.c196.exam.R;
import com.c196.exam.database.DatabaseHelper;
import com.c196.exam.entities.Term;

public class CreateTermDialogFragment extends DialogFragment {
    public static String TAG = "CreateTermDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText termName = new EditText(getContext());
        termName.setHint("Term name");

        builder.setTitle("Create a new term");
        builder.setView(termName)
                // Add action buttons
                .setPositiveButton("Create", (dialog, id) -> {
                    Term t = new Term();
                    t.setTitle(termName.getText().toString());
                    SQLiteDatabase db = new DatabaseHelper(getContext()).getDb();
                    Log.d("INFO", "DB Open: " + db.isOpen());
                })
                .setNegativeButton("Cancel", (DialogInterface.OnClickListener) (dialog, id) -> {
                    CreateTermDialogFragment.this.getDialog().cancel();
                });
        return builder.create();
    }
}

//https://developer.android.com/develop/ui/views/components/dialogs#CustomLayout