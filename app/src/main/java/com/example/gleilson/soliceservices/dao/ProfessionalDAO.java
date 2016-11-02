package com.example.gleilson.soliceservices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gleilson.soliceservices.model.Professional;
import com.example.gleilson.soliceservices.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleilson on 15/09/16.
 */
public class ProfessionalDAO extends BaseDAO {

    final private String TABLE_NAME = "professionals";

    public ProfessionalDAO(Context context) {
        super(context);
    }

    public void insert(Professional profile) {
        ContentValues values = getContentValues(profile);

        db.getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    @NonNull
    private ContentValues getContentValues(Professional professional) {
        ContentValues values = new ContentValues();
        values.put("firstname", professional.getFirstName());
        values.put("lastname", professional.getLastName());
        values.put("email", professional.getEmail());
        values.put("site", professional.getSite());
        values.put("url_image", professional.getUrlImage());

        return values;
    }

    public List<Professional> list() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        List<Professional> professionals = new ArrayList<Professional>();

        while (c.moveToNext()) {
            Professional professional = new Professional();

            professional.setId(c.getLong(c.getColumnIndex("id")));
            professional.setLastName(c.getString(c.getColumnIndex("lastname")));
            professional.setFirstName(c.getString(c.getColumnIndex("firstname")));
            professional.setEmail(c.getString(c.getColumnIndex("email")));
            professional.setSite(c.getString(c.getColumnIndex("site")));
            professional.setUrlImage(c.getString(c.getColumnIndex("url_image")));

            professionals.add(professional);
        }

        c.close();

        return professionals;
    }

    public void delete(Professional professional) {
        String [] params = { professional.getId().toString() };
        db.getWritableDatabase().delete(TABLE_NAME, "id = ?", params);
    }

    public void update(Professional professional) {
        ContentValues values = getContentValues(professional);

        String[] params = { professional.getId().toString() };
        db.getWritableDatabase().update(TABLE_NAME, values, "id = ?", params);

        db.close();
    }
}