package com.example.ygocardsearch.network;

import android.util.Log;

import com.example.ygocardsearch.card_data.CardDataList;
import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.network.YgoApiCall;
import com.example.ygocardsearch.network.YgoCardSingleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(onComplete -> CardDataList.convertToList(onComplete)
//                        , onError -> Log.d(TAG, "On Error: "+onError.getMessage()));
                .enqueue(new Callback<CardModel[][]>() {
                    @Override
                    public void onResponse(Call<CardModel[][]> call, Response<CardModel[][]> response) {
                         CardDataList.convertToList(response.body());
                        Log.d(TAG, "onResponse: Data is saved"+response.body()[0][0].getName());
                    }
                    @Override
                    public void onFailure(Call<CardModel[][]> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }
}
