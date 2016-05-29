package tech.gregwood.ladderandroid.utility;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Locale;

import tech.gregwood.ladderandroid.data.Organization;
import tech.gregwood.ladderandroid.data.Profile;
import tech.gregwood.ladderandroid.data.User;

/**
 * Created by greg on 5/17/2016.
 */
public class LoginTask extends AsyncTask<String, Void, Profile> {

    private final String DEBUG_TAG = "LADDER_DEBUG";

    /**
     * AsyncTask that takes in username and password, and then returns a Profile. Profile will be
     * null if there is no match found in the database.
     * @param params Pass in two strings: username then password.
     * @return
     */
    @Override
    protected Profile doInBackground(String... params) {
        //need 2 parameters
        if (params.length < 2) {
            return null;
        }
        String username = params[0];
        String password = params[1];

        try {
            // There should only be one Posting returned, but it will come wrapped in a JSONArray.
            JSONArray jsonArray = Utility.loginProfile(username, password);
            jsonArray = jsonArray.getJSONArray(0);
            JSONObject json = jsonArray.getJSONObject(0);
            Log.d(DEBUG_TAG, json.toString());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);

            if (json == null) {
                return null;
            }
            if (json.getString("AccountType").equals("0")) {
                //user
                User user = new User();
                user.setProfileID(Integer.parseInt(json.getString("ProfileID")));
                user.setUsername(json.getString("Username"));
                user.setEmail(json.getString("Email"));
                try {
                    if (json.getString("PictureURL") != null || json.getString("PictureURL").length() > 0) {
                        user.setPictureURL(new URL(json.getString("PictureURL")));
                    }
                } catch (MalformedURLException ex) {
                    //bad URL
                }
                try {
                    user.setTimestamp(formatter.parse(json.getString("Timestamp")));
                } catch (ParseException ex) {
                    //date couldn't be parsed
                }
                user.setAccountType(Integer.parseInt(json.getString("AccountType")));
                user.setFirstName(json.getString("FirstName"));
                user.setFirstName(json.getString("FirstName"));
                user.setLastName(json.getString("LastName"));
                user.setUserDescription(json.getString("Description"));
                user.setResume(json.getString("Resume"));
                user.setAcademicStatus(Integer.parseInt(json.getString("AcademicStatus")));

                return user;

            } else if (json.getString("AccountType").equals("1")) {
                //organization
                Organization org = new Organization();
                org.setProfileID(Integer.parseInt(json.getString("ProfileID")));
                org.setUsername(json.getString("Username"));
                org.setEmail(json.getString("Email"));
                try {
                    if (json.getString("PictureURL") != null || json.getString("PictureURL").length() > 0) {
                        org.setPictureURL(new URL(json.getString("PictureURL")));
                    }
                } catch (MalformedURLException ex) {
                    //bad url
                }
                try {
                    org.setTimestamp(formatter.parse(json.getString("Timestamp")));
                } catch (ParseException ex) {
                    //date couldn't be parsed
                }
                org.setAccountType(Integer.parseInt(json.getString("AccountType")));
                org.setOrganizationName(json.getString("OrganizationName"));
                org.setAddress(json.getString("Address"));
                try {
                    if (json.getString("PictureURL") != null || json.getString("PictureURL").length() > 0) {
                        org.setUrl(new URL(json.getString("URL")));
                    }
                } catch (MalformedURLException ex) {
                    //bad url
                }
                org.setMissionStatement(json.getString("MissionStatement"));

                return org;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
