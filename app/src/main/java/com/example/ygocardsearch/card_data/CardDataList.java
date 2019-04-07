package com.example.ygocardsearch.card_data;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ygocardsearch.sharedPref.FilterSharedPreference;
import com.example.ygocardsearch.model.CardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDataList {
    private static final String SPELL_CARD = "Spell Card";
    private static final String TRAP_CARD = "Trap Card";
    private static String TAG = "FINDME";
    private static String userInputLowerCase;
    private static List<CardModel> cardModelList;
    private static List<CardModel> filteredList;

    public static void convertToList(@NonNull CardModel[][] networkData) {
        cardModelList = new ArrayList<>();
        cardModelList.addAll(Arrays.asList(networkData[0]));
    }

    public static List<CardModel> getCardModelList() {
        return cardModelList;
    }

    public static List<CardModel> getFilteredList() {
        return filteredList;
    }

    public static void makeFilteredList(SharedPreferences sharedPreferences, String userInput) {
        filteredList = new ArrayList<>();

        boolean isMonster = false;
        boolean isSpell = false;
        boolean isTrap = false;
        String monsterType = null;
        String monsterAttribute = null;
        String spellType = null;
        String trapType = null;

        isMonster = sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY, false);
        isSpell = sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY, false);
        isTrap = sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY, false);
        monsterType = sharedPreferences.getString(FilterSharedPreference.MONSTER_TYPE_KEY, null);
        monsterAttribute = sharedPreferences.getString(FilterSharedPreference.MONSTER_ATTRIBUTE_KEY, null);
        spellType = sharedPreferences.getString(FilterSharedPreference.SPELL_TYPE, null);
        trapType = sharedPreferences.getString(FilterSharedPreference.TRAP_TYPE, null);

        if (userInput != null) {
            userInputLowerCase = userInput.toLowerCase().trim().replaceAll(" +", " ").replaceAll("-", " ");   // Word is minimized to 1 space only but only if there are letters following
        }
        Log.d(TAG, "makeFilteredList: " + userInput);
        Log.d(TAG, "makeFilteredList: " + monsterType);
        Log.d(TAG, "makeFilteredList: " + monsterAttribute);
        Log.d(TAG, "makeFilteredList: " + isMonster);
        Log.d(TAG, "makeFilteredList: " + isSpell);
        Log.d(TAG, "makeFilteredList: " + isTrap);

        for (CardModel card : cardModelList) {
            String cardName = card.getName().toLowerCase().replaceAll("-", " ");
            String cardDesc = card.getDesc().toLowerCase().replaceAll("-", " ");

            if (isMonster) {
                if (card.getAtk() != null) {                 // Verifies if the card is a monster card only
                    if (monsterType == null && monsterAttribute == null) {          // Accounts if both spinners are null
                        if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc)){
                            continue;
                        }
                        continue;
                    }
                    if (monsterType != null && monsterAttribute != null ){
                        if (card.getRace().contains(monsterType) && card.getAttribute().contains(monsterAttribute)){
                            if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc)){
                                continue;
                            }
                        }
                        continue;
                    }
                    if ((monsterType != null && card.getRace().contains(monsterType)) || (monsterAttribute != null && card.getAttribute().contains(monsterAttribute))) {
                        Log.d(TAG, "makeFilteredList: Code shouldn't get to here");
                        if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc)){
                            continue;
                        }
                    }
                }
            }
            if (isSpell) {
                if (card.getType().equals(SPELL_CARD)) {         // Verifies if the card is a spell card only
                    if (spellType == null) {
                        if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc))
                            continue;
                    }
                    if (card.getRace().equals(spellType)) {
                        if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc))
                            continue;
                    }
                }
            }
            if (isTrap) {
                if (card.getType().equals(TRAP_CARD)) {          // Verifies if the card is a trap card only.
                    if (trapType == null) {
                        if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc))
                            continue;
                    }
                    if (card.getRace().equals(trapType)) {
                        if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc)) continue;
                    }
                }
            }
        }
    }

    private static boolean inputNullChecker(String userInput, List<CardModel> filteredList, CardModel card, String cardName, String cardDesc) {
        if (userInput == null){
            filteredList.add(card);
            return true;
        }
        if (cardName.contains(userInputLowerCase) || cardDesc.contains(userInputLowerCase)) {
            filteredList.add(card);
            return true;
        }
        return false;
    }
}
