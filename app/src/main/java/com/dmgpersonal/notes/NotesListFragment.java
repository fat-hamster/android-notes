package com.dmgpersonal.notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {

    public static NotesListFragment newInstance(String param1, String param2) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    private void initView(View view) {
        LinearLayout linearLayout = (LinearLayout) view;
        String[] notesStringArray = getResources().getStringArray(R.array.notes);
        ArrayList<Notes> notes = new ArrayList<>();
        int idx = 0;
        for(String el: notesStringArray) {
            TextView tv = new TextView(getContext());
            notes.add(new Notes(el, el));
            tv.setText(notes.get(idx++).getTitle());
            tv.setTextSize(30);
            linearLayout.addView(tv);
        }
    }
}