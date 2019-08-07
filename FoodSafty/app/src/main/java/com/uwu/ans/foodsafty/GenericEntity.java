package com.uwu.ans.foodsafty;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.uwu.ans.foodsafty.util.IOUtils;

import java.io.Serializable;
import java.io.StringReader;


/**
 * Created by Rukshan on 11/12/18.
 */

public abstract class GenericEntity implements Serializable {

    public static <T extends GenericEntity> T asEntity(Class<T> clazz, Context context, String filename) {
        final Gson gson = new Gson();
        final String json = IOUtils.loadJsonFromFile(context, filename);
        final JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return gson.fromJson(reader, clazz);
    }

    public static String asEntity(GenericEntity clazz) {
        final Gson gson = new Gson();
        return gson.toJson(clazz);
    }

    public static <T extends GenericEntity> T asEntity(Class<T> clazz, String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }


}
