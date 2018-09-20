package com.example.tranvantungit.retrofitproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    CryptoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        RetrofitClient.getClient().create(RetrofitInterface.class).getMarkets().enqueue(new Callback<MarketResponse>() {
            @Override
            public void onResponse(@NonNull Call<MarketResponse> call, @NonNull Response<MarketResponse> response) {
                MarketResponse marketResponse  = response.body();
                List<Crypto> cryptoList =  marketResponse.getResult();
                mAdapter = new CryptoAdapter(cryptoList);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<MarketResponse> call, @NonNull Throwable t) {
                Log.e("Retrofit", "onFailure: ", t);
            }
        });
    }
}
