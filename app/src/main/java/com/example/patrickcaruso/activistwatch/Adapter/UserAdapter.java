package com.example.patrickcaruso.activistwatch.Adapter;

import com.example.patrickcaruso.activistwatch.Event.Event;
import com.example.patrickcaruso.activistwatch.Organization.Organization;
import com.example.patrickcaruso.activistwatch.User.User;
import com.example.patrickcaruso.activistwatch.User.UserBuilder;
import com.example.patrickcaruso.activistwatch.User.UserModel;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserAdapter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static User adapt(String databaseResponse) {
        if (databaseResponse == null) {
            return null;
        }

        databaseResponse = databaseResponse.replace("[", "").replace("]", "");

        Gson gson = new Gson();
        UserModel user = gson.fromJson(databaseResponse, UserModel.class);
        return new UserBuilder()
                .setId(user.getId())
                .setDateOfBirth(date(user.getDateOfBirth()))
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setUsername(user.getUsername())
                .setAttendedEvents(events(user.getAttendedEvents()))
                .setInterestedEvents(events(user.getInterestedEvents()))
                .setOrganizations(organizations(user.getOrganizations()))
                .createUser();
    }

    private static Date date(String date) {
        try {
            if(date == null) {
                return new Date();
            }
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Event> events(String attended) {
        if (attended.length() == 0) {
            return new ArrayList<>();
        }
        String[] events;
        if (attended.contains(",")) {
            events = attended.split(",");
        } else {
            events = new String[] {attended};
        }

        List<Event> eventList = new ArrayList<Event>();
        for (String event: events) {
            eventList.add(new Event(Integer.parseInt(event)));
        }
        return eventList;
    }

    private static List<Organization> organizations(String organization) {
        if (organization.length() == 0) {
            return new ArrayList<>();
        }
        String[] orgs;
        if (organization.contains(",")) {
            orgs = organization.split(",");
        } else {
            orgs = new String[] {organization};
        }

        List<Organization> orgList = new ArrayList<Organization>();
        for (String org: orgs) {
            orgList.add(new Organization(Integer.parseInt(org)));
        }
        return orgList;
    }
}
