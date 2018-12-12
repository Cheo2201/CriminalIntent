package com.josepacheco.android.criminalintent.CriminalInent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class CrimeActivity extends SinglefragmentActivity {

    private static final String EXTRA_CRIME_ID="com.antonyrebolledo.android.criminalintent.crime.id";


    public static Intent newIntent(Context packageContext, UUID crimeid){
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeid);
        return  intent;
    }


    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }
}
