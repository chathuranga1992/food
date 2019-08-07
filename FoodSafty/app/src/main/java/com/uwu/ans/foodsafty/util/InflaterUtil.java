package com.uwu.ans.foodsafty.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Rukshan Nawarathna : coolrukshan@gmail.com on 8/24/2016.
 */
public final class InflaterUtil {
    public static View inflate(Context context, View convertView, ViewGroup parent, int resId) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, parent, false);
        }
        return convertView;
    }

    public static View inflate(Context context, int resId) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(resId, null);
    }
}
