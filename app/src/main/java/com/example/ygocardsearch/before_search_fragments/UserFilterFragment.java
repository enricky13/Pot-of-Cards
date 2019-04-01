package com.example.ygocardsearch.before_search_fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.example.ygocardsearch.R;
import com.example.ygocardsearch.SharedPref.FilterSharedPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFilterFragment extends Fragment{
    public static final String TAG = "FINDME";
    private View rootView;
    private Button applyFilterButton;
    private CheckBox monsterCheck, spellCheck, trapCheck;
    private Spinner monsterTypeSpinner, spellTypeSpinner, trapTypeSpinner;


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
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerSetup();
        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterSharedPreference.addMainCardTypeToSharedPreference(getContext(),monsterCheck.isChecked(),spellCheck.isChecked(),trapCheck.isChecked());
            }
        });
    }

    private void findViews() {
        monsterCheck = rootView.findViewById(R.id.monster_card_checkbox);
        spellCheck = rootView.findViewById(R.id.spell_card_checkbox);
        trapCheck = rootView.findViewById(R.id.trap_card_checkbox);
        applyFilterButton = rootView.findViewById(R.id.apply_filter_button);
        monsterTypeSpinner = rootView.findViewById(R.id.monster_type_spinner);
        spellTypeSpinner = rootView.findViewById(R.id.spell_type_spinner);
        trapTypeSpinner = rootView.findViewById(R.id.trap_type_spinner);

    }

    public void spinnerSetup(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.monster_type,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monsterTypeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getContext(),R.array.spell_type,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spellTypeSpinner.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(getContext(),R.array.trap_type,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        trapTypeSpinner.setAdapter(adapter);
    }

}
