package com.example.ygocardsearch.card_data;

import android.util.Log;

import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.network.YgoApiCall;
import com.example.ygocardsearch.network.YgoCardSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardNetworkCallSingleton {
    public static final String TAG = "FINDME";
    private CardNetworkCallSingleton(){}

    public static void setupCardDataList(){
        YgoCardSingleton.getInstance()
                .create(YgoApiCall.class)
                .getCards()
                .enqueue(new Callback<CardModel[][]>() {
                    @Override
                    public void onResponse(Call<CardModel[][]> call, Response<CardModel[][]> response) {
                         CardDataList.convertToList(response.body());
                    }
                    @Override
                    public void onFailure(Call<CardModel[][]> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }
}
