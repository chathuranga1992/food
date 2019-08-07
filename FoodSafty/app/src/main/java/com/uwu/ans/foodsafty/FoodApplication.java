package com.uwu.ans.foodsafty;

import android.app.Application;
import android.content.Context;


/**
 * Created by Rukshan on 4/29/19.
 */

public class FoodApplication extends Application {
    public static Context sAppContext;
    private static FoodApplication mInstance;

    public static FoodApplication get() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = getApplicationContext();
//        Configuration.Builder config = new Configuration.Builder(this);
//        config.addModelClasses(UserDataModel.class, ItineraryDataModel.class);
//        ActiveAndroid.initialize(config.create());
//        Configuration dbConfiguration = new Configuration.Builder(this).setDatabaseName("ifh.db").create();
//        ActiveAndroid.initialize(dbConfiguration);
//        FoodApplication.mInstance = this;
//        mBasicClient = new BasicChatClient(getApplicationContext());
    }

//    public BasicChatClient getBasicClient() {
//        return this.mBasicClient;
//    }
//
//    public void shutdownclient() {
//        mBasicClient.shutdown();
//    }
//
//    public void deleteDb() {
//        ActiveAndroid.dispose();
//
//        String aaName = ReflectionUtils.getMetaData(sAppContext, "AA_DB_NAME");
//        L.w("db--------->>>", "---name------>>> " + aaName);
//
//        if (aaName == null) {
//            aaName = "ifh.db";
//        }
//
//        L.e("db--------->>>", "---name------>>> " + aaName);
//        this.deleteDatabase("ifh.db");
//        deleteDatabase(aaName);
//        ActiveAndroid.initialize(this);
//    }


}
