package com.example.ygocardsearch;

import com.example.ygocardsearch.card_data.CardDataList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private CardDataList cardDataList;
    @Before
    public void setupData(){
        cardDataList = new CardDataList();
    }
    @Test
    public void isDataCorrect(){

    }
}