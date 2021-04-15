package com.dmgpersonal.notes;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import java.util.zip.Inflater;

public class NoteBodyActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_edit).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_body);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
        }

        if (savedInstanceState == null) {
            NoteBodyFragment noteBodyFragment = new NoteBodyFragment();
            noteBodyFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_layout, noteBodyFragment)
                    .commit();
        }


    }
}