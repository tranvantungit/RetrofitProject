package com.example.tranvantungit.retrofitproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tranvantungit.retrofitproject.RetrofitClient.LOCAL_URL_PHOTOS;

public class RetrofitActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        recyclerView = findViewById(R.id.recyclerView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postPhotos(6969, "babybaby19951111", "https://c-sf.smule.com/sf/z1/account/picture/7a/04/63a836c1-b2ab-4f09-8be3-9711f59b0f37_1024.jpg", "https://c-sf.smule.com/sf/z1/account/picture/7a/04/63a836c1-b2ab-4f09-8be3-9711f59b0f37_1024.jpg");
            }
        });

        getListPhotos();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getListPhotos() {
        RetrofitClient.getClient().create(RetrofitInterface.class).getPhotos(LOCAL_URL_PHOTOS).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                List<Photo> photos = response.body();
                photoAdapter = new PhotoAdapter(photos);
                recyclerView.setAdapter(photoAdapter);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e("Retrofit", "onFailure: ", t);
            }
        });
    }

    private void postPhotos(int albumId, String title, String url, String thumbnailUrl) {
        RetrofitClient.getClient().create(RetrofitInterface.class).postPhoto(LOCAL_URL_PHOTOS, albumId, title, url, thumbnailUrl).enqueue(new Callback<Photo>() {
            @Override
            public void onResponse(Call<Photo> call, Response<Photo> response) {
                getListPhotos();
            }

            @Override
            public void onFailure(Call<Photo> call, Throwable t) {

            }
        });
    }

}
