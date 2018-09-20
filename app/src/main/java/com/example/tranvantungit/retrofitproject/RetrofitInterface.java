package com.example.tranvantungit.retrofitproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitInterface {
    @GET("getmarkets")
    Call<MarketResponse> getMarkets();

    @GET
    Call<List<Photo>> getPhotos(@Url String urlPhotos);

    @POST
    @FormUrlEncoded
    Call<Photo> postPhoto(@Url String urlPhotos,
                          @Field("albumId") int albumId,
                          @Field("title") String title,
                          @Field("url") String url,
                          @Field("thumbnailUrl") String thumbnailUrl);
}
