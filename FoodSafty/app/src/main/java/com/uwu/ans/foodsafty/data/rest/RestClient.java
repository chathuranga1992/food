package com.uwu.ans.foodsafty.data.rest;


import com.uwu.ans.foodsafty.util.Constants;


/**
 * Created by Rukshan on 4/29/19.
 */

public final class RestClient implements RestClientService {
    private final WebApiRest mWebApiRestClient;
    private final WebApiRest mWebApiRestChat;

    private String mToken;

    public RestClient(String token, String authType) {
        WebApiRequestInterceptor interceptor = new WebApiRequestInterceptor();
        interceptor.setAuth(token);
        interceptor.setAuthType(authType);
        mWebApiRestClient = WebApiRestFactory.newInstance(Constants.API_REST_HOST, interceptor.okHttpClient());
        mWebApiRestChat = WebApiRestFactory.newInstance(Constants.CHAT_API_REST_HOST, interceptor.okHttpClient());
    }

    @Override
    public void setToken(String token) {
        mToken = token;
    }

}
