package tech.gregwood.ladderandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import tech.gregwood.ladderandroid.data.Posting;
import tech.gregwood.ladderandroid.utility.GetAllPostingsTask;
import tech.gregwood.ladderandroid.utility.GetPostingTask;

public class MainActivity extends AppCompatActivity {

    private final String DEBUG_TAG = "LADDER_DEBUG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {

            ArrayList<Posting> postings = new GetAllPostingsTask().execute().get();

//            Posting posting = new GetPostingTask().execute(1).get();
//            ArrayList<Posting> postings = new ArrayList<>();
//            postings.add(posting);

            ListView postingsList = (ListView) findViewById(R.id.postings_list);

            postingsList.setAdapter(new ArrayAdapter<Posting>(this, android.R.layout.simple_list_item_1, postings));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
