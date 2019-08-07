package com.uwu.ans.foodsafty.data.rest;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rukshan Nawarathna : coolrukshan@gmail.com on 3/28/2017.
 */
public final class WebApiRestFactory {
    public static WebApiRest newInstance(String endpoint, OkHttpClient okHttpClient) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(WebApiRest.class);
    }
}
