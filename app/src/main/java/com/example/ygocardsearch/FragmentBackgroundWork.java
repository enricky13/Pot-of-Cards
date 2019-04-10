package com.example.ygocardsearch;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.example.ygocardsearch.model.CardModel;

public interface FragmentBackgroundWork {
    void goToCardSearchFragment();
    void goToCardCollectionFragment(String userInput);
    void gotToCorrectCardFragment(CardModel cardModel);
    void goToUserFilter();
    void goToCardRulings(String cardName);
    void startAppFragment();
    boolean isInternetOn();
    void restartCardDownload(Button button, int textForSuccess);
    void goToBioFragment();
}
