package com.dmgpersonal.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteBodyFragment extends Fragment {

    public static final String ARG_NOTE = "note";
    private Notes note;

    public NoteBodyFragment() {
        // Required empty public constructor
    }

    public static NoteBodyFragment newInstance(Notes note) {
        NoteBodyFragment fragment = new NoteBodyFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note_body, container, false);
        TextView notebody = view.findViewById(R.id.notebody);
        notebody.setTextSize(24);
        if (note != null) {
            notebody.setText(note.getBody());
        } else {
            notebody.setText(R.string.error);
        }
        return view;
    }
}