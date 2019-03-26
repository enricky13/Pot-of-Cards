package com.example.ygocardsearch.network;

import com.example.ygocardsearch.model.CardsList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YgoApiCall {
    @GET("/api/v4/cardinfo.php")
    Call <CardsList[][]> getCards();
}
