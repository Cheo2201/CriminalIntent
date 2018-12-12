package com.josepacheco.android.criminalintent.CriminalInent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.josepacheco.android.criminalintent.CriminalInent.modelo.Crime;
import com.josepacheco.android.criminalintent.CriminalInent.modelo.CrimeLab;
import com.josepacheco.android.criminalintent.R;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private Crime mCrime;
            private TextView mTitleTextView;
            private TextView mDataTextView;
            private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDataTextView = itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }
            public void bindCrime (Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getmTitle());
            mDataTextView.setText(mCrime.getmDate().toString());
            mSolvedCheckBox.setChecked(mCrime.ismSolved());
        }

        @Override
        public void onClick(View view) {
           Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getmId());
        }
    }

        private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
            private List<Crime> mCrimes;

            public CrimeAdapter(List<Crime> crimes) {
                mCrimes = crimes;
            }

            @NonNull
            @Override
            public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
                return new CrimeHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
                Crime crime = mCrimes.get(position);
                holder.bindCrime(crime);


            }

            @Override
            public int getItemCount() {

                return mCrimes.size();
            }
        }
    }
