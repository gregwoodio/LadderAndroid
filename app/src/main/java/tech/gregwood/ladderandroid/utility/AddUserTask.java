package tech.gregwood.ladderandroid.utility;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import tech.gregwood.ladderandroid.data.User;

/**
 * Created by greg on 5/17/2016.
 */
public class AddUserTask extends AsyncTask<User, Void, Boolean> {
    @Override
    protected Boolean doInBackground(User... params) {

        if (params.length == 0) {
            return false;
        }

        User user = params[0];

        try {
            return Utility.addUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
