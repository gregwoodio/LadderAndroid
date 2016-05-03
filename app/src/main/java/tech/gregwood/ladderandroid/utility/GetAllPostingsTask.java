package tech.gregwood.ladderandroid.utility;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import tech.gregwood.ladderandroid.data.Posting;

/**
 * Created by greg on 5/3/2016.
 */
public class GetAllPostingsTask extends AsyncTask<Void, Void, ArrayList<Posting>> {

    @Override
    protected ArrayList<Posting> doInBackground(Void... params) {

        ArrayList<Posting> postings = new ArrayList<>();

        try {
            //get json of all postings
            JSONArray json = Utility.getAllPostings();
            json = json.getJSONArray(0); //the JSONArray comes in a length 1 array, so get the array at index 0.

            //iterate through the array and create a Posting object for each
            for (int i = 0; i < json.length(); i++) {
                JSONObject obj = json.getJSONObject(i);

                Posting posting = new Posting();
                posting.setPostingID(Integer.parseInt(obj.getString("postingID")));
                posting.setJobTitle(obj.getString("jobTitle"));
                posting.setLocation(obj.getString("location"));
                posting.setJobDescription(obj.getString("description"));
                posting.setOrganizerName(obj.getString("organizationName"));

                //add new posting to array list
                postings.add(posting);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return postings;
    }

    @Override
    protected void onPostExecute(ArrayList<Posting> postings) {
        super.onPostExecute(postings);

    }
}
