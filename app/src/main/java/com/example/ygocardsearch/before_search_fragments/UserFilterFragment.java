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

import com.example.ygocardsearch.FragmentBackgroundWork;
import com.example.ygocardsearch.R;
import com.example.ygocardsearch.sharedPref.FilterSharedPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFilterFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    public static final String TAG = "FINDME";
    public static final String SHARED_PREF_KEY = "SHARED PREF KEY";
    private boolean isMonster, isSpell, isTrap;
    private int monsterTypePosition, monsterAttributePosition, spellTypeSpinnerPosition, trapTypeSpinnerPosition, atkMaxValue, atkMinValue;
    private View rootView;
    private Button applyFilterButton;
    private CheckBox monsterCheck, spellCheck, trapCheck;
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
        findViews();
        if (sharedPreferences != null){
            isMonster = sharedPreferences.getBoolean(FilterSharedPreference.MONSTER_CARD_KEY,false);
            isSpell = sharedPreferences.getBoolean(FilterSharedPreference.SPELL_CARD_KEY,false);
            isTrap = sharedPreferences.getBoolean(FilterSharedPreference.TRAP_CARD_KEY,false);

            monsterType = sharedPreferences.getString(FilterSharedPreference.MONSTER_TYPE_KEY, null);
            monsterAttribute = sharedPreferences.getString(FilterSharedPreference.MONSTER_ATTRIBUTE_KEY,null);
            spellType = sharedPreferences.getString(FilterSharedPreference.SPELL_TYPE,null);
            trapType = sharedPreferences.getString(FilterSharedPreference.TRAP_TYPE,null);

            monsterTypePosition = sharedPreferences.getInt(FilterSharedPreference.MONSTER_TYPE_POSITION_KEY,0);
            monsterAttributePosition = sharedPreferences.getInt(FilterSharedPreference.MONSTER_ATTRIBUTE_POSITION_KEY,0);
            spellTypeSpinnerPosition = sharedPreferences.getInt(FilterSharedPreference.SPELL_TYPE_POSITION_KEY,0);
            trapTypeSpinnerPosition = sharedPreferences.getInt(FilterSharedPreference.TRAP_TYPE_POSITION_KEY,0);
        }
        Log.d(TAG, "Is there a value for Monster Type: "+sharedPreferences.contains(FilterSharedPreference.MONSTER_TYPE_KEY));
        Log.d(TAG, "onCreateView: MonsterType Value: "+sharedPreferences.getString(FilterSharedPreference.MONSTER_TYPE_KEY, null)+" Monster Spinner Value: "+sharedPreferences.getInt(FilterSharedPreference.MONSTER_TYPE_POSITION_KEY, -1));

        monsterCheck.setChecked(isMonster);
        spellCheck.setChecked(isSpell);
        trapCheck.setChecked(isTrap);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerSetup();
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

        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atkValueChecker();

//                sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
                Log.d(TAG, "onClick: Monster Type Value: "+monsterType);
                Log.d(TAG, "onClick: Spell Type Value"+spellType);
                Log.d(TAG, "onClick: Monster Type Position Value: "+monsterTypePosition);
                FilterSharedPreference.addMainCardTypeToSharedPref(sharedPreferences, isMonster, isSpell, isTrap);
                FilterSharedPreference.addMonsterFilterToSharedPref(sharedPreferences, monsterType, monsterAttribute, monsterTypePosition, monsterAttributePosition);
                FilterSharedPreference.addSpellFilterToSharedPref(sharedPreferences, spellType, spellTypeSpinnerPosition);
                FilterSharedPreference.addTrapFilterToSharedPref(sharedPreferences, trapType, trapTypeSpinnerPosition);

                fragmentBackgroundWork.goToCardSearchFragment();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentBackgroundWork = (FragmentBackgroundWork) context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
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
        monsterTypeSpinner = rootView.findViewById(R.id.monster_type_spinner);
        monsterAttributeSpinner = rootView.findViewById(R.id.monster_attribute_spinner);
        spellTypeSpinner = rootView.findViewById(R.id.spell_type_spinner);
        trapTypeSpinner = rootView.findViewById(R.id.trap_type_spinner);
        atkMinEditText = rootView.findViewById(R.id.atk_minumum);
        atkMaxEditText = rootView.findViewById(R.id.atk_maximum);

        atkMinEditText.setTransformationMethod(null);
        atkMinEditText.setTransformationMethod(null);
    }

    public void spinnerSetup() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.monster_type, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monsterTypeSpinner.setAdapter(adapter);
        monsterTypeSpinner.setSelection(monsterTypePosition);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.monster_attribute, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monsterAttributeSpinner.setAdapter(adapter);
        monsterAttributeSpinner.setSelection(monsterAttributePosition);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.spell_type, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spellTypeSpinner.setAdapter(adapter);
        spellTypeSpinner.setSelection(spellTypeSpinnerPosition);

        adapter = ArrayAdapter.createFromResource(getContext(), R.array.trap_type, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        trapTypeSpinner.setAdapter(adapter);
        trapTypeSpinner.setSelection(trapTypeSpinnerPosition);

        monsterTypeSpinner.setOnItemSelectedListener(this);
        monsterAttributeSpinner.setOnItemSelectedListener(this);
        spellTypeSpinner.setOnItemSelectedListener(this);
        trapTypeSpinner.setOnItemSelectedListener(this);
    }

    private void atkValueChecker() {
        if (atkMaxEditText != null){
            atkMaxValue = Integer.parseInt(atkMaxEditText.getText().toString());
        }
        else {
            atkMaxValue = -1;
        }
        if (atkMinEditText != null){
            atkMinValue = Integer.parseInt(atkMinEditText.getText().toString());
        }
        else {
            atkMinValue = -1;
        }
    }

    // Code runs through here when starting the app
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: Code is running through here");
        Log.d(TAG, "onItemSelected: Position Number"+position);
        switch (parent.getId()) {
            case R.id.monster_type_spinner:
                if (position == 0) {
                    monsterType = null;
                } else {
                    monsterType = monsterTypeSpinner.getItemAtPosition(monsterTypePosition).toString();
                }
                monsterTypePosition = position;
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
