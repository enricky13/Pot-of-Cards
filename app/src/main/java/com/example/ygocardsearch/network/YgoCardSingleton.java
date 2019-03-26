package com.example.ygocardsearch.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YgoCardSingleton {
    private static Retrofit instance;
    private YgoCardSingleton(){}

    public static Retrofit getInstance() {
        if (instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl("https://db.ygoprodeck.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
