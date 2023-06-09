package com.c196.exam.ui.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.c196.exam.R;
import com.c196.exam.database.DatabaseHelper;
import com.c196.exam.entities.Term;
import com.c196.exam.ui.widgets.DatePicker;
import com.c196.exam.utility.DateManager;

public class CreateTermDialogFragment extends DialogFragment {
    public static String TAG = "CreateTermDialog";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        LinearLayout layout = new LinearLayout(this.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        final EditText termName = new EditText(this.getContext());
        final EditText startDate = new DatePicker(this.requireContext(), this.getChildFragmentManager(), "Start Date");
        final EditText endDate = new DatePicker(this.requireContext(), this.getChildFragmentManager(), "End Date");
        termName.setHint("Term name");
        layout.addView(termName);
        layout.addView(startDate);
        layout.addView(endDate);
        builder.setTitle("Create a new term");
        builder.setView(layout)
                // Add action buttons
                .setPositiveButton("Create", (dialog, id) -> {
                    long startTs = 0;
                    String startStr = "";
                    try{
                        startTs = DateManager.convertString(startDate.getText().toString());
                    } catch (Exception ex){
                        Toast t = new Toast(this.getContext());
                        t.setText("Please ensure your start date is in yyyy-MM-dd format.");
                        t.show();
                    }

                    try{
                        startStr = DateManager.convertLong(startTs);
                    } catch (Exception ex){
                        Log.e("EX", ex.getMessage());
                    }

                    //Term t = new Term(termName.getText().toString(), startDate.get);
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