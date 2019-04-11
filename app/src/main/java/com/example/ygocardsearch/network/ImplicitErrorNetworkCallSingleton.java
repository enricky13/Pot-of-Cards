package com.example.ygocardsearch.network;

import android.util.Log;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImplicitErrorNetworkCallSingleton {
    private static OkHttpClient client = new OkHttpClient();
    private static Request request;

    private ImplicitErrorNetworkCallSingleton(){}


    public static void makeCall(String website, View view){

        Log.d("FINDME", "makeCall: Going to make call");

        Request request = new Request.Builder()
                .url(website)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    throw new IOException(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    view.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()){
                    Log.d("FINDME", "onResponse: Message is unssuccessful");
                    view.setVisibility(View.INVISIBLE);
                }
                else {
                    Log.d("FINDME", "onResponse: message is successful");
                }
            }
        });
    }
}
