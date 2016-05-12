package com.tamer.plank.ui.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.tamer.plank.R;
import com.tamer.plank.model.CardLab;
import com.tamer.plank.model.EventCard;
import com.tamer.plank.ui.adapter.TabAdapter;
import com.tamer.plank.ui.base.BaseActivity;
import com.tamer.plank.ui.fragment.EventListFragment;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private SearchView mSearchView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_welcome);
        setSupportActionBar(toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.tabs_viewpager);

        TabAdapter adapter = (TabAdapter) getAdapter();
        mViewPager.setAdapter(adapter);
        //使TabLayout与ViewPager关联
        mTabLayout.setupWithViewPager(mViewPager);

//        mListView = ((ListFragment) getSupportFragmentManager().findFragmentByTag(getFragmentTag(R.id.tabs_viewpager,0))).getListView();
//        mListView = ((ListFragment) adapter.getRegisteredFragment(mViewPager.getCurrentItem())).getListView();


        if (mTabLayout != null) {
            initTabIcons(mTabLayout);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventCard eventCard = new EventCard();
                    CardLab.getInstance(getApplicationContext()).getEvents().add(eventCard);
                    //Start EventActivity
                    Intent i = new Intent(MainActivity.this, EventActivity.class);
                    i.putExtra(EventListFragment.EXTRA_EVENT_ID, eventCard.getId());
                    startActivity(i);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_welcome, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);//在菜单中找到对应控件的item
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (menuItem != null) {
            mSearchView = (SearchView) menuItem.getActionView();
        }
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            mSearchView.setOnQueryTextListener(this);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
//            mListView.clearTextFilter();
        } else {
//            mListView.setFilterText(newText);
        }
        return true;
    }

    private String getFragmentTag(int viewPagerId, int fragmentPosition)
    {
        return "android:switcher:" + viewPagerId + ":" + fragmentPosition;
    }
}
