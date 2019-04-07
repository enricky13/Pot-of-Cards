package com.example.ygocardsearch.network;

import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImplicitErrorNetworkCallSingleton {
    private static OkHttpClient client = new OkHttpClient();

    private ImplicitErrorNetworkCallSingleton(){}

    public static void getInstance(String website, View view){

        Request request = new Request.Builder()
                .url(website)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("FINDME", "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    view.setVisibility(View.INVISIBLE);
                }
                else {
                    Log.d("FINDME", "onResponse: message is successful");
                }
            }
        });
    }
}
