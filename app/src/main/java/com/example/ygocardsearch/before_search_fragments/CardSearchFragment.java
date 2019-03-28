package com.example.ygocardsearch.before_search_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ygocardsearch.FragmentToFragment;
import com.example.ygocardsearch.R;

public class CardSearchFragment extends Fragment {
    private Button goTofilterButton;
    private Button goToCardCollectionButton;
    private FragmentToFragment fragToFragListener;
    View rootView;

    public CardSearchFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CardSearchFragment newInstance() {
        CardSearchFragment fragment = new CardSearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_card_search, container, false);
        goTofilterButton = rootView.findViewById(R.id.go_to_filter_button);
        goToCardCollectionButton = rootView.findViewById(R.id.go_to_card_collection_button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goToCardCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FINDME", "onClick: ");
                fragToFragListener.goToCardCollectionFragment();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragToFragListener = (FragmentToFragment) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragToFragListener = null;
    }
}
