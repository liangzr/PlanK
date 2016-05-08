package com.tamer.plank.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tamer.plank.R;
import com.tamer.plank.ui.adapter.TabAdapter;
import com.tamer.plank.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.tabs_viewpager);

        mViewPager.setAdapter(getAdapter());
        mTabLayout.setupWithViewPager(mViewPager);

        if (mTabLayout != null) {
            initTabIcons(mTabLayout);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventActivity.start(MainActivity.this);
                }
            });
        }

    }

    protected FragmentStatePagerAdapter getAdapter() {
        int tabsCount = 3;
        return new TabAdapter(getSupportFragmentManager(), tabsCount);
    }

    private void initTabIcons(@NonNull TabLayout tabLayout) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                setTabIconByPosition(tab, i);
            }
        }
    }

    private void setTabIconByPosition(@NonNull TabLayout.Tab tab, int position) {
        switch (position) {
            case 0:
                tab.setIcon(R.drawable.selector_event);
                break;
            case 1:
                tab.setIcon(R.drawable.selector_lock);
                break;
            case 2:
                tab.setIcon(R.drawable.selector_timer);
            default:
                break;
        }
    }

}
