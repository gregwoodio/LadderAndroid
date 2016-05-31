package tech.gregwood.ladderandroid.utility;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import tech.gregwood.ladderandroid.data.Organization;
import tech.gregwood.ladderandroid.data.User;

/**
 * Created by greg on 5/17/2016.
 */
public class GetOrganizationTask extends AsyncTask<Integer, Void, Organization> {

    public final String DEBUG_TAG = "LADDER_DEBUG";

    @Override
    protected Organization doInBackground(Integer... params) {

        //must have only one param
        if (params.length != 1) {
            return null;
        }

        try {
            JSONArray json = Utility.getOrganization(params[0]);

            Log.d(DEBUG_TAG, json.toString());
            json = json.getJSONArray(0);
            JSONObject obj = json.getJSONObject(0);

            Organization organization = new Organization();
            organization.setProfileID(Integer.parseInt(obj.getString("ProfileID")));
            organization.setOrganizationName(obj.getString("OrganizationName"));
            organization.setAddress(obj.getString("Address"));
            organization.setUrl(new URL(obj.getString("URL")));
            organization.setMissionStatement(obj.getString("MissionStatement"));
            organization.setUsername(obj.getString("Username"));
            organization.setEmail(obj.getString("Email"));
            organization.setPictureURL(new URL(obj.getString("PictureURL")));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);
            organization.setTimestamp(formatter.parse(obj.getString("Timestamp")));

            organization.setAccountType(Integer.parseInt(obj.getString("AccountType")));

            return organization;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
