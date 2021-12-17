package com.example.WoTStat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
// Tag message
    private static final String TAG = "ResponseSuccess";

    public List<Stat> stats;
    public List<Stat> getStats() {
        return stats;
    }
    public void setStats(List<Stat> stats) {
        this.stats= stats;
    }

    public void recyclerViewMaker() {


        //stats = getStats();
        //RecyclerView recyclerView = findViewById(R.id.recyclerView);
        //RecyclerView.Adapter adapter = new StatRecyclerViewAdapter(getApplicationContext(),stats);
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        //recyclerView.setAdapter(adapter);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");



        // A new volley request Queue for calling the wargaming API
        RequestQueue queue = Volley.newRequestQueue(this);


        //Setting the search button listener
        Button buttonsearch = (Button) findViewById(R.id.searchbutton);
        buttonsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked buttonsearch.");
                String newAccountSearch;


        // Getting the user input from accountsearch and adding it to the end of the url to search for A user account
                EditText searchterm = findViewById(R.id.accountsearch);
                newAccountSearch = String.valueOf(searchterm.getText());

                String url = "https://api.worldoftanks.eu/wot/account/list/?application_id=b6c4165d27e3528c85b26168ca5fec8a&search="+newAccountSearch;


                // Request a string response from the provided URL.

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                //Logging the response
                        Log.d(TAG, response);
                        Log.d(TAG, url);

                        JsonConverter converter = new JsonConverter();
                        List<Stat> stats = converter.JsonToStat(response);
                        setStats(stats);
                        recyclerViewMaker();


                        

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getLocalizedMessage());
                    }

                });
            // Adding the request to the queue
                queue.add(stringRequest);


            }
        });

    // Button to take you to the shortlist page
        Button buttonshort = (Button) findViewById(R.id.buttonshort);
        buttonshort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked buttonshort.");

                Intent intent = new Intent(MainActivity.this, shortlist.class);
                startActivity(intent);
            }
        });




    }


// Functions that make it so the app remembers what it was doing if you do certain things like close it and resume it
    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onContentChanged() {

        super.onContentChanged();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}
