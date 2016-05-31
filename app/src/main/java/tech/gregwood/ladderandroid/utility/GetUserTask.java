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

import tech.gregwood.ladderandroid.data.User;

/**
 * Created by greg on 5/17/2016.
 */
public class GetUserTask extends AsyncTask<Integer, Void, User> {

    public final String DEBUG_TAG = "LADDER_DEBUG";

    @Override
    protected User doInBackground(Integer... params) {

        //must have only one param
        if (params.length != 1) {
            return null;
        }

        try {
            JSONArray json = Utility.getUser(params[0]);

            Log.d(DEBUG_TAG, json.toString());
            json = json.getJSONArray(0);
            JSONObject obj = json.getJSONObject(0);

            User user = new User();
            user.setProfileID(Integer.parseInt(obj.getString("ProfileID")));
            user.setFirstName(obj.getString("FirstName"));
            user.setLastName(obj.getString("LastName"));
            user.setUserDescription(obj.getString("Description"));
            user.setResume(obj.getString("Resume"));
            user.setAcademicStatus(Integer.parseInt(obj.getString("AcademicStatus")));
            user.setUsername(obj.getString("Username"));
            user.setEmail(obj.getString("Email"));
            user.setPictureURL(new URL(obj.getString("FirstName")));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CANADA);
            user.setTimestamp(formatter.parse(obj.getString("Timestamp")));

            user.setAccountType(Integer.parseInt(obj.getString("AccountType")));

            return user;

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
