package com.example.ygocardsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ygocardsearch.card_data.CardNetworkCallSingleton;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "FINDME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardNetworkCallSingleton.setupCardDataList();
        
    }
}
