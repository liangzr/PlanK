package com.tamer.plank.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by liangzr on 16-5-12.
 */
public class EventIntentJSONSerializer {
    private Context mContext;
    private String mFilename;

    public EventIntentJSONSerializer(Context c, String f) {
        mContext = c;
        mFilename = f;
    }

    public void saveEvents(ArrayList<EventCard> events) throws JSONException, IOException {
        //Build an array in JSON
        JSONArray array = new JSONArray();
        for (EventCard eventCard : events) {
            array.put(eventCard.toJSON());
           // array.put(eventCard.getTips());
        }

        //Write the file to disk
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<EventCard> loadEvents() throws IOException,JSONException {
        ArrayList<EventCard> events = new ArrayList<>();
        BufferedReader reader = null;
        try {
            //open and read the file into a stringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                //line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            //Parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            //Build the array of events from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                events.add(new EventCard(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {

        }finally {
            if (reader != null) {
                reader.close();
            }
        }
        return events;
    }

}
