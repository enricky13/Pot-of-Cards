package com.example.ygocardsearch.search_result_fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ygocardsearch.FragmentToFragment;
import com.example.ygocardsearch.R;
import com.example.ygocardsearch.model.CardModel;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpellTrapCardFragment extends Fragment {
    public static final String CARDMODEL = "CARDMODEL";
    private TextView cardName,cardDesc;
    private Button goToCardRulingsButton;
    private ImageView cardImg;
    private CardModel cardModel;
    private FragmentToFragment fragmentToFragment;
    View rootView;


    public SpellTrapCardFragment() {
        // Required empty public constructor
    }

    public static SpellTrapCardFragment newInstance(CardModel cardModel){
        SpellTrapCardFragment spellTrapCardFragment = new SpellTrapCardFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CARDMODEL,cardModel);
        spellTrapCardFragment.setArguments(bundle);
        return spellTrapCardFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_spell_trap_card, container, false);
        cardName = rootView.findViewById(R.id.card_name_spell_trap);
        cardDesc = rootView.findViewById(R.id.card_desc_spell_trap);
        cardImg = rootView.findViewById(R.id.card_image_spell_trap);
        goToCardRulingsButton = rootView.findViewById(R.id.go_to_card_rulings_spell_trap_button);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cardName.setText(cardModel.getName());
        cardDesc.setText(cardModel.getDesc());
        Picasso.get()
                .load(cardModel.getImage_url())
                .into(cardImg);
        goToCardRulingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentToFragment.goToCardRulings(cardModel.getName());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentToFragment = (FragmentToFragment) context;
        if (getArguments() != null){
            cardModel = (CardModel) getArguments().getSerializable(CARDMODEL);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cardModel = null;
        fragmentToFragment = null;
    }
}
