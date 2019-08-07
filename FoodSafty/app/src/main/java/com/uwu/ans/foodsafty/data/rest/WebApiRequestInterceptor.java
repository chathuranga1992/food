package com.uwu.ans.foodsafty.data.rest;


import com.uwu.ans.foodsafty.util.L;
import com.uwu.ans.foodsafty.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Rukshan Nawarathna : coolrukshan@gmail.com on 3/28/2017.
 */
public final class WebApiRequestInterceptor {

    private String mToken;
    private String mAuthType;


    public OkHttpClient okHttpClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request originalRequest = chain.request();
                        Request.Builder builder = originalRequest.newBuilder();

                        builder.addHeader("Content-Type", "application/json");
                        builder.addHeader("Accept", "multipart/form-data");
                        if (StringUtils.isNotEmpty(mToken)) {
                            builder.addHeader("Authorization", mAuthType + " " + mToken);
                        }
                        L.w("------auth token----->>", mAuthType + " " + mToken);
                        builder.addHeader("tz", StringUtils.getTimeZone());
                        Request newRequest = builder.build();
                        L.v("=====REQUEST======", "====BODY==== " + newRequest.body());
                        L.v("=====REQUEST======", "====URL==== " + newRequest.url());

                        //Log.e("=====REQUEST======","====URL==== "+newRequest.url());
                        return chain.proceed(newRequest);
                    }
                }).build();

        return okHttpClient;
    }

    public void setAuth(String auth) {

        L.d("------Token----->>", auth);
        this.mToken = auth;

    }

    public void setAuthType(String authtype) {

        L.d("------auth type----->>", authtype);
        this.mAuthType = authtype;


    }
}
