package com.tamer.plank.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-6.
 */
public class CardLab {
    private ArrayList<EventCard> mEvents;

    private static CardLab ourInstance = new CardLab();

    public static CardLab getInstance() {
        return ourInstance;
    }

    private CardLab() {

        mEvents = new ArrayList<>();
        for (int i=0;i<10;i++) {
            EventCard e = new EventCard();
            e.setTitle("Event #:" + i);
            mEvents.add(e);
        }

    }

    public ArrayList<EventCard> getEvents() {
        return mEvents;
    }

    public EventCard getEventCard(UUID uuid) {
        for (EventCard e : mEvents) {
            if (e.getId().equals(uuid)) {
                return e;
            }
        }
        return null;
    }
}
