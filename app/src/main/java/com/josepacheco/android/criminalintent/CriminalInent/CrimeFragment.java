package com.josepacheco.android.criminalintent.CriminalInent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.josepacheco.android.criminalintent.CriminalInent.modelo.Crime;
import com.josepacheco.android.criminalintent.CriminalInent.modelo.CrimeLab;
import com.josepacheco.android.criminalintent.R;

import java.util.UUID;

public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID="crime.id";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDataButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        UUID crimeId = (UUID) args.getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         View v = inflater.inflate(R.layout.fragment_crime,container,false);
        mTitleField = v.findViewById(R.id.crime_title);
        mDataButton = v.findViewById(R.id.crime_date);
        mSolvedCheckBox = v.findViewById(R.id.crime_solved);

        mTitleField.setText(mCrime.getmTitle());
        mSolvedCheckBox.setChecked(mCrime.ismSolved());
        mDataButton.setText(mCrime.getmDate().toString());
        mDataButton.setEnabled(false);

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               mCrime.setmTitle(mTitleField.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }


        });

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setmSolved(mSolvedCheckBox.isChecked());
            }
        });
         return v;
    }
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
