package com.example.ygocardsearch.search_result_fragments;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.R;
import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.network.ImplicitErrorNetworkCallSingleton;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonsterCardFragment extends Fragment {
    public static final String CARD_MODEL = "CardModel";
    private String website = "https://yugioh.fandom.com/wiki/Card_Rulings:";
    private TextView cardName, cardLevel, cardType, cardAttribute, cardAtk, cardDef, cardDesc;
    private Button cardRulingsButton;
    private ImageView cardImg;
    private CardModel cardModel;
    private FragmentBackgroundWork fragmentBackgroundWork;
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
        cardType = rootView.findViewById(R.id.monster_type_card_description);
        cardAttribute = rootView.findViewById(R.id.monster_attribute_card_description);
        cardAtk = rootView.findViewById(R.id.card_atk_card_description);
        cardDef = rootView.findViewById(R.id.card_def_card_description);
        cardDesc = rootView.findViewById(R.id.card_desc_card_description);
        cardImg = rootView.findViewById(R.id.card_image_card_description);
        cardRulingsButton = rootView.findViewById(R.id.go_to_card_rulings_button);
        //Checks if the network call delivers a 404 error, if it does it hides the button
        Log.d("FINDME", "onCreateView: Make Call now!!");
        ImplicitErrorNetworkCallSingleton.makeCall(website+cardModel.getName(), cardRulingsButton);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String attributeText = getStringForText(R.string.attribute_monster, cardModel.getAttribute());
        String typeText = getStringForText(R.string.type_monster, cardModel.getRace());
        String atkText = getStringForText(R.string.attack_monster, cardModel.getAtk());
        String defText = getStringForText(R.string.defense_monster, cardModel.getDef());
        String levelText = getStringForText(R.string.monster_level_rank, cardModel.getLevel());


        cardName.setText(cardModel.getName());
        cardLevel.setText(levelText);
        cardAttribute.setText(attributeText);
        cardType.setText(typeText);
        cardAtk.setText(atkText);
        cardDef.setText(defText);
        cardDesc.setText(cardModel.getDesc());
        Picasso.get()
                .load(cardModel.getImage_url())
                .placeholder(R.drawable.yugioh_card_back)
                .into(cardImg);
        cardRulingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentBackgroundWork.goToCardRulings(cardModel.getName());
            }
        });
    }

    private String getStringForText(int stringId, String placeHolder) {
        return getResources().getString(stringId, placeHolder);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentBackgroundWork = (FragmentBackgroundWork) context;
        if (cardModel == null){
            cardModel = (CardModel) getArguments().getSerializable(CARD_MODEL);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentBackgroundWork = null;
    }
}
