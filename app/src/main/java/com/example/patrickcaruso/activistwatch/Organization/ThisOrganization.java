package com.example.patrickcaruso.activistwatch.Organization;

public class ThisOrganization {
    private static Organization myOrganization;
    public static int id = 0;

    public static void setId(int myId) {
        id = myId;
        myOrganization = new Organization(myId);
    }

    public static int getId() {
        return id;
    }

    public static Organization myOrg() {
        return myOrganization;
    }
}