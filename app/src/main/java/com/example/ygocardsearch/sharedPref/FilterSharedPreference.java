package com.example.ygocardsearch.sharedPref;

import android.content.SharedPreferences;

public class FilterSharedPreference {
    public static final String MONSTER_CARD_KEY = "isMonsterCard";
    public static final String SPELL_CARD_KEY = "isSpellCard";
    public static final String TRAP_CARD_KEY = "isTrapCard";
    public static final String MONSTER_TYPE_KEY = "MONSTER TYPE KEY";
    public static final String MONSTER_ATTRIBUTE_KEY = "MONSTER ATTRIBUTE KEY";
    public static final String SPELL_TYPE = "SPELL TYPE";
    public static final String TRAP_TYPE = "TRAP TYPE";
    public static final String MONSTER_TYPE_POSITION_KEY = "MONSTER TYPE POSITION KEY";
    public static final String MONSTER_ATTRIBUTE_POSITION_KEY = "MONSTER ATTRIBUTE POSITION KEY";
    public static final String SPELL_TYPE_POSITION_KEY = "SPELL TYPE POSITION KEY";
    public static final String TRAP_TYPE_POSITION_KEY = "TRAP TYPE POSITION KEY";
    public static final String ATTACK_MAX_VALUE_KEY = "ATTACK MAX VALUE";
    public static final String ATTACK_MIN_VALUE_KEY = "ATTACK MIN VALUE";
    public static final String CHECK_FOR_ATTACK = "CHECK FOR ATTACK";

    public static void addMainCardTypeToSharedPref(SharedPreferences sharedPreferences, boolean isMonster, boolean isSpellCard, boolean isTrapCard){
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor
               .putBoolean(MONSTER_CARD_KEY, isMonster)
               .putBoolean(SPELL_CARD_KEY, isSpellCard)
               .putBoolean(TRAP_CARD_KEY, isTrapCard)
               .apply();
    }

    public static void addMonsterFilterToSharedPref(SharedPreferences sharedPreferences, String monsterType, String monsterAttribute, int monsterTypePosition, int monsterAttributePosition){
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor
                .putString(MONSTER_TYPE_KEY, monsterType)
                .putString(MONSTER_ATTRIBUTE_KEY, monsterAttribute)
                .putInt(MONSTER_TYPE_POSITION_KEY, monsterTypePosition)
                .putInt(MONSTER_ATTRIBUTE_POSITION_KEY, monsterAttributePosition)
                .apply();
    }

    public static void addSpellFilterToSharedPref(SharedPreferences sharedPreferences, String spellType, int spellTypePosition){
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor
                .putString(SPELL_TYPE, spellType)
                .putInt(SPELL_TYPE_POSITION_KEY, spellTypePosition)
                .apply();
    }

    public static void addTrapFilterToSharedPref(SharedPreferences sharedPreferences, String trapType, int trapTypePosition){
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor
                .putString(TRAP_TYPE, trapType)
                .putInt(TRAP_TYPE_POSITION_KEY, trapTypePosition)
                .apply();
    }

    public static void addAtkValueToSharedPref(SharedPreferences sharedPreferences, boolean checkForAtk, int atkMaxValue, int atkMinValue){
        SharedPreferences.Editor editor = sharedPreferences.edit();
            editor
                    .putBoolean(CHECK_FOR_ATTACK, checkForAtk)
                .putInt(ATTACK_MAX_VALUE_KEY, atkMaxValue)
                .putInt(ATTACK_MIN_VALUE_KEY, atkMinValue)
                    .apply();
    }
}
