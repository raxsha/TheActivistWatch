package com.example.patrickcaruso.activistwatch.Event;

import java.util.Date;

public class ThisEvent {
    private static Event thisEvent;
    public static int id = 0;
    private static Date time;
    public static void setId(int myId) {
        id = myId;
        thisEvent = new Event(myId);
    }

    public static int getId() {
        return id;
    }

    public static Date getTime() {return time; }
}