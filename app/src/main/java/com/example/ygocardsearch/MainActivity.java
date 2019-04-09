package com.example.ygocardsearch;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ygocardsearch.before_search_fragments.UserFilterFragment;
import com.example.ygocardsearch.card_data.CardDataList;
import com.example.ygocardsearch.network.CardNetworkCall;
import com.example.ygocardsearch.search_result_fragments.CardCollectionFragment;
import com.example.ygocardsearch.search_result_fragments.MonsterCardFragment;
import com.example.ygocardsearch.before_search_fragments.CardSearchFragment;
import com.example.ygocardsearch.before_search_fragments.UserChoosesFragment;
import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.search_result_fragments.NoSearchResultFragment;
import com.example.ygocardsearch.search_result_fragments.SpellTrapCardFragment;
import com.example.ygocardsearch.splashFragment.SplashPage;

public class MainActivity extends AppCompatActivity implements FragmentBackgroundWork {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isInternetOn()){
            Toast.makeText(this, "Provide Internet Connection for best experience", Toast.LENGTH_SHORT).show();
        }
        CardNetworkCall.setupCardDataList(this);
        inflateFragment(SplashPage.newInstance());
    }

    private void inflateFragment(Fragment fragment) {
        inflateFragment(fragment, false);
    }

    private void inflateFragment(Fragment fragment, boolean addToBack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        if (addToBack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_fragment_container, fragment)
                .commit();
    }

    @Override
    public void goToCardSearchFragment() {
        inflateFragment(CardSearchFragment.newInstance());
    }

    @Override
    public void goToCardCollectionFragment(String userInput) {
        if (CardDataList.getFilteredList().size() == 0) {
            Log.d("FINDME", "goToCardCollectionFragment: No Search Results");
            NoSearchResultFragment noSearchResultFragment = NoSearchResultFragment.newInstance();
            inflateFragment(noSearchResultFragment,true);
        } else if (userInput == null){
            CardCollectionFragment cardCollectionFragment = CardCollectionFragment.newInstance(userInput);
            inflateFragment(cardCollectionFragment,true);
        }
        else {
            Log.d("FINDME", "User Has Inputted ");
            CardCollectionFragment cardCollectionFragment = CardCollectionFragment.newInstance(userInput);
            inflateFragment(cardCollectionFragment, true);
        }
    }

    @Override
    public void gotToCorrectCardFragment(CardModel cardModel) {
        if (cardModel.getAtk() != null) {
            MonsterCardFragment monsterCardFragment = MonsterCardFragment.newInstance(cardModel);
            inflateFragment(monsterCardFragment, true);
        } else {
            SpellTrapCardFragment spellTrapCardFragment = SpellTrapCardFragment.newInstance(cardModel);
            inflateFragment(spellTrapCardFragment, true);
        }

    }

    @Override
    public void goToUserFilter() {
        UserFilterFragment userFilterFragment = UserFilterFragment.newInstance();
        inflateFragment(userFilterFragment, true);
    }

    @Override
    public void goToCardRulings(String cardName) {
        // May give a 404 error since card does not have card ruling
        String cardRulingWebsite = "https://yugioh.fandom.com/wiki/Card_Rulings:";
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(cardRulingWebsite + cardName));
        startActivity(webIntent);
    }

    @Override
    public void startAppFragment() {
        UserChoosesFragment userChoosesFragment = UserChoosesFragment.newInstance();
        inflateFragment(userChoosesFragment);
    }

    @Override
    public boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfoMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo netInfoWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (netInfoMobile != null && netInfoMobile.isConnectedOrConnecting()) {
            Log.v("TAG", "Mobile Internet connected");
            return true;
        }
        if (netInfoWifi != null && netInfoWifi.isConnectedOrConnecting()) {
            Log.v("TAG", "Wifi Internet connected");
            return true;
        }
        return false;
    }

    @Override
    public void restartCardDownload(Button button, int textForSuccess) {
        CardNetworkCall.setupCardDataList(button, textForSuccess);
    }
}
