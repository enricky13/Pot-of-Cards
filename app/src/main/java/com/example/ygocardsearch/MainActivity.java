package com.example.ygocardsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ygocardsearch.card_data.CardNetworkCallSingleton;
import com.example.ygocardsearch.search_result_fragments.CardCollectionFragment;
import com.example.ygocardsearch.search_result_fragments.MonsterCardFragment;
import com.example.ygocardsearch.before_search_fragments.CardSearchFragment;
import com.example.ygocardsearch.before_search_fragments.UserChoosesFragment;
import com.example.ygocardsearch.model.CardModel;

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
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToCardCollectionFragment() {
        CardCollectionFragment cardCollectionFragment = CardCollectionFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container,cardCollectionFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotToCorrectCardFragment(CardModel cardModel) {
        MonsterCardFragment monsterCardFragment = MonsterCardFragment.newInstance(cardModel);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, monsterCardFragment)
                .addToBackStack(null)
                .commit();
    }
}
