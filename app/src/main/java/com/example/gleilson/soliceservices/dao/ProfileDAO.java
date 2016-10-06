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
public class ProfileDAO extends SQLiteOpenHelper {

    public ProfileDAO(Context context) {
        super(context, "solicite_service", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE profile (" +
                         "   id INTEGER PRIMARY KEY " +
                         " , email TEXT NOT NULL " +
                         " , firstname TEXT NOT NULL " +
                         " , lastname TEXT NOT NULL " +
                         " , url_image TEXT " +
                         " , site TEXT " +
                         " , phone TEXT " +
                         " , token TEXT " +
                      ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int i1) {
//        String sql = "";
//        switch (oldVersion) {
//            case 1:
//                sql = "ALTER TABLE users ADD COLUMN path_image TEXT";
//                db.execSQL(sql);
//
//                sql = "ALTER TABLE users ADD COLUMN site TEXT";
//                db.execSQL(sql);
//        }
    }

    public void insert(Profile profile) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getContentValues(profile);

        db.insert("profile", null, values);
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

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

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
        String sql = "SELECT * FROM profile";

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(sql, null);

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
        SQLiteDatabase db = getWritableDatabase();

        String [] params = {user.getId().toString()};
        db.delete("profile", "id = ?", params);
    }

    public void update(Profile profile) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = getContentValues(profile);

        String[] params = { profile.getId().toString() };

        db.update("profile", values, "id = ?", params);
    }
}