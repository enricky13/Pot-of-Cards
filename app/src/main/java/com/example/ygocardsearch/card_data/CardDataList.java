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
        boolean isAtkChecked = false;
        int minValue = -1;
        int maxValue = -1;
        String monsterType = null;
        String monsterAttribute = null;
        String spellType = null;
        String trapType = null;


        isMonster = sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY, false);
        isSpell = sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY, false);
        isTrap = sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY, false);
        isAtkChecked = sharedPreferences.getBoolean(FilterSharedPreference.CHECK_FOR_ATTACK, false);
        minValue = sharedPreferences.getInt(FilterSharedPreference.ATTACK_MIN_VALUE_KEY, -1);
        maxValue = sharedPreferences.getInt(FilterSharedPreference.ATTACK_MAX_VALUE_KEY, -1);
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
            int cardAtkValue = 0;
            if (card.getAtk() != null){
                cardAtkValue = Integer.parseInt(card.getAtk());
            }


            if (monsterFilter(userInput, isMonster, isAtkChecked, minValue, maxValue, monsterType, monsterAttribute, card, cardName, cardDesc, cardAtkValue))
                continue;
            if (spellFilter(userInput, isSpell, spellType, card, cardName, cardDesc))
                continue;
            trapFilter(userInput, isTrap, trapType, card, cardName, cardDesc);
        }
    }

    private static void trapFilter(String userInput, boolean isTrap, String trapType, CardModel card, String cardName, String cardDesc) {
        if (isTrap) {
            if (card.getType().equals(TRAP_CARD)) {          // Verifies if the card is a trap card only.
                if (trapType == null) {
                    if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc))
                        return;
                }
                if (card.getRace().equals(trapType)) {
                    if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc)) return;
                }
            }
        }
    }

    private static boolean spellFilter(String userInput, boolean isSpell, String spellType, CardModel card, String cardName, String cardDesc) {
        if (isSpell) {
            if (card.getType().equals(SPELL_CARD)) {         // Verifies if the card is a spell card only
                if (spellType == null) {
                    if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc))
                        return true;
                }
                if (card.getRace().equals(spellType)) {
                    if (inputNullChecker(userInput, filteredList, card, cardName, cardDesc))
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean monsterFilter(String userInput, boolean isMonster, boolean isAtkChecked, int minValue, int maxValue, String monsterType, String monsterAttribute, CardModel card, String cardName, String cardDesc, int cardAtkValue) {
        if (isMonster) {
            if (card.getAtk() != null) {                 // Verifies if the card is a monster card only
                if (monsterType == null && monsterAttribute == null) {          // Accounts if both spinners are null
                    if (isAtkChecked){
                        if (minValue <= cardAtkValue && maxValue >= cardAtkValue){
                            if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc))
                                return true;
                        }
                    }
                    else {
                        if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc))
                            return true;
                    }
                }
                if (monsterType != null && monsterAttribute != null){
                    if (card.getRace().contains(monsterType) && card.getAttribute().contains(monsterAttribute)){
                        if (isAtkChecked){
                            if (minValue <= cardAtkValue && maxValue >= cardAtkValue){
                                if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc))
                                    return true;
                            }
                        }
                        else {
                            if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc))
                                return true;
                        }
                    }
                    return true;
                }
                if ((monsterType != null && card.getRace().contains(monsterType)) || (monsterAttribute != null && card.getAttribute().contains(monsterAttribute))) {
                    if (isAtkChecked){
                        if (minValue <= cardAtkValue && maxValue >= cardAtkValue){
                            if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc))
                                return true;
                        }
                    }
                    else {
                        if (inputNullChecker(userInput,filteredList,card,cardName,cardDesc))
                            return true;
                    }
                }
            }
        }
        return false;
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
