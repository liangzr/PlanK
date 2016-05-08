package com.tamer.plank.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-6.
 */
public class EventCard {
    private UUID mId;
    private String mTitle;
    private List<String> mTips;

    public EventCard() {
        //生成唯一标识符
        mId = UUID.randomUUID();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String Title) {
        this.mTitle = Title;
    }

    public List<String> getTips() {
        return mTips;
    }

    public void setTips(List<String> Tips) {
        this.mTips = Tips;
    }

    public UUID getId() {
        return mId;
    }
}
