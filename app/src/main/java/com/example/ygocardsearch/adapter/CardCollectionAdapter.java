package com.example.ygocardsearch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.views.CardCollectionViewHolder;

import java.util.List;

public class CardCollectionAdapter extends RecyclerView.Adapter<CardCollectionViewHolder> {
    private FragmentBackgroundWork fragmentBackgroundWork;
    List<CardModel> cardModelList;

    public CardCollectionAdapter(List<CardModel> cardModelList, FragmentBackgroundWork fragmentBackgroundWork) {
        this.cardModelList = cardModelList;
        this.fragmentBackgroundWork = fragmentBackgroundWork;
    }

    @NonNull
    @Override
    public CardCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_collection_itemview,viewGroup,false);
        return new CardCollectionViewHolder(view, fragmentBackgroundWork);
    }

    @Override
    public void onBindViewHolder(@NonNull CardCollectionViewHolder cardCollectionViewHolder, int i) {
        cardCollectionViewHolder.onBind(cardModelList.get(i));
    }

    @Override
    public int getItemCount() {
        return cardModelList.size();
    }

}
