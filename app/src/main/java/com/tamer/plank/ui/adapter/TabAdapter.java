package com.tamer.plank.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tamer.plank.ui.fragment.TabListFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private int mCount;

    public TabAdapter(@NonNull FragmentManager manager, int count) {
        super(manager);
        mCount = count;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public Fragment getItem(int position) {

        return TabListFragment.newInstance();
    }


}

