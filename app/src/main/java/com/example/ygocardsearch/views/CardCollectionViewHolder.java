package com.example.ygocardsearch.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.FragmentToFragment;
import com.example.ygocardsearch.model.CardModel;
import com.squareup.picasso.Picasso;

public class CardCollectionViewHolder extends RecyclerView.ViewHolder {
    private TextView cardNameTv;
    private ImageView cardImageIv;
    private FragmentToFragment fragmentToFragment;

    public CardCollectionViewHolder(@NonNull View itemView, FragmentToFragment fragmentToFragment) {
        super(itemView);
        cardNameTv = itemView.findViewById(R.id.card_name);
        cardImageIv = itemView.findViewById(R.id.card_image);
        this.fragmentToFragment = fragmentToFragment;
    }

    public void onBind(final CardModel cardModel){
        cardNameTv.setText(cardModel.getName());
        Picasso.get()
                .load(cardModel.getImage_url_small())
                .into(cardImageIv);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentToFragment.gotToCorrectCardFragment(cardModel);
            }
        });
    }
}
