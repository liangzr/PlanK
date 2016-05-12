package com.tamer.plank.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-6.
 */
public class CardLab {
    private ArrayList<EventCard> mEvents;

    private boolean mShortEncryptionFlag;

    private EventIntentJSONSerializer mSerializer;

    public static final String FILENAME = "events.json";

    private Context mAppContext;

    private static CardLab ourInstance;

    public static CardLab getInstance(Context c) {
        if (ourInstance == null) {
            ourInstance = new CardLab(c.getApplicationContext());
        }
        return ourInstance;
    }

    private CardLab(Context AppContext) {
        mAppContext = AppContext;

        mShortEncryptionFlag = false;

        mSerializer = new EventIntentJSONSerializer(mAppContext, FILENAME);

        try {
            mEvents = mSerializer.loadEvents();
            Log.d("JSON", "loaded from files");
        } catch (Exception e) {
            mEvents = new ArrayList<>();
            Log.e("JSON", "Error loading crimes:" + e);
        }
    }

    public boolean saveEvents() {
        try {
            mSerializer.saveEvents(mEvents);
            Log.d("JSON", "events saved to file");
            return true;
        } catch (Exception e) {
            Log.e("JSON", "Error saving events:" + e);
            return false;
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

    public boolean isShortEncryptionFlag() {
        return mShortEncryptionFlag;
    }

    public void setShortEncryptionFlag(boolean ShortEncryptionFlag) {
        this.mShortEncryptionFlag = ShortEncryptionFlag;
    }
}
