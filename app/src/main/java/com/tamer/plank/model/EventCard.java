package com.tamer.plank.model;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-6.
 */
public class EventCard {
    private UUID mId;
    private String mTitle;
    private ArrayList<String> mTips;

    public EventCard() {
        //生成唯一标识符
        mId = UUID.randomUUID();
        mTips = new ArrayList<>();
        mTips.add(null);
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
}
