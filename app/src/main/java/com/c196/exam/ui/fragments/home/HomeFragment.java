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

import com.c196.exam.MainActivity;
import com.c196.exam.database.DatabaseHelper;
import com.c196.exam.databinding.FragmentHomeBinding;
import com.c196.exam.entities.Term;
import com.c196.exam.ui.widgets.Card;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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
                MainActivity act = (MainActivity) this.getActivity();
                act.displayTerm(t);
            });

            TextView termName = new TextView(this.getContext());
            termName.layout(10,0, 10, 0);
            termName.setText(t.getTitle());
            termName.setGravity(Gravity.CENTER);

            TextView termStart = new TextView(this.getContext());
            termStart.layout(10,0, 10, 0);
            termStart.setText(t.getStart());
            termStart.setGravity(Gravity.START);

            TextView termEnd = new TextView(this.getContext());
            termEnd.layout(10,0, 10, 0);
            termEnd.setText(t.getEnd());
            termEnd.setGravity(Gravity.END);

            card.addView(termName);
            card.addView(termStart);
            card.addView(termEnd);

            ll.addView(card);
            index++;
        }

        binding.mainLayout.addView(ll);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

