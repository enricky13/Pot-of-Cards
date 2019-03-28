package com.example.ygocardsearch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygocardsearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardDescriptionFragment extends Fragment {
    View rootView;


    public CardDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_card_description, container, false);
        return rootView;
    }

}
