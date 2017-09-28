package com.example.patrickcaruso.activistwatch.Organization;

public class Organization {
    private int orgId;
    private int ownerId;
    private String name;
    private String description;
    private String keywords;
    private String members;
    private String events;

    public Organization(int id) {
        this.orgId = id;
    }

    public Organization(int orgId, int ownerId, String name) {
        this(orgId, ownerId, name, "", "", "", "");
    }

    public Organization(int orgId, int ownerId, String name, String description, String keywords, String members, String events) {
        this.orgId = orgId;
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.keywords = keywords;
        this.members = members;
        this.events = events;
    }

    //GETTERS
    public int getOrgId() {
        return orgId;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getKeywords() {
        return keywords;
    }
    public String getMembers() {
        return members;
    }
    public String getEvents() {
        return events;
    }

    //SETTERS
    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public void setMembers(String members) {
        this.members = members;
    }
    public void setEvents(String events) {
        this.events = events;
    }

}
