package com.example.patrickcaruso.activistwatch.Adapter;

import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.Event.Event;
import com.google.gson.Gson;
public class EventAdapter {
    public static Event adapt(String json) {
        try {
            Gson gson = new Gson();
            Event event1 = gson.fromJson(json.replace("[", "").replace("]", ""), Event.class);
            return event1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Event adapt(int id) {
        try {
            Gson gson = new Gson();
            String json = Database.lookupEvent(id).replace("[", "").replace("]", "");
            Event event1 = gson.fromJson(json, Event.class);
            return event1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
