package com.example.ygocardsearch.search_result_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.model.CardModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonsterCardFragment extends Fragment {
    public static final String CARD_MODEL = "CardModel";
    private TextView cardName, cardLevel, cardAtk, cardDef, cardDesc;
    private ImageView cardImg;
    private CardModel cardModel;
    View rootView;


    public MonsterCardFragment() {
        // Required empty public constructor
    }

    public static MonsterCardFragment newInstance(CardModel cardModel){
        MonsterCardFragment monsterCardFragment = new MonsterCardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CARD_MODEL,cardModel);
        monsterCardFragment.setArguments(bundle);
        return monsterCardFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_card_monster, container, false);
        cardName = rootView.findViewById(R.id.card_name_card_description);
        cardLevel = rootView.findViewById(R.id.card_level_card_description);
        cardAtk = rootView.findViewById(R.id.card_atk_card_description);
        cardDef = rootView.findViewById(R.id.card_def_card_description);
        cardDesc = rootView.findViewById(R.id.card_desc_card_description);
        cardImg = rootView.findViewById(R.id.card_image_card_description);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardName.setText(cardModel.getName());
        cardLevel.setText(cardModel.getLevel());
        cardAtk.setText(cardModel.getAtk());
        cardDef.setText(cardModel.getDef());
        cardDesc.setText(cardModel.getDesc());
        Picasso.get()
                .load(cardModel.getImage_url())
                .into(cardImg);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (cardModel == null){
            cardModel = (CardModel) getArguments().getSerializable(CARD_MODEL);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
