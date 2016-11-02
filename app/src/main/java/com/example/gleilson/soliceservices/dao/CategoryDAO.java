package com.example.gleilson.soliceservices.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.gleilson.soliceservices.model.Category;
import com.example.gleilson.soliceservices.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleilson on 15/09/16.
 */
public class CategoryDAO extends BaseDAO {

    final private String TABLE_NAME = "categories";

    public CategoryDAO(Context context) {
        super(context);
    }

    public void insert(Category category) {
        ContentValues values = getContentValues(category);

        db.getWritableDatabase().insert(TABLE_NAME, null, values);
    }

    @NonNull
    private ContentValues getContentValues(Category category) {
        ContentValues values = new ContentValues();
        values.put("id", category.getId());
        values.put("name", category.getName());
        values.put("qty", category.getQty());
        values.put("url_image", category.getUrlImage());

        return values;
    }

    public List<Category> list() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        Cursor c = db.getReadableDatabase().rawQuery(sql, null);

        List<Category> categories = new ArrayList<Category>();

        while (c.moveToNext()) {
            Category user = new Category();

            user.setId(c.getLong(c.getColumnIndex("id")));
            user.setName(c.getString(c.getColumnIndex("name")));
            user.setUrlImage(c.getString(c.getColumnIndex("url_image")));
            user.setQty(c.getInt(c.getColumnIndex("qty")));

            categories.add(user);
        }

        c.close();

        return categories;
    }

    public void delete(Category category) {
        deleteById(TABLE_NAME, category.getId());
    }

    public void update(Category category) {
        ContentValues values = getContentValues(category);
        String[] params = { category.getId().toString() };
        db.getWritableDatabase().update(TABLE_NAME, values, "id = ?", params);
    }
}