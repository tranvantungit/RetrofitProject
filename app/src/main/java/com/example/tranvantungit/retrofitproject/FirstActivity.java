package com.example.tranvantungit.retrofitproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import retrofit2.Retrofit;

public class FirstActivity extends AppCompatActivity {
    Button retrofitButton, noLibraryButton, volleyButton, RxJava_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
         retrofitButton = findViewById(R.id.retrofit_button);
         noLibraryButton = findViewById(R.id.nolibrary_button);
         volleyButton = findViewById(R.id.volley_button);
         RxJava_button = findViewById(R.id.RxJava_button);

         retrofitButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(FirstActivity.this, RetrofitActivity.class));
             }
         });

         noLibraryButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(FirstActivity.this, NoLibraryActivity.class));
             }
         });

         volleyButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(FirstActivity.this, VolleyActivity.class));
             }
         });

        RxJava_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstActivity.this, RxActivity.class));
            }
        });

    }
}
