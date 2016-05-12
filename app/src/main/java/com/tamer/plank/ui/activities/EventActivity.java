package com.tamer.plank.ui.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tamer.plank.R;
import com.tamer.plank.model.CardLab;
import com.tamer.plank.model.EventCard;
import com.tamer.plank.ui.base.BaseActivity;
import com.tamer.plank.ui.fragment.EventListFragment;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by liangzr on 16-5-8.
 */
public class EventActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener,View.OnClickListener {


    private EventCard mEvent;
    private ArrayList<EventCard> mEvents;
    private DatePickerDialog mDatePickerDialog;
    private int mYear, mMonth, mDay;
    private Button mLock;
    private int mPosition;

    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, EventActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_event);
        toolbar.inflateMenu(R.menu.menu_event);
        toolbar.setOnMenuItemClickListener(this);


        UUID eventId = (UUID) getIntent().getSerializableExtra(EventListFragment.EXTRA_EVENT_ID);
        mEvent = CardLab.getInstance(this).getEventCard(eventId);

        mPosition = (int) getIntent().getSerializableExtra("POSITION");

        EditText editText = (EditText) findViewById(R.id.event_title);
        editText.setText(mEvent.getTitle());

        TextView tagLow = (TextView) findViewById(R.id.tv_tag_low);
        TextView tagMid = (TextView) findViewById(R.id.tv_tag_mid);
        TextView tegHigh = (TextView) findViewById(R.id.tv_tag_high);

        tagLow.setOnClickListener(this);
        tagMid.setOnClickListener(this);
        tegHigh.setOnClickListener(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEvent.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
            }
        }, 2016, 5, 10);

        //把 FramLayout 的内容替换为 EventListFragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_event_tips);

        if (fragment == null) {
            fragment = EventListFragment.newInstance(mEvent.getId());
            fm.beginTransaction().add(R.id.fragment_event_tips, fragment).commit();
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_lock:
                if (mEvent.isEncryption()) {
                    mEvent.setEncryption(false);
                    item.setIcon(R.drawable.ic_action_action_unlock);

                } else {
                    mEvent.setEncryption(true);
                    item.setIcon(R.drawable.ic_action_action_lock);
                }
                return true;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定删除？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        CardLab.getInstance(getApplicationContext()).getEvents().remove(mPosition);
                        EventActivity.this.finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tag_low:
                mEvent.setTag(EventCard.TAG_LOW);
                Toast.makeText(this,"low",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_tag_mid:
                mEvent.setTag(EventCard.TAG_MID);
                Toast.makeText(this,"mid",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_tag_high:
                mEvent.setTag(EventCard.TAG_HIGH);
                Toast.makeText(this,"high",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
    }
}
