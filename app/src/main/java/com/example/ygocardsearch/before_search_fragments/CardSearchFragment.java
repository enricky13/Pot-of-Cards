package com.example.ygocardsearch.before_search_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ygocardsearch.FragmentToFragment;
import com.example.ygocardsearch.R;
import com.example.ygocardsearch.sharedPref.FilterSharedPreference;

public class CardSearchFragment extends Fragment {
    String TAG = "FINDME";
    private EditText searchCardEt;
    private Button goTofilterButton;
    private Button goToCardCollectionButton;
    private FragmentToFragment fragToFragListener;
    View rootView;

    public CardSearchFragment() {
        // Required empty public constructor
    }

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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(UserFilterFragment.SHARED_PREF_KEY,Context.MODE_PRIVATE);
        rootView = inflater.inflate(R.layout.fragment_card_search, container, false);
        goTofilterButton = rootView.findViewById(R.id.go_to_filter_button);
        goToCardCollectionButton = rootView.findViewById(R.id.go_to_card_collection_button);
        searchCardEt = rootView.findViewById(R.id.user_card_search);

        Log.d(TAG, "onCreateView: "+sharedPreferences.getString(FilterSharedPreference.MONSTER_ATTRIBUTE_KEY,null));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goTofilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragToFragListener.goToUserFilter();
            }
        });

        goToCardCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchCardEt.getText().toString().trim().length() < 1){
                    fragToFragListener.goToCardCollectionFragment(null);
                }
                else {
                    String userInput = searchCardEt.getText().toString();
                    fragToFragListener.goToCardCollectionFragment(userInput);
                }
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
