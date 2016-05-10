package com.tamer.plank.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.tamer.plank.ui.fragment.TabListFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    private int mCount;
    public Fragment currentFragment;
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        currentFragment = (TabListFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

}

