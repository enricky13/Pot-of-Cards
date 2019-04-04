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
        Log.d("FINDME", "getFilteredList: Monster Type Value: "+monsterType);
        Log.d("FINDME", "getFilteredList: monster Position: "+sharedPreferences.getInt(FilterSharedPreference.MONSTER_TYPE_POSITION_KEY, -1));

        for (CardModel card : cardModelList) {
            String cardName = card.getName().toLowerCase().replaceAll("-"," ");
            String cardDesc = card.getDesc().toLowerCase().replaceAll("-"," ");
            String cardRace = card.getRace();
            String cardAttribute = card.getAttribute();

            if (cardName.contains(userInputLowerCase) || cardDesc.contains(userInputLowerCase)){
                if (isMonster){
                    if (card.getAtk() == null){
                        continue;
                    }
                    if (monsterType != null && !(cardRace.equals(monsterType))){
                        continue;
                    }
                    if (monsterAttribute != null && !(cardAttribute.equals(monsterAttribute))){
                        continue;
                    }
                    filteredList.add(card);
                }
                if (isSpell){
                    if (card.getType().equals(TRAP_CARD) || card.getAtk() != null){
                        continue;
                    }
                    if (spellType != null && !(card.getType().equals(spellType))){
                        continue;
                    }
                    filteredList.add(card);
                }
                if (isTrap){
                    if (card.getType().equals(SPELL_CARD) || card.getAtk() != null){
                        continue;
                    }
                    filteredList.add(card);
                }
            }
        }

        Log.d("FINDME", "getFilteredList: "+monsterType);
        for (CardModel cardModel: filteredList){
            if (monsterType != null){               // Checks if the user wants to look for a monster type
                if (cardModel.getAtk() != null){            // Checks if the card is a monster only
                    if (!(cardModel.getRace().equals(monsterType))){
                        filteredList.remove(cardModel);
                    }
                }
            }
        }

        Log.d("FINDME", "getFilteredList: "+filteredList.size());
        return filteredList;
    }
}
