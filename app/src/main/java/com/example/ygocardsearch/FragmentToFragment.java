package com.example.ygocardsearch;

import com.example.ygocardsearch.model.CardModel;

public interface FragmentToFragment {
    void goToCardSearchFragment();
    void goToCardCollectionFragment();
    void gotToCorrectCardFragment(CardModel cardModel);
}
