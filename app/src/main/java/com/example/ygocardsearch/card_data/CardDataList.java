package com.example.ygocardsearch.card_data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ygocardsearch.model.CardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDataList {
    private static List<CardModel> cardModelList;

    public static List<CardModel> getCardModelList() {
        return cardModelList;
    }

    public static void convertToList(@NonNull CardModel[][] networkData){
        cardModelList = new ArrayList<>();
        cardModelList.addAll(Arrays.asList(networkData[0]));
    }

    public static List<CardModel> getFilteredList(String userInput){
        List<CardModel> filteredList = new ArrayList<>();
        String userInputLowerCase = userInput.toLowerCase().trim().replaceAll(" +"," ");   // Word is minimized to 1 space only but only if there are letters following
        Log.d("FINDME", "UserInput size:"+userInputLowerCase.length()+" UserInput word:"+userInputLowerCase);

        for (CardModel card : cardModelList) {
            String cardName = card.getName().toLowerCase().replaceAll("-+"," ");
            String cardDesc = card.getDesc().toLowerCase();

            if (cardName.contains(userInputLowerCase) || cardDesc.contains(userInputLowerCase)){
                filteredList.add(card);
            }
        }
        return filteredList;
    }
}
