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
}
