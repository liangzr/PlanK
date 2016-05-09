package com.tamer.plank.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tamer.plank.R;
import com.tamer.plank.model.CardLab;
import com.tamer.plank.model.EventCard;
import com.tamer.plank.ui.activities.EventActivity;

import java.util.ArrayList;

public class TabListFragment extends ListFragment {
    private ArrayList<EventCard> mEvents;

    public static TabListFragment newInstance() {
        return new TabListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mEvents = CardLab.getInstance().getEvents();

        EventAdapter adapter = new EventAdapter(mEvents);
        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setDivider(null);
       /* listView.setClickable(false);
        listView.setItemsCanFocus(false);*/
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //Get the event from the adapter
        EventCard eventCard = ((EventAdapter) getListAdapter()).getItem(position);

        //Start EventActivity
        Intent i = new Intent(getActivity(), EventActivity.class);
        i.putExtra(EventListFragment.EXTRA_EVENT_ID, eventCard.getId());
        startActivity(i);
    }

    private class EventAdapter extends ArrayAdapter<EventCard> {

        public EventAdapter(ArrayList<EventCard> eventCards) {
            super(getActivity(), 0, eventCards);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_event, null);
            }

            //configure the view for the Event
            EventCard eventCard = getItem(position);
            TextView textView = (TextView) convertView.findViewById(R.id.card_title);
            textView.setText(eventCard.getTitle());


            return convertView;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        ((EventAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
