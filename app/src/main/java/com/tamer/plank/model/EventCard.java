package com.tamer.plank.model;

import com.tamer.plank.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-6.
 */
public class EventCard {
    private UUID mId;
    private String mTitle;
    private ArrayList<String> mTips;
    private int mTag;
    private boolean mEncryption;

    public static final int TAG_LOW = R.color.tag_low;
    public static final int TAG_MID = R.color.tag_mid;
    public static final int TAG_HIGH = R.color.tag_high;

    //JSON
    public static final String JSON_ID = "id";
    public static final String JSON_TITLE = "title";
    public static final String JSON_TIP = "tip";
    public static final String JSON_TAG = "tag";
    public static final String JSON_ENCRYPTION = "encryption";


    public EventCard() {
        //生成唯一标识符
        mId = UUID.randomUUID();
        mTips = new ArrayList<>();
        mTips.add(null);
        mTag = TAG_MID;
        mTitle = null;
        mEncryption = false;
    }

    public EventCard(JSONObject json) throws JSONException {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mTag = json.getInt(JSON_TAG);
        mEncryption = json.getBoolean(JSON_ENCRYPTION);
        mTips = new ArrayList<>();
        mTips.add(null);
        /*JSONArray array = json.getJSONArray(JSON_TIP);
        //Build the array of events from JSONObjects
        for (int i = 0; i < array.length(); i++) {
            mTips.add((array.getJSONObject(i)).getString(JSON_TIP));
        }*/
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_TAG, mTag);
        json.put(JSON_ENCRYPTION, mEncryption);
        /*JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < mTips.size(); i++) {
            jsonArray.put(json.put(JSON_TIP, mTips.get(i)));
        }
        json.put(JSON_TIP,jsonArray);*/

        return json;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        this.mTitle = Title;
    }

    public ArrayList<String> getTips() {
        return mTips;
    }

    public void setTips(ArrayList<String> Tips) {
        this.mTips = Tips;
    }

    public UUID getId() {
        return mId;
    }

    public int getTag() {
        return mTag;
    }

    public void setTag(int Tag) {
        this.mTag = Tag;
    }

    public boolean isEncryption() {
        return mEncryption;
    }

    public void setEncryption(boolean Encryption) {
        this.mEncryption = Encryption;
    }
}
