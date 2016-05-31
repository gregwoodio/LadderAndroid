package tech.gregwood.ladderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import tech.gregwood.ladderandroid.data.Organization;
import tech.gregwood.ladderandroid.data.Posting;
import tech.gregwood.ladderandroid.data.Profile;
import tech.gregwood.ladderandroid.data.User;
import tech.gregwood.ladderandroid.utility.AddOrganizationTask;
import tech.gregwood.ladderandroid.utility.AddPostingTask;
import tech.gregwood.ladderandroid.utility.AddUserTask;
import tech.gregwood.ladderandroid.utility.GetAllPostingsTask;
import tech.gregwood.ladderandroid.utility.GetPostingTask;
import tech.gregwood.ladderandroid.utility.LoginTask;

public class MainActivity extends AppCompatActivity {

    private final String DEBUG_TAG = "LADDER_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {

            ArrayList<Posting> postings = new GetAllPostingsTask().execute().get();

            ListView postingsList = (ListView) findViewById(R.id.postings_list);

            postingsList.setAdapter(new ArrayAdapter<Posting>(this, android.R.layout.simple_list_item_1, postings));

//            Log.d("LADDER_DEBUG", "Postings: " + postings.toString());
//            Posting posting = new GetPostingTask().execute(1).get();
//            ArrayList<Posting> postings = new ArrayList<>();
//            postings.add(posting);


            /*
            //try logging a user
            Profile profile = new LoginTask().execute("mississauga", "password").get();
            if (profile == null) {
                // ¯\_(ツ)_/¯
                Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
            } else if (profile.getAccountType() == 0) {
                User user = (User)profile;
                String text = "Welcome " + user.getFirstName() + " " + user.getLastName() + "!";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            } else if (profile.getAccountType() == 1) {
                Organization org = (Organization)profile;
                String text = "Welcome " + org.getOrganizationName() + "!";
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
            */

/*
            //Try Adding a new user
            User user = new User();
            user.setUsername("test2");
            user.setFirstName("Jane");
            user.setLastName("Doe");
            user.setPassword("password");
            user.setEmail("janedoe@email.com");
            user.setUserDescription("This is another test account");
            user.setResume("Test resume");
            try {
                user.setPictureURL(new URL("https://i.imgur.com/bW6ur5w.jpg"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            boolean success = new AddUserTask().execute(user).get();
            if (success) {
                Toast.makeText(this, "User added.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "User was not added.", Toast.LENGTH_SHORT).show();
            }
            */


            //Try Adding a new organization
            Organization org = new Organization();
            org.setUsername("test3");
            org.setOrganizationName("Sample Organization");
            org.setPassword("password");
            org.setEmail("janedoe@email.com");
            org.setAddress("123 Test St.");
            org.setMissionStatement("Test mission");
            try {
                org.setUrl(null);
                org.setPictureURL(new URL("https://i.imgur.com/bW6ur5w.jpg"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            boolean success = new AddOrganizationTask().execute(org).get();
            if (success) {
                Toast.makeText(this, "Organization added.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Organization was not added.", Toast.LENGTH_SHORT).show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
