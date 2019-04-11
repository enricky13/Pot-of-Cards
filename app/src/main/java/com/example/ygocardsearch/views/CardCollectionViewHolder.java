package com.example.ygocardsearch.views;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.model.CardModel;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class CardCollectionViewHolder extends RecyclerView.ViewHolder {
    private static final String SPELL_CARD = "Spell Card";
    private static final String NORMAL_MONSTER = "Normal Monster";
    private static final String TRAP_CARD = "Trap Card";
    private static final String RITUAL_MONSTER = "Ritual Monster";
    private static final String RITUAL_EFFECT_MONSTER = "Ritual Effect Monster";
    private static final String SYNCHRO_MONSTER = "Synchro Monster";
    private static final String SYNCHRO_TUNER_MONSTER = "Synchro Tuner Monster";
    private static final String LINK_MONSTER = "Link Monster";
    private static final String FUSION_MONSTER = "Fusion Monster";
    private static final String XYZ_MONSTER = "XYZ Monster";
    private static final String[] PENDULUM_MONSTER = {"Pendulum Normal Monster", "Pendulum Effect Monster", "Pendulum Effect Fusion Monster", "Pendulum Flip Effect Monster", "XYZ Pendulum Effect Monster"};
    private static String[] EFFECT_MONSTERS = {"Flip Effect Monster","Effect Monster","Tuner Monster","Gemini Monster","Spirit Monster","Toon Monster","Union Effect Monster","Union Tuner Effect Monster"};
    private TextView cardNameTv;
    private ImageView cardImageIv;
    private FragmentBackgroundWork fragmentBackgroundWork;

    public CardCollectionViewHolder(@NonNull View itemView, FragmentBackgroundWork fragmentBackgroundWork) {
        super(itemView);
        cardNameTv = itemView.findViewById(R.id.card_name);
        cardImageIv = itemView.findViewById(R.id.card_image);
        this.fragmentBackgroundWork = fragmentBackgroundWork;
    }

    public void onBind(final CardModel cardModel){
        cardNameTv.setText(cardModel.getName());
        setBackgroundToCorrectColor(cardModel);

        Picasso.get()
                .load(cardModel.getImage_url_small())
                .placeholder(R.drawable.yugioh_card_small)
                .into(cardImageIv);

        itemView.setOnClickListener(v -> fragmentBackgroundWork.gotToCorrectCardFragment(cardModel));
    }

    private void setBackgroundToCorrectColor(CardModel cardModel) {
        if (cardModel.getType().equals(NORMAL_MONSTER)){
            setBackgroundColorBasedOnCard(R.color.normalMonster);
            cardNameTv.setTextColor(itemView.getResources().getColor(R.color.xyzMonster));
        }
        if (Arrays.asList(EFFECT_MONSTERS).contains(cardModel.getType())){
            setBackgroundColorBasedOnCard(R.color.effectMonster);
        }
        if (cardModel.getType().equals(RITUAL_MONSTER) || cardModel.getType().equals(RITUAL_EFFECT_MONSTER)){
            setBackgroundColorBasedOnCard(R.color.ritualMonster);
        }
        if (cardModel.getType().equals(SYNCHRO_MONSTER) || cardModel.getType().equals(SYNCHRO_TUNER_MONSTER)){
            setBackgroundColorBasedOnCard(R.color.synchroMonster);
            cardNameTv.setTextColor(itemView.getResources().getColor(R.color.xyzMonster));
        }
        if (cardModel.getType().equals(LINK_MONSTER)){
            setBackgroundColorBasedOnCard(R.color.linkMonster);
        }
        if (cardModel.getType().equals(FUSION_MONSTER)){
            setBackgroundColorBasedOnCard(R.color.fusionMonster);
        }
        if (cardModel.getType().equals(XYZ_MONSTER)){
            setBackgroundColorBasedOnCard(R.color.xyzMonster);
        }
        if (cardModel.getType().equals(SPELL_CARD)){
            setBackgroundColorBasedOnCard(R.color.spellCard);
        }
        if (cardModel.getType().equals(TRAP_CARD)){
            setBackgroundColorBasedOnCard(R.color.trapCard);
        }
        if (Arrays.asList(PENDULUM_MONSTER).contains(cardModel.getType())){
            itemView.setBackground(ContextCompat.getDrawable(itemView.getContext(),R.drawable.pendulum_background_color));
        }
    }

    private void setBackgroundColorBasedOnCard(int color) {
        itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), color));
    }
}
