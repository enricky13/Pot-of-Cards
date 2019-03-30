package com.example.ygocardsearch;

import com.example.ygocardsearch.model.CardModel;

public interface FragmentToFragment {
    void goToCardSearchFragment();
    void goToCardCollectionFragment(String userInput);
    void gotToCorrectCardFragment(CardModel cardModel);
}
