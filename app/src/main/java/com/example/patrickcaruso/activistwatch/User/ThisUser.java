package com.example.patrickcaruso.activistwatch.User;


public class ThisUser {

    private static User myuser;
    public static int id = 0;

    public static void setId(int myId) {
        id = myId;
        myuser = new User(myId);
    }

    public int getId() {
        return id;
    }

    public static User myUser() {
        return myuser;
    }
}
