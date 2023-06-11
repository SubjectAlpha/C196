package com.c196.exam.ui.home;

import android.app.DialogFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.c196.exam.database.DatabaseHelper;
import com.c196.exam.databinding.FragmentHomeBinding;
import com.c196.exam.entities.Term;
import com.c196.exam.ui.dialogs.CreateTermDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.addCourse.setOnClickListener(view -> {
            new CreateTermDialogFragment().show(getChildFragmentManager(), CreateTermDialogFragment.TAG);
        });

        DatabaseHelper helper = new DatabaseHelper(this.getContext());
        ArrayList<Term> terms = helper.getTerms();
        LinearLayout ll = new LinearLayout(this.getContext());
        ll.setOrientation(LinearLayout.VERTICAL);

        for(Term t: terms){
            TextView termName = new TextView(this.getContext());
            termName.setText(t.getTitle());
            ll.addView(termName);
        }

        binding.mainLayout.addView(ll);

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

