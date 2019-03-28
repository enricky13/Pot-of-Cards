package com.example.ygocardsearch.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.adapter.CardCollectionAdapter;
import com.example.ygocardsearch.card_data.CardDataList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardCollectionFragment extends Fragment {
    private RecyclerView cardCollectionRecyclerView;
    View rootView;


    public CardCollectionFragment() {
        // Required empty public constructor
    }

    public static CardCollectionFragment newInstance(){
        CardCollectionFragment cardCollectionFragment = new CardCollectionFragment();
        return cardCollectionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_card_collection, container, false);
        cardCollectionRecyclerView = rootView.findViewById(R.id.card_recyclerview);
        cardCollectionRecyclerView.setAdapter(new CardCollectionAdapter(CardDataList.getCardModelList()));
        cardCollectionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        return rootView;
    }

}
