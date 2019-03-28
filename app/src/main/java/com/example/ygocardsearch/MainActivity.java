package com.example.ygocardsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ygocardsearch.card_data.CardNetworkCallSingleton;
import com.example.ygocardsearch.search_result_fragments.CardCollectionFragment;
import com.example.ygocardsearch.search_result_fragments.MonsterCardFragment;
import com.example.ygocardsearch.before_search_fragments.CardSearchFragment;
import com.example.ygocardsearch.before_search_fragments.UserChoosesFragment;
import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.search_result_fragments.SpellTrapCardFragment;

public class MainActivity extends AppCompatActivity implements FragmentToFragment {

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
        if (cardModel.getAtk() != null){
            MonsterCardFragment monsterCardFragment = MonsterCardFragment.newInstance(cardModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, monsterCardFragment)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            SpellTrapCardFragment spellTrapCardFragment = SpellTrapCardFragment.newInstance(cardModel);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container,spellTrapCardFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }
}
