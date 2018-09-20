package com.example.tranvantungit.retrofitproject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    static final String BASE_URL = "https://bittrex.com/api/v1.1/public/";
    static final String LOCAL_URL_PHOTOS = "http://10.0.2.2:3000/photos";
    private static Retrofit retrofit = null;

    private static OkHttpClient getOkhttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(loggingInterceptor);
        okhttpBuilder.connectTimeout(15, TimeUnit.SECONDS);
        return okhttpBuilder.build();
    }
    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkhttpClient())
                    .build();
        }

        return retrofit;
    }
}
