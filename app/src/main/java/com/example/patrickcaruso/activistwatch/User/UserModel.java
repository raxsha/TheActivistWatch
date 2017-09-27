package com.example.patrickcaruso.activistwatch.User;

import com.example.patrickcaruso.activistwatch.Event.Event;
import com.example.patrickcaruso.activistwatch.Organization.Organization;

import java.util.Date;
import java.util.List;

public class UserModel {

    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String organizations;
    private String attendedEvents;
    private String interestedEvents;

    public UserModel(int id,
                     String username,
                     String email,
                     String firstName,
                     String lastName,
                     String dateOfBirth,
                     String organizations,
                     String attendedEvents,
                     String interestedEvents) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.organizations = organizations;
        this.attendedEvents = attendedEvents;
        this.interestedEvents = interestedEvents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOrganizations() {
        return organizations;
    }

    public void setOrganizations(String organizations) {
        this.organizations = organizations;
    }

    public String getAttendedEvents() {
        return attendedEvents;
    }

    public void setAttendedEvents(String attendedEvents) {
        this.attendedEvents = attendedEvents;
    }

    public String getInterestedEvents() {
        return interestedEvents;
    }

    public void setInterestedEvents(String interestedEvents) {
        this.interestedEvents = interestedEvents;
    }
}
