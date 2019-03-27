package com.example.ygocardsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ygocardsearch.card_data.CardNetworkCallSingleton;
import com.example.ygocardsearch.fragments.CardSearchFragment;
import com.example.ygocardsearch.fragments.FragmentToFragment;
import com.example.ygocardsearch.fragments.UserChoosesFragment;

public class MainActivity extends AppCompatActivity implements FragmentToFragment {
    public static final String TAG = "FINDME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardNetworkCallSingleton.setupCardDataList();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, UserChoosesFragment.newInstance())
                .commit();
    }

    @Override
    public void goToCardSearchFragment() {
        CardSearchFragment cardSearchFragment = CardSearchFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container,cardSearchFragment)
                .commit();
    }
}
