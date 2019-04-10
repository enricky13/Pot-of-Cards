package com.example.ygocardsearch.before_search_fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygocardsearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutMeFragment extends Fragment {


    public AboutMeFragment() {
        // Required empty public constructor
    }

    public static AboutMeFragment newInstance() {
        AboutMeFragment aboutMeFragment = new AboutMeFragment();
        return aboutMeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_me, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
