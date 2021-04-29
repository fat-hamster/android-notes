package com.dmgpersonal.notes;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private Notes currentNote = null;

    private boolean isLandscape;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if(savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        }

        if (isLandscape) {
            showLandNotesBody(currentNote);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(CURRENT_NOTE, currentNote);
        super.onSaveInstanceState(outState);
    }

    private void showNotesBody(Notes note) {
        if (isLandscape) {
            showLandNotesBody(note);
        } else {
            showPortNotesBody(note);
        }
    }

    private void showLandNotesBody(Notes note) {
        if (note == null) {
            return;
        }
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notebody_land, NoteBodyFragment.newInstance(note))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private void showPortNotesBody(Notes note) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteBodyActivity.class);
        intent.putExtra(NoteBodyFragment.ARG_NOTE, note);
        startActivity(intent);
    }

    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] notesStringArray = getResources().getStringArray(R.array.notes);
        ArrayList<Notes> notes = new ArrayList<>();
        int idx = 0;
        for (String el : notesStringArray) {
            TextView tv = new TextView(getContext());
            notes.add(new Notes(el, el));
            tv.setText(notes.get(idx).getTitle());
            tv.setTextSize(30);
            linearLayout.addView(tv);

            final int finalIdx = idx++;
            tv.setOnClickListener(v -> {
                currentNote = notes.get(finalIdx);
                showNotesBody(currentNote);
            });
        }
    }
}