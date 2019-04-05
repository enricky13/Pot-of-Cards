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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public static List<CardModel> getFilteredList(SharedPreferences sharedPreferences, String userInput){
        List<CardModel> filteredList = new ArrayList<>();

        boolean isMonster = false;
        boolean isSpell = false;
        boolean isTrap = false;
        String monsterType = null;
        String monsterAttribute = null;
        String spellType = null;
        String trapType = null;

        isMonster = sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY,false);
        isSpell = sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY, false);
        isTrap = sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY, false);
        monsterType = sharedPreferences.getString(FilterSharedPreference.MONSTER_TYPE_KEY, null);
        monsterAttribute = sharedPreferences.getString(FilterSharedPreference.MONSTER_ATTRIBUTE_KEY, null);
        spellType = sharedPreferences.getString(FilterSharedPreference.SPELL_TYPE, null);
        trapType = sharedPreferences.getString(FilterSharedPreference.TRAP_TYPE, null);


        String userInputLowerCase = userInput.toLowerCase().trim().replaceAll(" +"," ").replaceAll("-", " ");   // Word is minimized to 1 space only but only if there are letters following
        String TAG = "FINDME";
//        Log.d(TAG, "getFilteredList: "+userInputLowerCase);
//        Log.d(TAG, "getFilteredList: Monster Type Value: "+monsterType);
//        Log.d(TAG, "getFilteredList: monster Position: "+sharedPreferences.getInt(FilterSharedPreference.MONSTER_TYPE_POSITION_KEY, -1));
//        Log.d(TAG, "getFilteredList: Card Model Size"+cardModelList.size());
//        Log.d(TAG, "getFilteredList: Monster Attribute"+monsterAttribute);

        Log.d(TAG, "getFilteredList: Spell Type value: "+spellType);

        for (CardModel card : cardModelList) {
            String cardName = card.getName().toLowerCase().replaceAll("-"," ");
            String cardDesc = card.getDesc().toLowerCase().replaceAll("-"," ");

            if (cardName.contains(userInputLowerCase) || cardDesc.contains(userInputLowerCase)) {
                if (isMonster){
                    if (card.getAtk() != null){
                        if (monsterType == null && monsterAttribute == null){
                            filteredList.add(card);
                            continue;
                        }
                        if ((monsterType != null &&card.getRace().contains(monsterType)) || (monsterAttribute != null &&card.getAttribute().contains(monsterAttribute))){
                            filteredList.add(card);
                            continue;
                        }
                    }
                }
                if (isSpell){
                    if (card.getType().equals(SPELL_CARD)){
                        if (spellType == null){
                            filteredList.add(card);
                            continue;
                        }
                        if (card.getRace().equals(spellType)){
                            filteredList.add(card);
                            continue;
                        }
                    }
                }
                if (isTrap){
                    if (card.getType().equals(TRAP_CARD)){
                        if (trapType == null){
                            filteredList.add(card);
                        }
                        if (card.getRace().equals(trapType)){
                            filteredList.add(card);
                        }
                    }
                }
            }

        }

        Log.d(TAG, "size of filtered List: "+filteredList.size());
        return filteredList;
    }
}
