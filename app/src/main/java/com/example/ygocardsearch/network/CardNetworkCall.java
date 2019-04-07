package com.example.ygocardsearch.network;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ygocardsearch.MainActivity;
import com.example.ygocardsearch.before_search_fragments.CardSearchFragment;
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

public class CardNetworkCall {
    public static final String TAG = "FINDME";
    private CardNetworkCall(){}

    public static void setupCardDataList(Activity activity) {
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
                        Toast.makeText(activity.getApplicationContext(), "Card Data Download Complete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<CardModel[][]> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
    }

        public static void setupCardDataList(Button button, int textForSuccess){
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
                            Toast.makeText(button.getContext(), "Card Data Download Complete", Toast.LENGTH_SHORT).show();
                            button.setText(textForSuccess);
                        }
                        @Override
                        public void onFailure(Call<CardModel[][]> call, Throwable t) {
                            Log.d(TAG, "onFailure: "+t.getMessage());
                        }
                    });
    }



}
