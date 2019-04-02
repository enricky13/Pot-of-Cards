package com.example.ygocardsearch.card_data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ygocardsearch.SharedPref.FilterSharedPreference;
import com.example.ygocardsearch.model.CardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDataList {
    private static final String SPELL_CARD = "Spell Card";
    private static final String TRAP_CARD = "Trap Card";
    private static List<CardModel> cardModelList;

    public static List<CardModel> getCardModelList() {
        return cardModelList;
    }

    public static void convertToList(@NonNull CardModel[][] networkData){
        cardModelList = new ArrayList<>();
        cardModelList.addAll(Arrays.asList(networkData[0]));
    }

    public static List<CardModel> getFilteredList(String userInput, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        List<CardModel> filteredList = new ArrayList<>();

        boolean isMonster = false;
        boolean isSpell = false;
        boolean isTrap = false;

        if (sharedPreferences != null){
            isMonster = sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY,false);
            isSpell = sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY, false);
            isTrap = sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY, false);
        }

        String userInputLowerCase = userInput.toLowerCase().trim().replaceAll(" +"," ").replaceAll("-", " ");   // Word is minimized to 1 space only but only if there are letters following
        Log.d("FINDME", "UserInput size:"+userInputLowerCase.length()+" UserInput word:"+userInputLowerCase);

        for (CardModel card : cardModelList) {
            String cardName = card.getName().toLowerCase().replaceAll("-"," ");
            String cardDesc = card.getDesc().toLowerCase().replaceAll("-"," ");

            if (cardName.contains(userInputLowerCase) || cardDesc.contains(userInputLowerCase)){
                if (isMonster){
                    if (card.getAtk() != null){
                        filteredList.add(card);
                        continue;
                    }
                }
                if (isSpell){
                    if (card.getType().equals(SPELL_CARD)){
                        filteredList.add(card);
                        continue;
                    }
                }
                if (isTrap){
                    if (card.getType().equals(TRAP_CARD)){
                        filteredList.add(card);
                    }
                }
            }
        }
        return filteredList;
    }
}
