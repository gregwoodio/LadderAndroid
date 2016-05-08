package tech.gregwood.ladderandroid.utility;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import tech.gregwood.ladderandroid.data.Posting;

/**
 * Created by greg on 5/3/2016.
 */
public class Utility {

    /**
     * A GET request to the specified URL.
     * @param url
     * @return A JSON array read from the URL.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray readFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1) {
            sb.append((char) cp);
        }

        String jsonText = sb.toString();
        JSONArray json = new JSONArray(jsonText);
        return json;
    }

    /**
     * A POST request to the specified URL.
     * @param urlString The requested URL
     * @param params SimpleEntries as Key Value pairs, to be used as POST parameters and values.
     * @return A JSON array read from the URL.
     */
    public static JSONArray readFromUrl(String urlString, AbstractMap.SimpleEntry<String, String>... params)
        throws IOException, JSONException{

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            AbstractMap.SimpleEntry<String, String> param = params[i];
            sb.append(param.getKey() + "=" + param.getValue());
            if (i < (params.length - 1)) {
                sb.append("&");
            }
        }
        String urlParams = sb.toString();
        byte[] postData = urlParams.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //add headers
        con.setRequestMethod("POST");


        // Send post request
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty( "charset", "utf-8");
        con.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
        con.setUseCaches( false );

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.write(postData);
        wr.flush();
        wr.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));

        sb = new StringBuilder();
        int cp;
        while ((cp = br.read()) != -1) {
            sb.append((char) cp);
        }

        String jsonText = sb.toString();
        JSONArray json = new JSONArray(jsonText);
        return json;
    }

    /**
     * Gets all the active Postings from the database.
     * @return A JSON array of all active postings
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray getAllPostings() throws IOException, JSONException {
        JSONArray json = readFromUrl("http://mobile.sheridanc.on.ca/~woodgre/Ladder/GetAllPostings.php");

        return json;
    }

    /**
     * Gets a specified posting
     * @param id ID of the specified posting.
     * @return A JSON array containing the specified posting.
     * @throws IOException
     * @throws JSONException
     */
    public static JSONArray getPosting(int id) throws IOException, JSONException {
        AbstractMap.SimpleEntry<String, String> requestedID = new AbstractMap.SimpleEntry<String, String>("PostingID", Integer.toString(id));
        JSONArray json = readFromUrl("http://mobile.sheridanc.on.ca/~woodgre/Ladder/GetPosting.php", requestedID);
        return json;
    }

    /**
     * Adds a Posting object to the database.
     * @param posting The posting to add to the database.
     * @return Success or failure of database operation.
     * @throws IOException
     * @throws JSONException
     */
    public static boolean addPosting(Posting posting) throws IOException, JSONException {

        //TODO: Add validation

        AbstractMap.SimpleEntry<String, String> postingID = new AbstractMap.SimpleEntry<String, String>("OrganizationID", Integer.toString(posting.getOrganizerID()));
        AbstractMap.SimpleEntry<String, String> jobTitle = new AbstractMap.SimpleEntry<String, String>("JobTitle", posting.getJobTitle());
        AbstractMap.SimpleEntry<String, String> location = new AbstractMap.SimpleEntry<String, String>("Location", posting.getLocation());
        AbstractMap.SimpleEntry<String, String> description = new AbstractMap.SimpleEntry<String, String>("Description", posting.getJobDescription());

        JSONArray json = readFromUrl("http://mobile.sheridanc.on.ca/~woodgre/Ladder/AddPosting.php", postingID, jobTitle, location, description);

        Log.d("LADDER_DEBUG", json.toString());

        String result = json.getString(0);
        if (result.equals("true")) {
            return true;
        }

        return false;
    }
}
