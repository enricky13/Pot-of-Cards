package com.example.ygocardsearch.before_search_fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygocardsearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFilterFragment extends Fragment {


    public UserFilterFragment() {
        // Required empty public constructor
    }

    public static UserFilterFragment newInstance() {
        UserFilterFragment userFilterFragment = new UserFilterFragment();
        return userFilterFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_filter, container, false);
    }

}
