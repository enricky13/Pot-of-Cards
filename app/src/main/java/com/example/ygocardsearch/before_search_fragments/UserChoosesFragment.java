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

import com.example.ygocardsearch.FragmentToFragment;
import com.example.ygocardsearch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserChoosesFragment extends Fragment {
    private Button goToLifePoint;
    private Button goToCardSearch;
    private FragmentToFragment fragtofraglistener;
    View rootView;


    public UserChoosesFragment() {
        // Required empty public constructor
    }

    public static UserChoosesFragment newInstance(){
        UserChoosesFragment userChooses = new UserChoosesFragment();
        return userChooses;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_chooses, container, false);
        goToLifePoint = rootView.findViewById(R.id.go_to_life_point_button);
        goToCardSearch = rootView.findViewById(R.id.go_to_card_search_button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        goToCardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragtofraglistener.goToCardSearchFragment();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragtofraglistener = (FragmentToFragment) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragtofraglistener = null;
    }
}
