package com.example.ygocardsearch.before_search_fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.R;
import com.example.ygocardsearch.sharedPref.FilterSharedPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFilterFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String TAG = "FINDME";
    public static final String SHARED_PREF_KEY = "SHARED PREF KEY";
    private boolean isMonster, isSpell, isTrap, checkForAtk;
    private int monsterTypePosition, monsterAttributePosition, spellTypeSpinnerPosition, trapTypeSpinnerPosition, atkMaxValue, atkMinValue;
    private View rootView;
    private Button applyFilterButton, resetFilterButton;
    private CheckBox monsterCheck, spellCheck, trapCheck, atkCheck;
    private Spinner monsterTypeSpinner, monsterAttributeSpinner, spellTypeSpinner, trapTypeSpinner;
    private String monsterType, monsterAttribute, spellType, trapType;
    private EditText atkMinEditText, atkMaxEditText;
    private FragmentBackgroundWork fragmentBackgroundWork;
    private SharedPreferences sharedPreferences;

    public UserFilterFragment() {
        // Required empty public constructor
    }

    public static UserFilterFragment newInstance() {
        UserFilterFragment userFilterFragment = new UserFilterFragment();
        return userFilterFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_filter, container, false);
        sharedPreferences = rootView.getContext().getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        findViews();
        spinnerSetup();
        sharedPrefUpdate();
        Log.d(TAG, "onCreateView: Value of monster type position: "+monsterTypePosition);
        setFiltersForUser();
        return rootView;
    }

    private void sharedPrefUpdate() {
        if (sharedPreferences != null){
            isMonster = sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY,false);
            isSpell = sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY,false);
            isTrap = sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY,false);
            checkForAtk = sharedPreferences.getBoolean(FilterSharedPreference.CHECK_FOR_ATTACK, false);

            monsterType = sharedPreferences.getString(FilterSharedPreference.MONSTER_TYPE_KEY, null);
            monsterAttribute = sharedPreferences.getString(FilterSharedPreference.MONSTER_ATTRIBUTE_KEY,null);
            spellType = sharedPreferences.getString(FilterSharedPreference.SPELL_TYPE,null);
            trapType = sharedPreferences.getString(FilterSharedPreference.TRAP_TYPE,null);

            monsterTypePosition = sharedPreferences.getInt(FilterSharedPreference.MONSTER_TYPE_POSITION_KEY,0);
            monsterAttributePosition = sharedPreferences.getInt(FilterSharedPreference.MONSTER_ATTRIBUTE_POSITION_KEY,0);
            spellTypeSpinnerPosition = sharedPreferences.getInt(FilterSharedPreference.SPELL_TYPE_POSITION_KEY,0);
            trapTypeSpinnerPosition = sharedPreferences.getInt(FilterSharedPreference.TRAP_TYPE_POSITION_KEY,0);
            atkMinValue = sharedPreferences.getInt(FilterSharedPreference.ATTACK_MIN_VALUE_KEY, 0);
            atkMaxValue = sharedPreferences.getInt(FilterSharedPreference.ATTACK_MAX_VALUE_KEY, 0);
        }
    }

    private void setFiltersForUser() {
        Log.d(TAG, "setFiltersForUser: "+checkForAtk+monsterTypePosition+monsterAttributePosition+spellTypeSpinnerPosition+trapTypeSpinnerPosition);
        monsterCheck.setChecked(isMonster);
        spellCheck.setChecked(isSpell);
        trapCheck.setChecked(isTrap);
        atkCheck.setChecked(checkForAtk);
        monsterTypeSpinner.setSelection(monsterTypePosition);
        monsterAttributeSpinner.setSelection(monsterAttributePosition);
        spellTypeSpinner.setSelection(spellTypeSpinnerPosition);
        trapTypeSpinner.setSelection(trapTypeSpinnerPosition);
        atkMinEditText.setText(Integer.toString(atkMinValue));
        atkMaxEditText.setText(Integer.toString(atkMaxValue));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Log.d(TAG, "onViewCreated Monster Type Value: "+monsterType);

        monsterCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMonster = monsterCheck.isChecked();
            }
        });
        spellCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSpell = spellCheck.isChecked();
            }
        });
        trapCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTrap = trapCheck.isChecked();
            }
        });
        atkCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForAtk = atkCheck.isChecked();
            }
        });

        resetFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().clear().apply();
                sharedPrefUpdate();
                setFiltersForUser();
                isMonster = true;
                isSpell = true;
                isTrap = true;
                monsterCheck.setChecked(isMonster);
                spellCheck.setChecked(isSpell);
                trapCheck.setChecked(isTrap);
                FilterSharedPreference.addMainCardTypeToSharedPref(sharedPreferences, isMonster, isSpell, isTrap);
                Toast.makeText(getContext(), "Filter Information Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (atkCheck.isChecked()){
                    atkValueChecker();
                }
                if (!isMonster && !isSpell && !isTrap) {
                    Toast.makeText(getContext(), "You must search at least one card type", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(TAG, "onClick: Monster Type Value: " + monsterType);
                    Log.d(TAG, "onClick: Spell Type Value" + spellType);
                    Log.d(TAG, "onClick: Monster Type Position Value: " + monsterTypePosition);
                    Log.d(TAG, "onClick: Monster Checkbox: " + monsterCheck.isChecked());
                    FilterSharedPreference.addMainCardTypeToSharedPref(sharedPreferences, isMonster, isSpell, isTrap);
                    FilterSharedPreference.addMonsterFilterToSharedPref(sharedPreferences, monsterType, monsterAttribute, monsterTypePosition, monsterAttributePosition);
                    FilterSharedPreference.addAtkValueToSharedPref(sharedPreferences, checkForAtk, atkMaxValue, atkMinValue);
                    FilterSharedPreference.addSpellFilterToSharedPref(sharedPreferences, spellType, spellTypeSpinnerPosition);
                    FilterSharedPreference.addTrapFilterToSharedPref(sharedPreferences, trapType, trapTypeSpinnerPosition);

                    fragmentBackgroundWork.goToCardSearchFragment();
                }

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentBackgroundWork = (FragmentBackgroundWork) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentBackgroundWork = null;
    }

    private void findViews() {
        monsterCheck = rootView.findViewById(R.id.monster_card_checkbox);
        spellCheck = rootView.findViewById(R.id.spell_card_checkbox);
        trapCheck = rootView.findViewById(R.id.trap_card_checkbox);
        applyFilterButton = rootView.findViewById(R.id.apply_filter_button);
        resetFilterButton = rootView.findViewById(R.id.reset_filter_button);
        monsterTypeSpinner = rootView.findViewById(R.id.monster_type_spinner);
        monsterAttributeSpinner = rootView.findViewById(R.id.monster_attribute_spinner);
        spellTypeSpinner = rootView.findViewById(R.id.spell_type_spinner);
        trapTypeSpinner = rootView.findViewById(R.id.trap_type_spinner);
        atkCheck = rootView.findViewById(R.id.attack_value_checkbox);
        atkMinEditText = rootView.findViewById(R.id.atk_minumum);
        atkMaxEditText = rootView.findViewById(R.id.atk_maximum);

        atkMinEditText.setTransformationMethod(null);
        atkMaxEditText.setTransformationMethod(null);
    }

    public void spinnerSetup() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.monster_type, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        monsterTypeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.monster_attribute, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        monsterAttributeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.spell_type, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spellTypeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.trap_type, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        trapTypeSpinner.setAdapter(adapter);

        monsterTypeSpinner.setOnItemSelectedListener(this);
        monsterAttributeSpinner.setOnItemSelectedListener(this);
        spellTypeSpinner.setOnItemSelectedListener(this);
        trapTypeSpinner.setOnItemSelectedListener(this);
    }

    private void atkValueChecker() {
        if (atkMaxEditText != null && !(atkMaxEditText.getText().toString().trim().length() == 0)){
            atkMaxValue = Integer.parseInt(atkMaxEditText.getText().toString());
        }
        else {
            atkMaxValue = 10001;
        }
        if (atkMinEditText != null && !(atkMinEditText.getText().toString().trim().length() == 0)){
            atkMinValue = Integer.parseInt(atkMinEditText.getText().toString());
        }
        else {
            atkMinValue = 0;
        }
    }

    // Code runs through here when starting the app
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.monster_type_spinner:
                if (position == 0) {
                    Log.d(TAG, "onItemSelected: Monster Type being overriden");
                    monsterType = null;
                } else {
                    Log.d(TAG, "onItemSelected: "+position);
                    monsterType = parent.getItemAtPosition(position).toString();
                    Log.d(TAG, "onItemSelected: "+monsterType);
                }
                monsterTypePosition = position;
                Log.d(TAG, "onItemSelected: Monster Type Position"+monsterTypePosition);
                break;
            case R.id.monster_attribute_spinner:
                if (position == 0) {
                    monsterAttribute = null;
                } else {
                    monsterAttribute = monsterAttributeSpinner.getItemAtPosition(position).toString();
                }
                monsterAttributePosition = position;
                break;
            case R.id.spell_type_spinner:
                if (position == 0) {
                    spellType = null;
                } else {
                    spellType = spellTypeSpinner.getItemAtPosition(position).toString();
                }
                spellTypeSpinnerPosition = position;
                break;
            case R.id.trap_type_spinner:
                if (position == 0) {
                    trapType = null;
                } else {
                    trapType = trapTypeSpinner.getItemAtPosition(position).toString();
                }
                trapTypeSpinnerPosition = position;
                break;
        }
        Log.d(TAG, "onItemSelected: This Method is done: "+monsterTypePosition);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // NO - OP
    }
}
