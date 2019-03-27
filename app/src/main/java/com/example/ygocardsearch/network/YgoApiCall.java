package com.example.ygocardsearch.network;

import com.example.ygocardsearch.model.CardModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface YgoApiCall {
    @GET("/api/v4/cardinfo.php")
    Call <CardModel[][]> getCards();
}
