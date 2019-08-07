package com.uwu.ans.foodsafty.facade;

import android.content.SharedPreferences;

import com.uwu.ans.foodsafty.R;
import com.uwu.ans.foodsafty.util.Constants;
import com.uwu.ans.foodsafty.util.UIUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.Context.MODE_PRIVATE;
import static com.uwu.ans.foodsafty.FoodApplication.sAppContext;



/**
 * Created by Rukshan on 4/29/19.
 */

public final class SystemFacade implements ISystemFacade {
    private static SystemFacade mFACADE = new SystemFacade();
    private final ExecutorService mExecutorService;
    private SharedPreferences mPreferences;

    private SystemFacade() {
        mExecutorService = Executors.newFixedThreadPool(Constants.DEFAULT_THREADS_NUM);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                mExecutorService.shutdown();
            }
        });
        mPreferences = sAppContext.getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);

    }

    public static SystemFacade getFacade() {
        if (mFACADE == null) {
            mFACADE = new SystemFacade();
        } else {
            return mFACADE;
        }
        return mFACADE;
    }

    public static void invalidate() {
        mFACADE = null;
    }

    private String getToken() {
        if (mPreferences.contains(Constants.ACCESS_TOKEN)) {
            return mPreferences.getString(Constants.ACCESS_TOKEN, "");
        } else {
            return UIUtils.getTitle(R.string.sample_token);
        }
    }


    public void setToken(String token) {

        mPreferences.edit().putString(Constants.ACCESS_TOKEN, token).apply();
    }

}
