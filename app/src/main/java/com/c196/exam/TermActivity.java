package com.c196.exam;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.c196.exam.ui.fragments.term.TermFragment;

public class TermActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_term, TermFragment.newInstance()).commitNow();
        }

        Toolbar t = findViewById(R.id.toolbar);

        Intent i = getIntent();
        int it = i.getIntExtra("TERM_ID", -1);

        t.setTitle("Term: " + it);
    }
}