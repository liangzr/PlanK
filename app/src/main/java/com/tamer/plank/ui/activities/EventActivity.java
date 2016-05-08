package com.tamer.plank.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tamer.plank.R;
import com.tamer.plank.ui.base.BaseActivity;

/**
 * Created by liangzr on 16-5-8.
 */
public class EventActivity extends BaseActivity {

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, EventActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
    }
}
