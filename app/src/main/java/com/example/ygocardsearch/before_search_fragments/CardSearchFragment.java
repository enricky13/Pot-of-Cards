package com.example.ygocardsearch.before_search_fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.R;
import com.example.ygocardsearch.card_data.CardDataList;
import com.example.ygocardsearch.sharedPref.FilterSharedPreference;

public class CardSearchFragment extends Fragment {
    private String TAG = "FINDME";
    private EditText searchCardEt;
    private Button goTofilterButton;
    private Button goToCardCollectionButton;
    private BottomNavigationView goToBioBnv;
    private FragmentBackgroundWork fragToFragListener;
    SharedPreferences sharedPreferences;
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
        sharedPreferences = getContext().getSharedPreferences(UserFilterFragment.SHARED_PREF_KEY,Context.MODE_PRIVATE);
        rootView = inflater.inflate(R.layout.fragment_card_search, container, false);
        goTofilterButton = rootView.findViewById(R.id.go_to_filter_button);
        goToCardCollectionButton = rootView.findViewById(R.id.go_to_card_collection_button);
        searchCardEt = rootView.findViewById(R.id.user_card_search);
        goToBioBnv = rootView.findViewById(R.id.navigation_menu_bio);

        if ((sharedPreferences == null) || !sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY,false) && !sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY, false) && !sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY,false)){
                    sharedPreferences.edit()
                    .putBoolean(FilterSharedPreference.MONSTER_CARD_KEY,true)
                    .putBoolean(FilterSharedPreference.SPELL_CARD_KEY,true)
                    .putBoolean(FilterSharedPreference.TRAP_CARD_KEY,true)
                    .apply();
        }

        Log.d(TAG, "onCreateView: "+sharedPreferences.getString(FilterSharedPreference.MONSTER_ATTRIBUTE_KEY,null));

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (CardDataList.getCardModelList() == null){
            goToCardCollectionButton.setText(getString(R.string.re_download));
        }

        goTofilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    fragToFragListener.goToUserFilter();

            }
        });

        goToCardCollectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CardDataList.getCardModelList() != null) {

                    if (searchCardEt.getText().toString().trim().length() < 1) {
                        CardDataList.makeFilteredList(sharedPreferences, null);
                        fragToFragListener.goToCardCollectionFragment(null);
                    } else {
                        CardDataList.makeFilteredList(sharedPreferences, searchCardEt.getText().toString());
                        String userInput = searchCardEt.getText().toString();
                        fragToFragListener.goToCardCollectionFragment(userInput);
                    }
                }
                else {
                    fragToFragListener.restartCardDownload(goToCardCollectionButton, R.string.start_search_text);
                }
            }
        });

        goToBioBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                fragToFragListener.goToBioFragment();
                return true;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragToFragListener = (FragmentBackgroundWork) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragToFragListener = null;
    }
}
