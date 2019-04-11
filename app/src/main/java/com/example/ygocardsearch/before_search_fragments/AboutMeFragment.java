package com.example.ygocardsearch.before_search_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutMeFragment extends Fragment {
    private FragmentBackgroundWork fragmentBackgroundWork;
    private Button goToLinkedIn;
    private Button goToGithub;
    private View rootView;

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
        rootView = inflater.inflate(R.layout.fragment_about_me, container, false);
        goToLinkedIn = rootView.findViewById(R.id.go_to_linkedIn);
        goToGithub = rootView.findViewById(R.id.go_to_github);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goToLinkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentBackgroundWork.goToLinkedIn();
            }
        });

        goToGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentBackgroundWork.goToGithub();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentBackgroundWork = (FragmentBackgroundWork) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentBackgroundWork = null;
    }
}
