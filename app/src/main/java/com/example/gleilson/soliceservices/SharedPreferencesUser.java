package com.example.gleilson.soliceservices;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.gleilson.soliceservices.model.Profile;

/**
 * Created by gleilson on 22/09/16.
 */
public class SharedPreferencesUser {

    public static void saveProfile(Context context, String token) {
        SharedPreferences sp = context.getSharedPreferences("Profile", 0);
        SharedPreferences.Editor Ed = sp.edit();

        Ed.putString("authentication_token", token);

        Ed.commit();
    }

    public static String getToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("Profile", context.MODE_PRIVATE);

        return sp.getString("authentication_token", null);
    }
}
