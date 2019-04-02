package com.example.ygocardsearch.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class FilterSharedPreference {
    public static final String MONSTER_CARD_KEY = "isMonsterCard";
    public static final String SPELL_CARD_KEY = "isSpellCard";
    public static final String TRAP_CARD_KEY = "isTrapCard";
    public static final String MONSTER_TYPE_KEY = "MONSTER TYPE KEY";
    public static final String MONSTER_ATTRIBUTE_KEY = "MONSTER ATTRIBUTE KEY";
    public static final String SPELL_TYPE = "SPELL TYPE";
    public static final String TRAP_TYPE = "TRAP TYPE";
    public static SharedPreferences sharedPreferences;

    public static void addMainCardTypeToSharedPref(Context context, boolean isMonster, boolean isSpellCard, boolean isTrapCard){
       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
       sharedPreferences.edit()
               .putBoolean(MONSTER_CARD_KEY, isMonster)
               .putBoolean(SPELL_CARD_KEY, isSpellCard)
               .putBoolean(TRAP_CARD_KEY, isTrapCard)
               .apply();
    }

    public static void addMonsterFilterToSharedPref(Context context, String monsterType, String monsterAttribute){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString(MONSTER_TYPE_KEY, monsterType)
                .putString(MONSTER_ATTRIBUTE_KEY, monsterAttribute)
                .apply();
    }

    public static void addSpellFilterToSharedPref(Context context, String spellType){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString(SPELL_TYPE, spellType)
                .apply();
    }

    public static void addTrapFilterToSharedPref(Context context, String trapType){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit()
                .putString(TRAP_TYPE, trapType)
                .apply();
    }
}
