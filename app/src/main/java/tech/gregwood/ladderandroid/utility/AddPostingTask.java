package tech.gregwood.ladderandroid.utility;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

import tech.gregwood.ladderandroid.data.Posting;

/**
 * Created by greg on 5/8/2016.
 */
public class AddPostingTask extends AsyncTask <Posting, Void, Boolean> {
    @Override
    protected Boolean doInBackground(Posting... postings) {
        if (postings.length == 0) {
            return false;
        }

        Posting posting = postings[0];
        try {
            return Utility.addPosting(posting);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
