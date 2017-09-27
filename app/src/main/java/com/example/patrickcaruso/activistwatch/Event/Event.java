package com.example.patrickcaruso.activistwatch.Event;

import android.graphics.Point;

import com.example.patrickcaruso.activistwatch.Organization.Organization;
import com.example.patrickcaruso.activistwatch.User.User;

import java.util.Date;
import java.util.List;

public class Event {

    private int id;
    private Organization ownerOrganization;
    private User ownerUser;
    private String photo;
    private String name;
    private String description;
    private String blurb;
    private Point location;
    private Date time;
    private List<String> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Organization getOwnerOrganization() {
        return ownerOrganization;
    }

    public void setOwnerOrganization(Organization ownerOrganization) {
        this.ownerOrganization = ownerOrganization;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Integer> getPosts() {
        return posts;
    }

    public void setPosts(List<Integer> posts) {
        this.posts = posts;
    }

    private List<Integer> posts;

}
