package com.example.patrickcaruso.activistwatch.Adapter;

import com.example.patrickcaruso.activistwatch.Database.Database;
import com.example.patrickcaruso.activistwatch.Organization.Organization;
import com.google.gson.Gson;

public class OrganizationAdapter {

    public static Organization adapt(String json) {
        try {
            Gson gson = new Gson();
            Organization org = gson.fromJson(json.replace("[", "").replace("]", ""), Organization.class);
            return org;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Organization adapt(int id) {
        try {
            Gson gson = new Gson();
            String json = Database.lookupOrganization(id).replace("[", "").replace("]", "");
            Organization org = gson.fromJson(json, Organization.class);
            return org;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
