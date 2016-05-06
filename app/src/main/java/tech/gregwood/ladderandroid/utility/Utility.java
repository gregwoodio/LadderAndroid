package tech.gregwood.ladderandroid.utility;

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

/**
 * Created by greg on 5/3/2016.
 */
public class Utility {

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
     * @return
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

    public static JSONArray getAllPostings() throws IOException, JSONException {
        JSONArray json = readFromUrl("http://mobile.sheridanc.on.ca/~woodgre/Ladder/GetAllPostings.php");

        return json;
    }

    public static JSONArray getPosting(int id) throws IOException, JSONException {
        AbstractMap.SimpleEntry<String, String> requestedID = new AbstractMap.SimpleEntry<String, String>("PostingID", Integer.toString(id));
        JSONArray json = readFromUrl("http://mobile.sheridanc.on.ca/~woodgre/Ladder/GetPosting.php", requestedID);
        return json;
    }
}
