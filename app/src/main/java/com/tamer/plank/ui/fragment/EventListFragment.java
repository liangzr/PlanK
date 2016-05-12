package com.tamer.plank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    private ListView mListView;

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
        mEvent = CardLab.getInstance(getActivity()).getEventCard(eventId);

        EventListAdapter adapter = new EventListAdapter(mEvent.getTips());
        setListAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = getListView();
        mListView.setDivider(null);
    }

    private class EventListAdapter extends ArrayAdapter<String> {
        private int mPosition;

        public EventListAdapter(ArrayList<String> tips) {
            super(getActivity(), 0, tips);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            final String tip = mEvent.getTips().get(position);

            mPosition = position;
            //If we weren't given a view ,inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_tip, null);
                viewHolder = new ViewHolder();
                viewHolder.editText = (EditText) convertView.findViewById(R.id.event_tip);
                viewHolder.fab = (FloatingActionButton) convertView.findViewById(R.id.fab_tip);

                viewHolder.editText.setTag(position);
                convertView.setTag(viewHolder);

                viewHolder.editText.addTextChangedListener(new TipTextWatcher(viewHolder));
                viewHolder.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mEvent.getTips().add(null);
                        refreshList();
                        int itemPositon = mEvent.getTips().size() -1;
                        mListView.smoothScrollToPositionFromTop(itemPositon,10);
                    }
                });
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.editText.setTag(position);
            }

            viewHolder.editText.setText(tip);
            viewHolder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus) {
                        mListView.smoothScrollToPositionFromTop(position, 10);

                    }
                }
            });
            viewHolder.fab.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mEvent.getTips().size() > 1) {
                        mEvent.getTips().remove(position);
                        refreshList();
                    } else {
                        Toast.makeText(getActivity(),"last one!!!",Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
            });

            return convertView;

        }

        private class ViewHolder {
            EditText editText;
            FloatingActionButton fab;
        }

        private class TipTextWatcher implements TextWatcher {

            ViewHolder holder = null;

            public TipTextWatcher(ViewHolder holder) {
                this.holder = holder;
            }

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int position = (int) holder.editText.getTag();
                mEvent.getTips().set(position, s.toString());

                Log.d("getView", "Position: " + Integer.toString(position) + " | " + "tip:" + s.toString());
            }
        }


    }


    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    public void refreshList() {
        ((EventListAdapter) getListAdapter()).notifyDataSetChanged();
    }
}
