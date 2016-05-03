package tech.gregwood.ladderandroid.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by greg on 5/3/2016.
 */
public class Utility {

    public static JSONArray readFromUrl(String url) throws MalformedURLException, IOException, JSONException {
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

    public static JSONArray getAllPostings() throws MalformedURLException, IOException, JSONException {
        JSONArray json = readFromUrl("http://mobile.sheridanc.on.ca/~woodgre/Ladder/GetAllPostings.php");

        return json;
    }
}
