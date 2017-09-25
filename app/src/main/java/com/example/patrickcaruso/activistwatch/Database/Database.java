package com.example.patrickcaruso.activistwatch.Database;

import com.example.patrickcaruso.activistwatch.Constants.URLConstants;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Database {

    /**
     * Creates an event in the database and returns the event's id
     * @param ownerOrganization the organization that is creating the event; -1 if not run by organization
     * @param ownerMember the member that is creating the event
     * @param pictureUrl the url of the photo that will be shown
     * @param name the name of the event
     * @param description the description of the event
     * @param blurb the blurb that is shown when swiping
     * @param location the location of the event
     * @param time the time of the event
     * @param tags the tags associated with the event
     * @param posts the posts made by the event
     * @return the id of the newly created event
     */
    public static int createEvent(int ownerOrganization,
                                  int ownerMember,
                                  String pictureUrl,
                                  String name,
                                  String description,
                                  String blurb,
                                  String location,
                                  String time,
                                  String tags,
                                  String posts) throws IOException {
        return genericUrlToId(generateCreateEventURL(ownerOrganization, ownerMember, pictureUrl, name, description, blurb,
                location, time, tags, posts));
    }

    /**
     * Queries a user from the database to get their information
     * @param id the id of the user
     * @return the user's unfiltered metadata as a JSON response
     * @throws IOException if network fails
     */
    public static String lookupEvent(int id) throws IOException {
        return query(generateEventQueryURL(id));
    }

    /**
     * Queries a user from the database to get their information
     * @param id the id of the user
     * @return the user's unfiltered metadata as a JSON response
     * @throws IOException if network fails
     */
    public static String lookupUser(int id) throws IOException {
        return query(generateUserQueryURL(id));
    }

    /**
     * Queries an organization from the database
     * @param id the id of the organization
     * @return the organization's unfiltered metadata as a JSON response
     * @throws IOException if network fails
     */
    public static String lookupOrganization(int id) throws IOException {
        return query(generateOrganizationQueryURL(id));
    }

    /**
     * Creates an organization and returns the new organization's id
     * @param ownerId the id of the owner of the organization
     * @param organizationName the name of the organization
     * @param description the description of the organization
     * @param keywords the keywords as a list delimited by commas (ex- "animals,dogs,cats")
     * @param members the members as a list delimited by commas (ex - "123,95,519")
     * @return the id of the newly created organization
     * @throws IOException if network fails
     */
    public static int createOrganization(int ownerId,
                                         String organizationName,
                                         String description,
                                         String keywords,
                                         String members) throws IOException {
        return genericUrlToId(generateCreateOrganizationUrl(ownerId, organizationName,
                description, keywords, members));
    }

    /**
     * Attempts to login with a username and password
     *
     * @param username the user's username
     * @param password the user's password
     * @return ret >  0  : successful login; returns the user's id
     * ret = -1  : unable to login
     */
    public static int login(String username,
                            String password) throws IOException {
        return genericUrlToId(generateLoginUrl(username, password));
    }

    /**
     * Generates a request to the server to create a user
     * @param username the user's declared username
     * @param email the user's declared email
     * @param password the user's declared password
     */
    public static int register(final String username,
                              final String email,
                              final String password) throws IOException {
        return genericUrlToId(generateRegisterUserUrl(username, email, password));
    }

    /**
     * Creates a generic request to the server and returns the
     * contents as the id
     * @param url the url of the PHP query file
     * @return ret > 0  : if valid id response
     *         ret = -1 : if invalid response
     * @throws IOException if network error
     */
    private static int genericUrlToId(String url) throws IOException {
        String response = query(url).trim();
        if (responseMatchesId(response)) {
            return Integer.parseInt(response);
        } else {
            return -1;
        }
    }

    private static String query(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private static boolean responseMatchesId(String string){
        final String SUCCESS_PATTERN =
                "[0-9]+";
        Pattern pattern = Pattern.compile(SUCCESS_PATTERN);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * Generates a URL to POST user information to in order
     * to add the user to the user database
     * @param username the user's declared username
     * @param password the user's declared password
     * @return an HTTPUrlConnection compatible POST URL
     */
    private static String generateLoginUrl(String username,
                                    String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.LOGIN_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.ADD_USER_USERNAME_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(username);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_USER_PASSWORD_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(password);
        return sb.toString();
    }

    /**
     * Generates a URL to POST user information to in order
     * to add the user to the user database
     * @param username the user's declared username
     * @param email the user's declared email
     * @param password the user's declared password
     * @return an HTTPUrlConnection compatible POST URL
     */
    private static String generateRegisterUserUrl(String username,
                                           String email,
                                           String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.ADD_USER_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.ADD_USER_USERNAME_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(username);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_USER_EMAIL_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(email);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_USER_PASSWORD_ATTRIBUTE);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(password);
        return sb.toString();
    }

    private static String generateCreateOrganizationUrl(int ownerId,
                                                        String organizationName,
                                                        String description,
                                                        String keywords,
                                                        String members) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.ADD_ORGANIZATION_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.ADD_ORGANIZATION_OWNER);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(ownerId);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_ORGANIZATION_NAME);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(organizationName);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_ORGANIZATION_DESCRIPTION);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(description);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_ORGANIZATION_KEYWORDS);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(keywords);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_ORGANIZATION_MEMBERS);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(members);
        return sb.toString();
    }

    private static String generateOrganizationQueryURL(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.LOOKUP_ORGANIZATION_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.LOOKUP_ORGANIZATION_ID);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(id);
        return sb.toString();
    }

    private static String generateUserQueryURL(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.LOOKUP_USER_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.LOOKUP_USER_ID);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(id);
        return sb.toString();
    }

    private static String generateEventQueryURL(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.LOOKUP_EVENT_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.LOOKUP_EVENT_ID);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(id);
        return sb.toString();
    }

    private static String generateCreateEventURL(int ownerOrganization,
                                                 int ownerMember,
                                                 String pictureUrl,
                                                 String name,
                                                 String description,
                                                 String blurb,
                                                 String location,
                                                 String time,
                                                 String tags,
                                                 String posts) {
        StringBuilder sb = new StringBuilder();
        sb.append(URLConstants.ADD_EVENT_URL_BASE);
        sb.append(URLConstants.POST_DELIMETER);
        sb.append(URLConstants.ADD_EVENT_OWNER_ORGANIZATION);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(ownerOrganization);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_OWNER_MEMBER);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(ownerMember);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_PICTURE_URL);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(pictureUrl);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_NAME);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(name);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_DESCRIPTION);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(description);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_BLURB);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(blurb);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_LOCATION);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(location);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_TIME);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(time);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_TAGS);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(tags);
        sb.append(URLConstants.POST_AND);
        sb.append(URLConstants.ADD_EVENT_POSTS);
        sb.append(URLConstants.POST_EQUALS);
        sb.append(posts);
        return sb.toString();
    }
}
