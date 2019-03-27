package com.example.ygocardsearch.card_data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.ygocardsearch.model.CardModel;

import java.util.ArrayList;
import java.util.List;

public class CardDataList {

    public static void convertToList(@NonNull CardModel[][] networkData){
        List<CardModel> cardModelList = new ArrayList<>();
        for (int i = 0; i < networkData[0].length; i++) {
            cardModelList.add(networkData[0][i]);
        }

        Log.d("FINDME", "convertToList: "+cardModelList.size());
        Log.d("FINDME", "convertToList: "+cardModelList.get(0).getDesc());
    }
}
