package com.example.patrickcaruso.activistwatch.User;

import com.example.patrickcaruso.activistwatch.Event.Event;
import com.example.patrickcaruso.activistwatch.Organization.Organization;

import java.util.Date;
import java.util.List;

public class UserBuilder {
    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private List<Organization> organizations;
    private List<Event> attendedEvents;
    private List<Event> interestedEvents;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserBuilder setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
        return this;
    }

    public UserBuilder setAttendedEvents(List<Event> attendedEvents) {
        this.attendedEvents = attendedEvents;
        return this;
    }

    public UserBuilder setInterestedEvents(List<Event> interestedEvents) {
        this.interestedEvents = interestedEvents;
        return this;
    }

    public User createUser() {
        return new User(id, username, email, firstName, lastName, dateOfBirth, organizations, attendedEvents, interestedEvents);
    }
}