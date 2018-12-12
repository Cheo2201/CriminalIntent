package com.josepacheco.android.criminalintent.CriminalInent;

import android.support.v4.app.Fragment;

public class CrimeListActivity extends SinglefragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
