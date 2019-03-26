package com.example.ygocardsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ygocardsearch.model.CardsList;
import com.example.ygocardsearch.network.YgoApiCall;
import com.example.ygocardsearch.network.YgoCardSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FINDME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        YgoCardSingleton.getInstance()
                .create(YgoApiCall.class)
                .getCards()
                .enqueue(new Callback<CardsList[][]>() {
                    @Override
                    public void onResponse(Call<CardsList[][]> call, Response<CardsList[][]> response) {
                        CardsList[][] cardsLists = response.body();
                        Log.d(TAG, "onResponse: "+cardsLists[0].length);
                        Log.d(TAG, "onResponse: "+response.body()[0][0].getAtk());
                    }

                    @Override
                    public void onFailure(Call<CardsList[][]> call, Throwable t) {
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });
    }
}
