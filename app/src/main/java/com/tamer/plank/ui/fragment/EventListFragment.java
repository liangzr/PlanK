package com.tamer.plank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.tamer.plank.R;
import com.tamer.plank.model.CardLab;
import com.tamer.plank.model.EventCard;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-8.
 */
public class EventListFragment extends ListFragment {

    public static final String EXTRA_EVENT_ID = "com.tamer.plank.ui.event_id";

    private EventCard mEvent;

    public static EventListFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_EVENT_ID, crimeId);

        EventListFragment fragment = new EventListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID eventId = (UUID) getArguments().getSerializable(EXTRA_EVENT_ID);
        mEvent = CardLab.getInstance().getEventCard(eventId);

        EventListAdapter adapter = new EventListAdapter(mEvent.getTips());
        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView = getListView();
        listView.setDivider(null);
    }

    private class EventListAdapter extends ArrayAdapter<String> {

        public EventListAdapter(ArrayList<String> tips) {
            super(getActivity(), 0, tips);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //If we weren't given a view ,inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_tip, null);
            }

            String tip = getItem(position);
            EditText editText = (EditText) convertView.findViewById(R.id.event_tip);
            editText.setText(tip);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mEvent.getTips().set(position, s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            /*editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == KeyEvent.KEYCODE_ENTER) {
                        mEvent.getTips().set(position, v.getText().toString());
                        mEvent.getTips().add("");
                        ((EventListAdapter) getListAdapter()).notifyDataSetChanged();
                    }
                    return true;
                }
            });*/
            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((EventListAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
