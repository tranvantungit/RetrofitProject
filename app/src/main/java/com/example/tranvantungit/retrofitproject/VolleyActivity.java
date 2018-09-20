package com.example.tranvantungit.retrofitproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.List;

import static com.example.tranvantungit.retrofitproject.RetrofitClient.LOCAL_URL_PHOTOS;

public class VolleyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, LOCAL_URL_PHOTOS, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, LOCAL_URL_PHOTOS,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<Photo> photos = Arrays.asList(new Gson().fromJson(response, Photo[].class));
                mAdapter = new PhotoAdapter(photos);
                recyclerView.setAdapter(mAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

}
