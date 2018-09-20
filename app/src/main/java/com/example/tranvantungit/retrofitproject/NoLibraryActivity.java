package com.example.tranvantungit.retrofitproject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.tranvantungit.retrofitproject.RetrofitClient.LOCAL_URL_PHOTOS;

public class NoLibraryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_library);
        recyclerView = findViewById(R.id.recyclerView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new NetworkTask().execute();
    }

    @SuppressLint("StaticFieldLeak")
    class NetworkTask extends AsyncTask<Void, Void, List<Photo>> {

        @Override
        protected List<Photo> doInBackground(Void... voids) {
            HttpURLConnection connection = null;
            List<Photo> photos = new ArrayList<>();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                URL url = new URL(LOCAL_URL_PHOTOS);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                if (connection.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    String jsonString = stringBuilder.toString();
                    //Gson convert
                    List<Photo> photos1 = Arrays.asList(new Gson().fromJson(jsonString, Photo[].class));
                    photos.addAll(photos1);

                    //Manual Convert
//                    JSONArray jsonArray = new JSONArray(jsonString);
//                    Photo photo;
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject object = jsonArray.getJSONObject(i);
//                        int albumId = object.getInt("albumId");
//                        int id = object.getInt("id");
//                        String title = object.getString("title");
//                        String urlImage = object.getString("url");
//                        String thumbnailUrl = object.getString("thumbnailUrl");
//                        photo = new Photo();
//                        photo.setId(id);
//                        photo.setAlbumId(albumId);
//                        photo.setTitle(title);
//                        photo.setUrl(urlImage);
//                        photo.setThumbnailUrl(thumbnailUrl);
//                        photos.add(photo);
//                    }
                } else {

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }


            return photos;
        }

        @Override
        protected void onPostExecute(List<Photo> photos) {
            photoAdapter = new PhotoAdapter(photos);
            recyclerView.setAdapter(photoAdapter);
            super.onPostExecute(photos);
        }
    }
}
