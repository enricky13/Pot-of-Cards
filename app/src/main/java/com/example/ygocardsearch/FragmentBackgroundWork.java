package com.example.ygocardsearch;

import com.example.ygocardsearch.model.CardModel;

public interface FragmentBackgroundWork {
    void goToCardSearchFragment();
    void goToCardCollectionFragment(String userInput);
    void gotToCorrectCardFragment(CardModel cardModel);
    void goToUserFilter();
    void goToCardRulings(String cardName);
    void startAppFragment();
    boolean isInternetOn();
    void restartCardDownload();
}
