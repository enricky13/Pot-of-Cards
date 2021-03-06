package com.example.ygocardsearch.search_result_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.adapter.CardCollectionAdapter;
import com.example.ygocardsearch.card_data.CardDataList;
import com.example.ygocardsearch.FragmentBackgroundWork;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardCollectionFragment extends Fragment {
    public static final String USERINPUT_KEY = "USERINPUT";
    private FragmentBackgroundWork fragmentBackgroundWorkListener;
    private ImageView imageView;
    private String userInput;
    View rootView;


    public CardCollectionFragment() {
        // Required empty public constructor
    }

    public static CardCollectionFragment newInstance(String userInput){
        CardCollectionFragment cardCollectionFragment = new CardCollectionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERINPUT_KEY,userInput);
        cardCollectionFragment.setArguments(bundle);
        return cardCollectionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_card_collection, container, false);

        RecyclerView cardCollectionRecyclerView = rootView.findViewById(R.id.card_recyclerview);
        cardCollectionRecyclerView.setAdapter(new CardCollectionAdapter(CardDataList.getFilteredList(), fragmentBackgroundWorkListener));
        cardCollectionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentBackgroundWorkListener = (FragmentBackgroundWork) context;
            userInput = getArguments().getString(USERINPUT_KEY);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentBackgroundWorkListener = null;
        userInput = null;
    }
}
