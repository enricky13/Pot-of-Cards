package com.example.ygocardsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.ygocardsearch.card_data.CardDataList;
import com.example.ygocardsearch.model.CardModel;
import com.example.ygocardsearch.network.CardNetworkCall;
import com.example.ygocardsearch.network.ImplicitErrorNetworkCallSingleton;
import com.example.ygocardsearch.network.YgoCardSingleton;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Request;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest{
    private List<CardModel> cardModelList;
    @Before
    public void setupTest(){
        CardDataList.convertToList(new CardModel[4][10]);
        cardModelList = CardDataList.getCardModelList();
    }

    @Test
    public void isDataCorrect(){
        Assert.assertNotNull(cardModelList);
        Assert.assertNotEquals(cardModelList, 0);
        Assert.assertEquals(cardModelList.size(), 10);
    }
}