package com.example.ygocardsearch.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class FilterSharedPreference {
    private static final String MONSTER_CARD_KEY = "isMonsterCard";
    private static final String SPELL_CARD_KEY = "isSpellCard";
    private static final String TRAP_CARD_KEY = "isTrapCard";
    private static SharedPreferences sharedPreferences;

    public static void addMainCardTypeToSharedPref(Context context, boolean isMonster, boolean isSpellCard, boolean isTrapCard){
       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
       sharedPreferences.edit()
               .putBoolean(MONSTER_CARD_KEY, isMonster)
               .putBoolean(SPELL_CARD_KEY, isSpellCard)
               .putBoolean(TRAP_CARD_KEY, isTrapCard)
               .apply();
    }

    public static void addMonsterTypesToSharedPref(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit();
    }
}
