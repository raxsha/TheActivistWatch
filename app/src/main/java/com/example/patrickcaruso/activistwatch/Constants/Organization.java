package com.example.patrickcaruso.activistwatch.Constants;

import java.util.List;

/**
 * Created by Greeness on 9/25/2017.
 */

public class Organization {
    private int orgId;
    private int ownerId;
    private String name;
    private String description;
    private String keywords;
    private String members;
    private String events;

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
}
