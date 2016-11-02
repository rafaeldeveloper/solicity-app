package com.example.gleilson.soliceservices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.LocaleDisplayNames;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.gleilson.soliceservices.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleilson on 15/09/16.
 */
public class ProfileDAO extends BaseDAO {

    final private String TABLE_NAME = "profile";

    public ProfileDAO(Context context) {
        super(context);
    }

    public void insert(Profile profile) {
        ContentValues values = getContentValues(profile);

        db.getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    @NonNull
    private ContentValues getContentValues(Profile user) {
        ContentValues values = new ContentValues();
        values.put("firstname", user.getFirstName());
        values.put("lastname", user.getLastName());
        values.put("email", user.getEmail());
        values.put("site", user.getSite());
        values.put("url_image", user.getUrlImage());
        values.put("token", user.getToken());

        return values;
    }

    public Profile get(String token) {

        String sql = "SELECT * FROM profile;";
//        String sql = "SELECT * FROM profile WHERE token = '" + token + "';"
//
//
 Log.d("MAISUMTESTE", sql);;
        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        Profile profile = new Profile();

        while (c.moveToNext()) {

            Log.d("MAISUMTESTE", "asdadsasdad");

            profile.setId(c.getLong(c.getColumnIndex("id")));
            profile.setLastName(c.getString(c.getColumnIndex("lastname")));
            profile.setFirstName(c.getString(c.getColumnIndex("firstname")));
            profile.setEmail(c.getString(c.getColumnIndex("email")));
            profile.setSite(c.getString(c.getColumnIndex("site")));
            profile.setUrlImage(c.getString(c.getColumnIndex("url_image")));


            Log.d("MAISUMTESTE", profile.getEmail());
            Log.d("MAISUMTESTE", profile.getId().toString());
        }

        c.close();

        return profile;
    }

    public List<Profile> listUsers() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        List<Profile> users = new ArrayList<Profile>();

        while (c.moveToNext()) {
            Profile user = new Profile();

            user.setId(c.getLong(c.getColumnIndex("id")));
            user.setLastName(c.getString(c.getColumnIndex("lastname")));
            user.setFirstName(c.getString(c.getColumnIndex("firstname")));
            user.setEmail(c.getString(c.getColumnIndex("email")));
            user.setSite(c.getString(c.getColumnIndex("site")));
            user.setUrlImage(c.getString(c.getColumnIndex("url_image")));

            users.add(user);
        }

        c.close();

        return users;
    }

    public void delete(Profile user) {
        String [] params = {user.getId().toString()};
        db.getWritableDatabase().delete(TABLE_NAME, "id = ?", params);
    }

    public void update(Profile profile) {
        ContentValues values = getContentValues(profile);

        String[] params = { profile.getId().toString() };
        db.getWritableDatabase().update(TABLE_NAME, values, "id = ?", params);

        db.close();
    }
}