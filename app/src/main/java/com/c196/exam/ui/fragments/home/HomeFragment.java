package com.c196.exam.ui.fragments.home;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.c196.exam.R;
import com.c196.exam.database.DatabaseHelper;
import com.c196.exam.databinding.FragmentHomeBinding;
import com.c196.exam.entities.Term;
import com.c196.exam.ui.dialogs.CreateTermDialogFragment;
import com.c196.exam.ui.fragments.term.TermFragment;
import com.c196.exam.ui.widgets.Card;

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

        //This method of creating views is fucking awful do better Android
        LinearLayout ll = new LinearLayout(this.getContext());
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                0.25f);
        llParams.setMargins(0, 50, 0, 50);
        ll.setLayoutParams(llParams);

        int index = 0;
        for(Term t: terms){
            Card card = Card.createCard(this.getContext(), t.getId(), index);
            card.setOnClickListener(v -> {
                CharSequence termId = v.getContentDescription();
                System.out.println(termId);
                TermFragment tf = new TermFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.mainLayout, tf, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            });
            TextView termName = new TextView(this.getContext());
            termName.layout(10,0, 10, 0);
            termName.setText(t.getTitle());
            termName.setGravity(Gravity.CENTER);
            card.addView(termName);
            ll.addView(card);
            index++;
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

